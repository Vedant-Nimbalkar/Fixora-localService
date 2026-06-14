package com.localservice.controller;

import com.localservice.model.Booking;
import com.localservice.repository.BookingRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private BookingRepository bookingRepository;

    @Value("${razorpay.key.id}")
    private String keyId;

    @Value("${razorpay.key.secret}")
    private String keySecret;

    // Step 1 — Create Razorpay order
    @PostMapping("/create-order/{bookingId}")
    public ResponseEntity<Map<String, Object>> createOrder(@PathVariable Long bookingId) {
        Map<String, Object> response = new HashMap<>();

        try {
            Booking booking = bookingRepository.findById(bookingId)
                    .orElseThrow(() -> new RuntimeException("Booking not found"));

            RazorpayClient client = new RazorpayClient(keyId, keySecret);

            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", 100); // amount in paise = ₹500 (change as needed)
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "booking_" + bookingId);

            Order order = client.orders.create(orderRequest);

            // Save Razorpay order ID to booking
            booking.setRazorpayOrderId(order.get("id").toString());
            bookingRepository.save(booking);

            response.put("orderId", order.get("id").toString());
            response.put("amount", 50000);
            response.put("currency", "INR");
            response.put("keyId", keyId);
            response.put("bookingId", bookingId);
            response.put("customerName", booking.getCustomerName());
            response.put("userEmail", booking.getUserEmail());

            return ResponseEntity.ok(response);

        } catch (RazorpayException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    // Step 2 — Verify payment after success
    @PostMapping("/verify")
    public ResponseEntity<Map<String, Object>> verifyPayment(@RequestBody Map<String, String> payload) {
        Map<String, Object> response = new HashMap<>();

        try {
            String razorpayOrderId   = payload.get("razorpay_order_id");
            String razorpayPaymentId = payload.get("razorpay_payment_id");
            String razorpaySignature = payload.get("razorpay_signature");
            Long bookingId           = Long.parseLong(payload.get("bookingId"));

            // Verify signature
            String data = razorpayOrderId + "|" + razorpayPaymentId;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(keySecret.getBytes(), "HmacSHA256"));
            byte[] hash = mac.doFinal(data.getBytes());

            // Convert to hex
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            if (hexString.toString().equals(razorpaySignature)) {
                // Signature matches — mark booking as PAID
                Booking booking = bookingRepository.findById(bookingId)
                        .orElseThrow(() -> new RuntimeException("Booking not found"));
                booking.setPaymentStatus("PAID");
                bookingRepository.save(booking);

                response.put("success", true);
                response.put("message", "Payment verified successfully");
            } else {
                response.put("success", false);
                response.put("message", "Payment verification failed");
            }

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}
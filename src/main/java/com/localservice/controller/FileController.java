//package com.localservice.controller;
//
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.UrlResource;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//@Controller
//public class FileController {
//
//    private static final String UPLOAD_DIR =
//        "src/main/resources/static/uploads/partners/";
//
//    @GetMapping("/uploads/partners/{filename:.+}")
//    @ResponseBody
//    public ResponseEntity<Resource> serveFile(
//            @PathVariable String filename) {
//        try {
//            Path file = Paths.get(UPLOAD_DIR).resolve(filename);
//            Resource resource = new UrlResource(file.toUri());
//
//            if (resource.exists() || resource.isReadable()) {
//                String contentType = "application/octet-stream";
//                if (filename.endsWith(".png")) contentType = "image/png";
//                else if (filename.endsWith(".jpg") || 
//                         filename.endsWith(".jpeg")) contentType = "image/jpeg";
//                else if (filename.endsWith(".pdf")) contentType = "application/pdf";
//
//                return ResponseEntity.ok()
//                    .contentType(MediaType.parseMediaType(contentType))
//                    .body(resource);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.notFound().build();
//    }
//}

package com.localservice.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileController {

    private static final String UPLOAD_DIR =
        "src/main/resources/static/uploads/partners/";

    @GetMapping("/uploads/partners/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(
            @PathVariable String filename) {
        try {
            Path file = Paths.get(UPLOAD_DIR).resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                String contentType = "application/octet-stream";
                if (filename.endsWith(".png")) contentType = "image/png";
                else if (filename.endsWith(".jpg") || 
                         filename.endsWith(".jpeg")) contentType = "image/jpeg";
                else if (filename.endsWith(".pdf")) contentType = "application/pdf";

                return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.notFound().build();
    }
}
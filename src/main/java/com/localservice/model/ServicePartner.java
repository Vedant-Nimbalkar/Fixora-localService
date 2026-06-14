//
//package com.localservice.model;
//
//import jakarta.persistence.*;
//
//@Entity
//public class ServicePartner {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//
//	private String name;
//	private String email;
//	private String phone;
//	private String serviceType;
//	private int experience;
//	private String password;
//	private String city;
//	@Column(columnDefinition = "TEXT")
//	private String bio;
//	private String profilePhotoPath;
//
//	@Column(length = 2000)
//	private String portfolioPhotos;
//
//	public String getBio() {
//		return bio;
//	}
//
//	public void setBio(String bio) {
//		this.bio = bio;
//	}
//
//	public String getProfilePhotoPath() {
//		return profilePhotoPath;
//	}
//
//	public void setProfilePhotoPath(String profilePhotoPath) {
//		this.profilePhotoPath = profilePhotoPath;
//	}
//
//	public String getPortfolioPhotos() {
//		return portfolioPhotos;
//	}
//
//	public void setPortfolioPhotos(String portfolioPhotos) {
//		this.portfolioPhotos = portfolioPhotos;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getPhone() {
//		return phone;
//	}
//
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}
//
//	public String getServiceType() {
//		return serviceType;
//	}
//
//	public void setServiceType(String serviceType) {
//		this.serviceType = serviceType;
//	}
//
//	public int getExperience() {
//		return experience;
//	}
//
//	public void setExperience(int experience) {
//		this.experience = experience;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	private boolean suspended = false;
//
//	public boolean isSuspended() {
//		return suspended;
//	}
//
//	public void setSuspended(boolean suspended) {
//		this.suspended = suspended;
//	}
//
//	public String getCity() {
//		return city;
//	}
//
//	public void setCity(String city) {
//		this.city = city;
//	}
//}

package com.localservice.model;

import jakarta.persistence.*;

@Entity
public class ServicePartner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String email;
	private String phone;
	private String serviceType;
	private int experience;
	private String password;
	private String city;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private boolean suspended = false;

	public boolean isSuspended() {
		return suspended;
	}

	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
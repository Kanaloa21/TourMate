package com.ssafy.tourmate.dto;

public class UserDto {
	String userId;
	String userPw;
	String userName;
	int gender;
	String phoneNumber;
	int authorizeLevel;

	public UserDto() {
		super();
	}

	public UserDto(String userId, String userPw, String userName, int gender, String phoneNumber, int authorizeLevel) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.authorizeLevel = authorizeLevel;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	

	public int getAuthorizeLevel() {
		return authorizeLevel;
	}

	public void setAuthorizeLevel(int authorizeLevel) {
		this.authorizeLevel = authorizeLevel;
	}

	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", userPw=" + userPw + ", userName=" + userName + ", gender=" + gender
				+ ", phoneNumber=" + phoneNumber + ", authorizeLevel=" + authorizeLevel + "]";
	}


}

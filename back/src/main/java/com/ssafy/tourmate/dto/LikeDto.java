package com.ssafy.tourmate.dto;

public class LikeDto {
	int id;
	String userId;
	
	public LikeDto() {
		super();
	}
	public LikeDto(int id, String userId) {
		super();
		this.id = id;
		this.userId = userId;

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "LikeDto [id=" + id + ", userId=" + userId + "]";
	}
	
}

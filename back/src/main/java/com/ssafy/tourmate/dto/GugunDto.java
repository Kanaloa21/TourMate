package com.ssafy.tourmate.dto;

public class GugunDto {
	String gugunName;
	int gugunCode;
	
	public GugunDto() {
		super();
	}

	public GugunDto(String name, int gugunCode) {
		super();
		this.gugunName = name;
		this.gugunCode = gugunCode;
	}

	public String getGugunName() {
		return gugunName;
	}

	public void setGugunName(String name) {
		this.gugunName = name;
	}

	public int getGugunCode() {
		return gugunCode;
	}

	public void setGugunCode(int gugunCode) {
		this.gugunCode = gugunCode;
	}

	@Override
	public String toString() {
		return "gugunDto [name=" + gugunName + ", gugunCode=" + gugunCode + "]";
	}
	
}

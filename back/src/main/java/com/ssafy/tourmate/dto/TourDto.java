package com.ssafy.tourmate.dto;

public class TourDto {
	int contentId;
	int contentType;
	String imageURL;
	String title;
	String addr1;
	String addr2;
	double latitude;
	double longitude;
	int likeCount;
	boolean liked;

	
	public TourDto() {
		super();
	}

	public TourDto(int contentId, int contentType, String imageURL, String title, String addr1, String addr2,
			double latitude, double longitude, int likeCount, boolean liked) {
		super();
		this.contentId = contentId;
		this.contentType = contentType;
		this.imageURL = imageURL;
		this.title = title;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.latitude = latitude;
		this.longitude = longitude;
		this.likeCount = likeCount;
		this.liked = liked;
	}

	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	
	public int getContentType() {
		return contentType;
	}
	public void setContentType(int contentType) {
		this.contentType = contentType;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	
	public boolean getLiked() {
		return liked;
	}
	public void setLiked(boolean liked) {
		this.liked = liked;
	}
	@Override
	public String toString() {
		return "TourDto [contentId=" + contentId + ", imageURL=" + imageURL + ", title=" + title + ", addr1=" + addr1
				+ ", addr2=" + addr2 + ", latitude=" + latitude + ", longitude=" + longitude + ", likeCount="
				+ likeCount + "isLiked=" + liked + "]";
	}
	
}

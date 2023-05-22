package com.ssafy.tourmate.dto;

public class PlanAttractionDto {
	int id;
	int contentId;
	String imageURL;
	String title;
	String addr1;
	String addr2;
	double latitude;
	double longitude;
	int likeCount;
	String startTime;
	String endTime;
	
	public PlanAttractionDto() {
		super();
	}

	

	public PlanAttractionDto(int id, int contentId, String imageURL, String title, String addr1, String addr2,
			double latitude, double longitude, int likeCount, String startTime, String endTime) {
		super();
		this.id = id;
		this.contentId = contentId;
		this.imageURL = imageURL;
		this.title = title;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.latitude = latitude;
		this.longitude = longitude;
		this.likeCount = likeCount;
		this.startTime = startTime;
		this.endTime = endTime;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}



	@Override
	public String toString() {
		return "PlanAttractionDto [id=" + id + ", contentId=" + contentId + ", imageURL=" + imageURL + ", title="
				+ title + ", addr1=" + addr1 + ", addr2=" + addr2 + ", latitude=" + latitude + ", longitude="
				+ longitude + ", likeCount=" + likeCount + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}

}

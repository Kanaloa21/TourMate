package com.ssafy.tourmate.dto;

import java.util.List;

public class PlanDto {
	int planId;
	String userId;
	String subject;
	String summary;
	String createTime;
	int hit;
	int likeCount;
	boolean isBookmarked;
	boolean isLiked;
	List<PlanAttractionDto> planAttractions;
	
	
	public PlanDto() {
		super();
	}


	public PlanDto(int planId, String userId, String subject, String summary, String createTime, int hit, int like,
			boolean isBookmarked, boolean isLiked, List<PlanAttractionDto> planAttractions) {
		super();
		this.planId = planId;
		this.userId = userId;
		this.subject = subject;
		this.summary = summary;
		this.createTime = createTime;
		this.hit = hit;
		this.likeCount = like;
		this.isBookmarked = isBookmarked;
		this.isLiked = isLiked;
		this.planAttractions = planAttractions;
	}


	public int getPlanId() {
		return planId;
	}


	public void setPlanId(int planId) {
		this.planId = planId;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getSummary() {
		return summary;
	}


	public void setSummary(String summary) {
		this.summary = summary;
	}


	public String getCreateTime() {
		return createTime;
	}


	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	public int getHit() {
		return hit;
	}


	public void setHit(int hit) {
		this.hit = hit;
	}


	public int getLikeCount() {
		return likeCount;
	}


	public void setLikeCount(int like) {
		this.likeCount = like;
	}


	public boolean isBookmarked() {
		return isBookmarked;
	}


	public void setBookmarked(boolean isBookmarked) {
		this.isBookmarked = isBookmarked;
	}


	public boolean isLiked() {
		return isLiked;
	}


	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}


	public List<PlanAttractionDto> getPlanAttractions() {
		return planAttractions;
	}


	public void setPlanAttractions(List<PlanAttractionDto> planAttractions) {
		this.planAttractions = planAttractions;
	}


	@Override
	public String toString() {
		return "PlanDto [planId=" + planId + ", userId=" + userId + ", subject=" + subject + ", summary=" + summary
				+ ", createTime=" + createTime + ", hit=" + hit + ", likeCount=" + likeCount + ", isBookmarked=" + isBookmarked
				+ ", isLiked=" + isLiked + "]";
	}
	
	
}

package com.ssafy.tourmate.dto;

public class CommentDto {
	int id;
	int contentId;
	String comment;
	String writer;
	String writeDate;
	
	public CommentDto() {
		super();
	}

	public CommentDto(int id, int contentId, String comment, String writer, String writeDate) {
		super();
		this.id = id;
		this.contentId = contentId;
		this.comment = comment;
		this.writer = writer;
		this.writeDate = writeDate;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	@Override
	public String toString() {
		return "CommentDto [id=" + id + ", contentId=" + contentId + ", content=" + comment + ", writer=" + writer
				+ ", writeDate=" + writeDate + "]";
	}
	
	
}

package com.ssafy.tourmate.dto;

public class NoticeDto {
	int id;
	String title;
	String writer;
	String content;
	String date;
	
	public NoticeDto() {
		super();
	}
	public NoticeDto(int id, String title, String writer, String content, String date) {
		super();
		this.id = id;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "NoticeDto [id=" + id + ", title=" + title + ", writer=" + writer + ", content=" + content + ", date="
				+ date + "]";
	}
	
}

package com.ssafy.tourmate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.tourmate.dto.NoticeDto;
import com.ssafy.tourmate.mapper.NoticeMapper;

@Service
public class NoticeService {

	private NoticeMapper noticeMapper;
	
	private NoticeService(NoticeMapper mapper) {
		this.noticeMapper = mapper;
	}
	
	public int writeNotice(NoticeDto notice) {
		return noticeMapper.insertNotice(notice);
	}
	
	public List<NoticeDto> getNoticeList(String keyword) {
		return noticeMapper.selectList(keyword);
	}
	
	public NoticeDto getNotice(int id) {
		return noticeMapper.selectOne(id);
	}
	
	public int modifyNotice(NoticeDto notice) {
		return noticeMapper.updateNotice(notice);
	}
	
	public int removeNotice(int id) {
		return noticeMapper.deleteNotice(id);
	}

}

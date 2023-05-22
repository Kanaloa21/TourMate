package com.ssafy.tourmate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.tourmate.dto.NoticeDto;

@Mapper
public interface NoticeMapper {

	public List<NoticeDto> selectList(String keyword);
	public NoticeDto selectOne(int id);
	
	public int insertNotice(NoticeDto notice);
	
	public int updateNotice(NoticeDto notice);
	
	public int deleteNotice(int id);

}

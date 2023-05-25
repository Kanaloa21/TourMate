package com.ssafy.tourmate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.tourmate.dto.CommentDto;

@Mapper
public interface CommentMapper {

	public List<CommentDto> selectTourCommentList(int contentId);
	public int insertTourComment(CommentDto dto);
	public int deleteTourComment(int commentId);
	
	public List<CommentDto> selectPlanCommentList(int planId);
	public int insertPlanComment(CommentDto dto);
	public int deletePlanComment(int commentId);
	public int deletePlanCommentByPlanId(int planId);
	
}

package com.ssafy.tourmate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.tourmate.dto.CommentDto;
import com.ssafy.tourmate.mapper.CommentMapper;

@Service
public class CommentService {

	@Autowired
	private CommentMapper commentMapper;
	
	public List<CommentDto> getTourComment(int contentId) {
		return commentMapper.selectTourCommentList(contentId);
	}
	
	public int writeTourComment(CommentDto dto) {
		return commentMapper.insertTourComment(dto);
	}
	
	public int removeTourComment(int commentId) {
		return commentMapper.deleteTourComment(commentId);
	}
	
	public List<CommentDto> getPlanComment(int planId) {
		return commentMapper.selectPlanCommentList(planId);
	}
	
	public int writePlanComment(CommentDto dto) {
		return commentMapper.insertPlanComment(dto);
	}
	
	public int removePlanComment(int commentId) {
		return commentMapper.deletePlanComment(commentId);
	}
}

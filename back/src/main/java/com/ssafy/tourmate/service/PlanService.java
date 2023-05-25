package com.ssafy.tourmate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.tourmate.dto.PlanAttractionDto;
import com.ssafy.tourmate.dto.PlanDto;
import com.ssafy.tourmate.mapper.CommentMapper;
import com.ssafy.tourmate.mapper.PlanMapper;

@Service
public class PlanService {
	
	private PlanMapper planMapper;
	private CommentMapper commentMapper;
	
	private PlanService(PlanMapper mapper, CommentMapper commentMapper) {
		this.planMapper = mapper;
		this.commentMapper = commentMapper;
	}

	public List<PlanDto> searchPlans(String title, String userId, int sortType) {
		return planMapper.select(title, userId, sortType);
	}
	
	public PlanDto searchPlan(int planId, String userId) {
		planMapper.updateHit(planId);
		PlanDto plan = planMapper.selectPlan(planId);
		
		if (userId != null) {
			if (planMapper.checkBookmarked(planId, userId) == 1) {
				plan.setBookmarked(true);
			}
			
			if (planMapper.checkLiked(planId, userId) == 1) {
				plan.setLiked(true);
			}
		}

		plan.setLikeCount(planMapper.countLiked(planId));
		
		List<PlanAttractionDto> planAttractions = planMapper.selectPlanAttractions(planId);
		plan.setPlanAttractions(planAttractions);
		
		return plan;
	}

	
	public int createPlan(PlanDto plan) {
		
		int result = planMapper.insertPlan(plan);
		if (result == 0 ) return 0;
		
		result = planMapper.insertPlanAttractions(plan.getPlanId(), plan.getPlanAttractions());
		return result;
	}
	
	public int modifyPlan(PlanDto plan) {
		
		int result = planMapper.updatePlan(plan);
		planMapper.deletePlanAttractions(plan.getPlanId());
		planMapper.insertPlanAttractions(plan.getPlanId(), plan.getPlanAttractions());
		
		return result;
	}

	public int removePlan(int planId) {
		commentMapper.deletePlanCommentByPlanId(planId);
		planMapper.deletePlanAttractions(planId);
		planMapper.deleteBookmark(planId, null);
		planMapper.deleteLike(planId, null);
		int result = planMapper.deletePlan(planId);
		
		return result;
	}

	public int changePlanLike(int planId, String userId) {
		if (planMapper.checkLiked(planId, userId) == 0) {
			return planMapper.insertLike(planId, userId);
		} else {
			return planMapper.deleteLike(planId, userId);
		}
	}

	public int changePlanBookmark(int planId, String userId) {
		if (planMapper.checkBookmarked(planId, userId) == 0) {
			return planMapper.insertBookmark(planId, userId);
		} else {
			return planMapper.deleteBookmark(planId, userId);
		}
	}

	public List<PlanDto> searchBookmarkPlans(String userId, int sortType) {
		return planMapper.selectBookmarkList(userId, sortType);
	}

}

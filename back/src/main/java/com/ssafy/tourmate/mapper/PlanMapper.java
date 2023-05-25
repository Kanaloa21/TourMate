package com.ssafy.tourmate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.tourmate.dto.PlanAttractionDto;
import com.ssafy.tourmate.dto.PlanDto;

@Mapper
public interface PlanMapper {

	List<PlanDto> select(String title, String userId, int sortType);
	List<PlanDto> selectBookmarkList(String userId, int sortType);
	PlanDto selectPlan(int planId);
	List<PlanAttractionDto> selectPlanAttractions(int planId);
	int countLiked(int planId);
	int checkLiked(int planId, String userId);
	int checkBookmarked(int planId, String userId);
	
	int insertPlan(PlanDto plan);
	int insertPlanAttractions(int planId, List<PlanAttractionDto> planAttractions);
	int insertLike(int planId, String userId);
	int insertBookmark(int planId, String userId);
	
	int updatePlan(PlanDto plan);
	int updateHit(int planId);
	
	int deletePlan(int planId);
	int deletePlanAttractions(int planId);
	int deleteLike(int planId, String userId);
	int deleteBookmark(int planId, String userId);
	
	
}

package com.ssafy.tourmate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.tourmate.dto.GugunDto;
import com.ssafy.tourmate.dto.SidoDto;
import com.ssafy.tourmate.dto.TourDto;

@Mapper
public interface TourMapper {

	List<TourDto> selectList(int sidoCode, int gugunCode, String keyword);
	
	TourDto selectDetail(int contentId);

	int createLike(int contentId, String userId);
	
	int deleteLike(int contentId, String userId);

	List<SidoDto> selectSidoList();
	
	List<GugunDto> selectGugunList(int sidoCode);
	
	int isLiked(int contentId, String userId);


}

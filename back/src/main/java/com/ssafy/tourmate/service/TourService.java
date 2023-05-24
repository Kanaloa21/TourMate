package com.ssafy.tourmate.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ssafy.tourmate.dto.GugunDto;
import com.ssafy.tourmate.dto.SidoDto;
import com.ssafy.tourmate.dto.TourDto;
import com.ssafy.tourmate.mapper.TourMapper;

@Service
public class TourService {
	
	private TourMapper tourMapper;
	
	private TourService(TourMapper mapper) {
		this.tourMapper = mapper;
	}

	public List<TourDto> getTourList(int sidoCode, int gugunCode, String keyword, String userId) {
		return tourMapper.selectList(sidoCode, gugunCode, keyword, userId);
	}

	public TourDto getTourDetail(int contentId, String userId) {
		TourDto dto = tourMapper.selectDetail(contentId);
		if (userId != null) {
			dto.setLiked(tourMapper.isLiked(contentId, userId) == 1);
		}
		return dto;
	}

	public int updateLike(int contentId, String userId) {
		if (tourMapper.isLiked(contentId, userId) == 0) {
			return tourMapper.createLike(contentId, userId);
		} else {
			return tourMapper.deleteLike(contentId, userId);
		}
	}
	

	public List<GugunDto> getGugunList(int sidoCode) {
		return tourMapper.selectGugunList(sidoCode);
	}

	public List<SidoDto> getSidoList() {
		return tourMapper.selectSidoList();
	}

}

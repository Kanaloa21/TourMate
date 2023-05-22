package com.ssafy.tourmate.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.tourmate.dto.GugunDto;
import com.ssafy.tourmate.dto.SidoDto;
import com.ssafy.tourmate.dto.TourDto;
import com.ssafy.tourmate.service.TourService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/tour")
@CrossOrigin("*")
@Api("여행지 컨트롤러 API V1")
public class TourController {
	
	private static final Logger logger = LoggerFactory.getLogger(TourController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	private TourService tourService;

	private TourController(TourService service) {
		this.tourService = service;
	}
	
	@ApiOperation(value = "시도 리스트", notes = "시도 리스트를 반환한다", response = SidoDto.class)
	@GetMapping("/sido")
	public ResponseEntity<List<SidoDto>> getSidoList() {
		
		logger.debug("시도 리스트 불러오기");
		return new ResponseEntity<>(tourService.getSidoList(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "구군 리스트", notes = "시도에 해당하는 구군 리스트를 반환한다.", response = GugunDto.class)
	@GetMapping("/gugun")
	public ResponseEntity<List<GugunDto>> getGugunList(@RequestParam("sidoCode") @ApiParam("시도 코드") int sidoCode) {
		
		logger.debug("구군 정보 불러오기, 요청 시도 코드 {}",sidoCode);
		return new ResponseEntity<List<GugunDto>>(tourService.getGugunList(sidoCode), HttpStatus.OK);
	}

	@ApiOperation(value = "관광지 검색 리스트", notes = "관광지 검색 리스트를 좋아요 순서로 반환한다", response = TourDto.class)
	@GetMapping("/list")
	public ResponseEntity<List<TourDto>> getTourList(
			@RequestParam(value = "sidoCode") @ApiParam(value = "시도 코드 (없을 시 0 입력)") 
			int sidoCode,
			@RequestParam(value = "gugunCode") @ApiParam(value = "구군 코드 (없을 시 0 입력)") 
			int gugunCode,
			@RequestParam(value = "keyword", required = false) @ApiParam(value = "검색 키워드", required = false) 
			String keyword
	) {
		
		logger.debug("관광지 검색 => 시도 {} 구군 {} 키워드 {}",sidoCode, gugunCode, keyword);
		return new ResponseEntity<>(tourService.getTourList(sidoCode, gugunCode, keyword), HttpStatus.OK);
	}

	@ApiOperation(value = "관광지 상세정보", notes = "관광지 정보를 가져온다.", response = TourDto.class)
	@GetMapping("/detail")
	public ResponseEntity<TourDto> getDetail(
			@RequestParam("contentId") @ApiParam("관광지 코드") 
			int contentId, 
			@RequestParam(value = "userId", required = false) @ApiParam("유저 ID (로그인 시)") 
			String userId
	) {
		logger.debug("관광지 상세정보 보기");
		return new ResponseEntity<>(tourService.getTourDetail(contentId, userId), HttpStatus.OK);
	}
	
	@ApiOperation(value = "좋아요 갱신", notes = "좋아요 상태를 갱신한다. liked가 true일때는 좋아요를 눌렀을때, false일때는 좋아요를 취소했을 때이다."
			+ "\n만약 갱신이 성공하면 success를, 실패하면 fail을 전달한다.", response = String.class)
	@PutMapping("/like")
	public ResponseEntity<String> updateLike(
			@PathVariable("contentId") @ApiParam("관광지 ID")
			int contentId,
			@PathVariable("userId") @ApiParam("유저 ID")
			String userId,
			@PathVariable("liked") @ApiParam("좋아요를 눌렀을 때는 true, 취소할 때는 false")
			boolean isLiked
	) {
		
		logger.debug("좋아요 갱신");
		String msg = SUCCESS;
		HttpStatus status = HttpStatus.OK;
		try {
			if (isLiked) {
				if (tourService.createLike(contentId, userId) == 1) {
					logger.info("좋아요 성공");
				} else {
					logger.error("좋아요 실패");
					msg = FAIL;
				}
			} else {
				if(tourService.deleteLike(contentId, userId) == 1) {
					logger.info("좋아요 취소 성공");
				} else {
					logger.info("좋아요 취소 실패");
					msg = FAIL;
				}
			}
		} catch (Exception e) {
			logger.error("좋아요 에러 발생");
			msg = FAIL;
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(msg, status);
	}
	
}

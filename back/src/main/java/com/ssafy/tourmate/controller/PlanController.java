package com.ssafy.tourmate.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.tourmate.dto.LikeDto;
import com.ssafy.tourmate.dto.PlanDto;
import com.ssafy.tourmate.service.PlanService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/plan")
@CrossOrigin("*")
@Api("여행 계획 컨트롤러 API V1")
public class PlanController {
	
	private static final Logger logger = LoggerFactory.getLogger(PlanController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	private PlanService planService;
	
	private PlanController(PlanService service) {
		this.planService = service;
	}
	
	@ApiOperation(value = "여행 계획 리스트", notes = "여행 계획 리스트를 불러온다.", response = PlanDto.class)
	@GetMapping
	public ResponseEntity<List<PlanDto>> searchAllPlanList(
			@ApiParam(value = "여행 계획 제목" ,required = false)
			@RequestParam(value = "title", required=false) 
			String title,
			@ApiParam(value = "유저 ID", required = false)
			@RequestParam(value = "userId", required=false) 
			String userId,
			@ApiParam(value = "정렬 방법 (0 : 최신순, 1 : 좋아요 순, 2 : 조회수 순", required = true)
			@RequestParam(value = "sortType", required = true)
			int sortType
	) {
		logger.debug("여행 계획 리스트 불러오기, params : {}, {}, {}", title, userId, sortType);
		return new ResponseEntity<>(planService.searchPlans(title, userId, sortType), HttpStatus.OK);
	}
	
	@ApiOperation(value = "여행 계획 상세보기", notes = "여행 계획의 상세 정보를 불러온다", response = PlanDto.class)
	@GetMapping("/detail")
	public ResponseEntity<PlanDto> searchPlanDetail(
			@RequestParam("planId") @ApiParam("여행 계획 ID") int planId,
			@RequestParam(value = "userId", required = false) @ApiParam("유저 ID (로그인 했을 시)") String userId
	) {
		logger.debug("여행 계획 상세보기, Plan ID : {}, User ID : {}", planId, userId);
		return new ResponseEntity<PlanDto>(planService.searchPlan(planId, userId), HttpStatus.OK);
	}
	
	@ApiOperation(value = "여행 계획 등록", notes = "여행 계획을 등록한다, 성공시 success를, 실패시 fail을 전달한다", response = String.class)
	@PostMapping
	public ResponseEntity<String> registPlan(@RequestBody @ApiParam("여행 계획 DTO") PlanDto plan) {
		
		String msg = SUCCESS;
		logger.debug("여행 계획 등록");
		if (planService.createPlan(plan) == 0) {
			msg = FAIL;
			logger.error("여행 계획 등록 실패");
		} else {
			logger.info("여행 계획 등록 성공");
		}
		
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@ApiOperation(value = "여행 계획 수정", notes = "여행 계획을 수정한다, 성공시 success를, 실패시 fail을 전달한다", response = String.class)
	@PutMapping
	public ResponseEntity<String> modifyPlan(@RequestBody @ApiParam("여행 계획 DTO") PlanDto plan) {
		String msg = SUCCESS;
		logger.debug("여행 계획 수정");
		
		if (planService.modifyPlan(plan) == 0) {
			logger.error("여행 계획 수정 실패");
			msg = FAIL;
		} else {
			logger.info("여행 계획 수정 성공");
		}
		
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@ApiOperation(value = "여행 계획 삭제", notes = "여행 계획을 삭제한다. 성공시 success, 실패시 fail을 반환한다", response = String.class)
	@DeleteMapping("/{planId}")
	public ResponseEntity<String> removePlan(@PathVariable("planId") @ApiParam("플랜 Id") int planId) {
		String msg = SUCCESS;
		logger.debug("여행 계획 삭제");
		
		if (planService.removePlan(planId) == 0) {
			msg = FAIL;
			logger.error("여행 계획 삭제 실패");
		} else {
			logger.error("여행 계획 삭제 성공");
		}
		
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	
	@PutMapping("/like")
	public ResponseEntity<String> changePlanLike(
			@RequestBody @ApiParam("id : 플랜 id, userId : 유저 id") LikeDto dto
	) {
		String msg = "Success";
		int result = 0;
		

		if (planService.changePlanLike(dto.getId(), dto.getUserId()) == 0) {
			msg = "Fail";
		}
		
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	// JWT 적용시 userID를 헤더에서 가져옴
	@PutMapping("/bookmark")
	public ResponseEntity<String> changePlanBookmark(
			@RequestBody @ApiParam("id : 플랜 id, userId : 유저 id") LikeDto dto
	) {
		String msg = "Success";
		int result = 0;
		

		if (planService.changePlanBookmark(dto.getId(), dto.getUserId()) == 0) {
			msg = "Fail";
		}
		
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

}

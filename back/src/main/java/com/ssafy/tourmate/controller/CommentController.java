package com.ssafy.tourmate.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.tourmate.dto.CommentDto;
import com.ssafy.tourmate.service.CommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin("*")
@Api("댓글 컨트롤러 API V1")
public class CommentController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private CommentService commentService;

	@ApiOperation(value = "관광지 댓글 목록", notes = "관광지 댓글 목록을 불러온다", response = Map.class)
	@GetMapping("/tour/comment/{contentId}")
	public ResponseEntity<Map<String, Object>> getTourComment(@PathVariable("contentId") @ApiParam("관광지 ID") int contentId) {
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String msg = SUCCESS;
		logger.debug("관광지 댓글 목록 불러오기");
		
		try {
			List<CommentDto> list = commentService.getTourComment(contentId);
			resultMap.put("commentList", list);
			logger.info("관광지 댓글 목록 불러오기 성공");
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			msg = FAIL;
			logger.error("관광지 댓글 불러오기 실패, error : {}", e);
		}
		resultMap.put("message", msg);
		
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "관광지 댓글 작성", notes = "관광지 댓글을 작성하여 등록한다", response = Map.class)
	@PostMapping("/tour/comment")
	public ResponseEntity<Map<String, Object>> writeTourComment(@RequestBody @ApiParam("contentId : 관광지 ID, comment : 댓글 내용, writer : 작성자") CommentDto dto) {
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String msg = SUCCESS;
		logger.debug("관광지 댓글 작성");
		
		try {
			if (commentService.writeTourComment(dto) == 1) {
				logger.info("관광지 댓글 작성 성공");
			} else {
				logger.error("관광지 댓글 작성 실패");
				status = HttpStatus.ACCEPTED;
				msg = FAIL;
			}
		}catch (Exception e) {
			logger.error("관광지 댓글 작성 실패, error : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			msg = FAIL;
		}
		resultMap.put("message", msg);
		
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "관광지 댓글 삭제", notes = "관광지 댓글을 삭제한다", response = Map.class)
	@DeleteMapping("/tour/comment/{commentId}")
	public ResponseEntity<Map<String, Object>> removeTourComment(@PathVariable("commentId") @ApiParam("댓글 ID") int commentId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String msg = SUCCESS;
		logger.debug("관광지 댓글 삭제");
		
		try {
			if (commentService.removeTourComment(commentId) == 1) {
				logger.info("관광지 댓글 삭제 성공");
			} else {
				logger.error("관광지 댓글 삭제 실패");
				status = HttpStatus.ACCEPTED;
				msg = FAIL;
			}
		}catch (Exception e) {
			logger.error("관광지 댓글 삭제 실패, error : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			msg = FAIL;
		}
		resultMap.put("message", msg);
		
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}
	
	
	@ApiOperation(value = "플랜 댓글 목록", notes = "플랜 댓글 목록을 불러온다", response = Map.class)
	@GetMapping("/plan/comment/{planId}")
	public ResponseEntity<Map<String, Object>> getPlanComment(@PathVariable("planId") @ApiParam("플랜 ID") int planId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String msg = SUCCESS;
		logger.debug("플랜 댓글 목록 불러오기");
		
		try {
			List<CommentDto> list = commentService.getPlanComment(planId);
			resultMap.put("commentList", list);
			logger.info("플랜 댓글 목록 불러오기 성공");
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			msg = FAIL;
			logger.error("플랜 댓글 불러오기 실패, error : {}", e);
		}
		resultMap.put("message", msg);
		
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "플랜 댓글 작성", notes = "플랜 댓글을 작성하여 등록한다", response = Map.class)
	@PostMapping("/plan/comment")
	public ResponseEntity<Map<String, Object>> writePlanComment(@RequestBody @ApiParam("contentId : 플랜 ID, comment : 댓글 내용, writer : 작성자") CommentDto dto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String msg = SUCCESS;
		logger.debug("플랜 댓글 작성");
		
		try {
			if (commentService.writePlanComment(dto) == 1) {
				logger.info("플랜 댓글 작성 성공");
			} else {
				logger.error("플랜 댓글 작성 실패");
				status = HttpStatus.ACCEPTED;
				msg = FAIL;
			}
		}catch (Exception e) {
			logger.error("플랜 댓글 작성 실패, error : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			msg = FAIL;
		}
		resultMap.put("message", msg);
		
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "플랜 댓글 삭제", notes = "플랜 댓글을 삭제한다", response = Map.class)
	@DeleteMapping("/plan/comment/{commentId}")
	public ResponseEntity<Map<String, Object>> removePlanComment(@PathVariable("commentId") @ApiParam("댓글 ID") int commentId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String msg = SUCCESS;
		logger.debug("플랜 댓글 삭제");
		
		try {
			if (commentService.removePlanComment(commentId) == 1) {
				logger.info("플랜 댓글 삭제 성공");
			} else {
				logger.error("플랜 댓글 삭제 실패");
				status = HttpStatus.ACCEPTED;
				msg = FAIL;
			}
		}catch (Exception e) {
			logger.error("플랜 댓글 삭제 실패, error : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			msg = FAIL;
		}
		resultMap.put("message", msg);
		
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}
	
}

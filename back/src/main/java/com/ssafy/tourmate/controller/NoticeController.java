package com.ssafy.tourmate.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.tourmate.dto.NoticeDto;
import com.ssafy.tourmate.service.NoticeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/notice")
@CrossOrigin("*")
@Api("공지사항 컨트롤러 API V1")
public class NoticeController {
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private NoticeService noticeService;
	
	@ApiOperation(value = "공지사항 목록", notes = "검색 결과에 따라 공지사항 목록을 불러온다", response = NoticeDto.class)
	@GetMapping
	public ResponseEntity<List<NoticeDto>> getNoticeList(
			@ApiParam(required=false) 
			@RequestParam(value = "검색 키워드", required = false) 
			String keyword
	) {
		return new ResponseEntity<List<NoticeDto>>(noticeService.getNoticeList(keyword), HttpStatus.OK);
	}
	
	@ApiOperation(value = "공지사항 세부내용", notes = "공지사항의 내용을 불러온다.", response = NoticeDto.class)
	@GetMapping("/{noticeId}")
	public ResponseEntity<NoticeDto> getNotice(@PathVariable("noticeId") @ApiParam("공지사항 Id 번호") int noticeId) {
		return new ResponseEntity<NoticeDto>(noticeService.getNotice(noticeId), HttpStatus.OK);
	}
	
	@ApiOperation(value = "공지사항 작성", notes = "공지사항을 작성한다, 작성에 성공하면 success를, 실패하면 fail을 반환한다", response = String.class)
	@PostMapping("/admin")
	public ResponseEntity<String> writeNotice(@RequestBody @ApiParam("공지사항 내용") NoticeDto notice) {
		
		String msg = SUCCESS;
		
		int result = noticeService.writeNotice(notice);
		if (result == 0) {
			msg = FAIL;
		}
		
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@ApiOperation(value = "공지사항 수정", notes = "공지사항을 수정한다, 수정에 성공하면 success를, 실패하면 fail을 반환한다", response = String.class)
	@PutMapping("/admin")
	public ResponseEntity<String> modifyNotice(@RequestBody @ApiParam("공지사항 수정 내용") NoticeDto notice) {
		
		String msg = SUCCESS;
		
		int result = noticeService.modifyNotice(notice);
		if (result == 0) {
			msg = FAIL;
		}
		
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@ApiOperation(value = "공지사항 삭제", notes = "공지사항을 삭제한다, 삭제에 성공하면 success를, 실패하면 fail을 반환한다", response = String.class)
	@DeleteMapping("/admin/{noticeId}")
	public ResponseEntity<String> removeNotice(@PathVariable("noticeId") @ApiParam("공지사항 Id 번호") int noticeId) {
		
		String msg = SUCCESS;
		
		int result = noticeService.removeNotice(noticeId);
		if (result == 0) {
			msg = FAIL;
		}
		
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
}

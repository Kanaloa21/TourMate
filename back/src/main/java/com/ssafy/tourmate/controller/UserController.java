package com.ssafy.tourmate.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.ssafy.tourmate.dto.UserDto;
import com.ssafy.tourmate.service.JwtService;
import com.ssafy.tourmate.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@Api("유저 컨트롤러 API V1")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	private JwtService jwtService;
	private UserService userService;
	
	private UserController(UserService service, JwtService jwtService) {
		this.userService = service;
		this.jwtService = jwtService;
	}
	
	@ApiOperation(value="로그인", notes = "access-token, refresh-token, 회원 정보를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody 
			@ApiParam(value = "userId : 아이디, userPw : 비밀번호", required = true)
			UserDto userDto
	) {
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		logger.info("Login 실행");
		try {
			UserDto loginInfo = userService.login(userDto.getUserId(), userDto.getUserPw());
			if (loginInfo != null) {
				String accessToken = jwtService.createAccessToken(loginInfo);// key, data
				String refreshToken = jwtService.createRefreshToken(loginInfo);// key, data
				userService.saveRefreshToken(loginInfo.getUserId(), refreshToken);
				logger.info("로그인 성공!");
				logger.debug("로그인 accessToken 정보 : {}", accessToken);
				logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", SUCCESS);
				resultMap.put("loginInfo", loginInfo);
				status = HttpStatus.ACCEPTED;
			} else {
				logger.error("로그인 실패! 아이디나 비밀번호가 틀림");
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "토큰 검증", notes = "토큰 유효성 여부를 확인한다", response = Map.class)
	@GetMapping("/checkAuth")
	public ResponseEntity<Map<String, Object>> checkAuth(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		logger.info("토큰 검증 실행");

		if (jwtService.checkToken(request.getHeader("access-token"))) {
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} else {
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<>(resultMap, status);
	}
	
	@ApiOperation(value = "회원 정보 검색", notes = "회원 정보를 반환한다.", response = Map.class)
	@GetMapping("/{userId}")
	public ResponseEntity<Map<String, Object>> getUserInfo(
			@PathVariable("userId") 
			@ApiParam(value = "인증할 회원의 아이디.", required = true) 
			String userId
	) {
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		logger.debug("회원 정보 검색 실행");
		
		try {
			UserDto userInfo = userService.getUserInfo(userId);
			resultMap.put("userInfo", userInfo);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
			logger.info("회원 정보 조회 성공");
		} catch (Exception e) {
			logger.error("회원 정보 조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	@ApiOperation(value = "로그아웃", notes = "회원 정보를 담은 Token을 제거한다.", response = Map.class)
	@GetMapping("/logout/{userId}")
	public ResponseEntity<?> removeToken(@PathVariable("userId") @ApiParam(value = "로그아웃 할 유저 id") String userId) {
		HttpStatus status = HttpStatus.ACCEPTED;
		String msg = SUCCESS;
		logger.debug("로그아웃 실행");
		
		try {
			userService.deleteRefreshToken(userId);
			logger.info("로그아웃 토큰 제거 완료");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("로그아웃 실패 : {}", e);
			msg = FAIL;
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<String>(msg, status);

	}
	
	@ApiOperation(value = "refresh Token 재발급", notes = "refresh token을 이용해 새로운 access token과 refresh token을 재발급받는다.", response = Map.class)
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody @ApiParam("client에 저장된 userId") UserDto loginInfo, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refresh-token");
		logger.debug("access-token 재발급 실행");
		logger.debug("token : {}, userId : {}", token, loginInfo.getUserId());
		
		if (jwtService.checkToken(token) && token.equals(userService.getRefreshToken(loginInfo.getUserId()))) {
			UserDto userDto = userService.getUserInfo(loginInfo.getUserId());
			String accessToken = jwtService.createAccessToken(userDto);
			String refreshToken = jwtService.createRefreshToken(userDto);
			userService.saveRefreshToken(loginInfo.getUserId(), refreshToken);
			logger.debug("access-token : {}", accessToken);
			logger.debug("refresh-token : {}", refreshToken);
			logger.debug("정상적으로  token 재발급!!!");
			resultMap.put("access-token", accessToken);
			resultMap.put("refresh-token", refreshToken);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} else {
			logger.debug("refresh-token도 사용불가!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "회원 등록", notes = "신규 회원 정보를 등록한다, 등록에 성공하면 success를, 실패하면 fail을 반환한다.", response = String.class)
	@PostMapping("/regist")
	public ResponseEntity<String> registUser(@RequestBody @ApiParam("회원 등록 내용") UserDto userInfo) {
		
		logger.debug("회원 등록 실행");
		String msg = SUCCESS;
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			if (userService.registUser(userInfo) == 1) {
				logger.info("회원 등록 성공");
			} else {
				logger.error("회원 등록 실패");
				msg = FAIL;
			}
		} catch (Exception e) {
			logger.error("회원 등록 실패 : {}", e);
			msg = FAIL;
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<String>(msg, status);
	}
	
	@ApiOperation(value = "회원 수정", notes = "회원 정보를 수정한다, 수정에 성공하면 success를, 실패하면 fail을 반환한다.", response = String.class)
	@PutMapping("/modify")
	public ResponseEntity<String> modifyUserInfo(@RequestBody @ApiParam("회원 수정 내용") UserDto userInfo) {
		
		logger.debug("회원 수정 실행");
		String msg = SUCCESS;
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			
			if (userService.modifyUserInfo(userInfo) == 1) {
				logger.info("회원 수정 성공");
			} else {
				logger.error("회원 수정 실패");
				msg = FAIL;
			}
		} catch (Exception e) {
			logger.error("회원 수정 실패 : {}", e);
			msg = FAIL;
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<String>(msg, status);
	}
	

	@ApiOperation(value = "회원 삭제", notes = "회원을 삭제한다, 삭제에 성공하면 success를, 실패하면 fail을 반환한다.", response = String.class)
	@GetMapping("/remove/{userId}")
	public ResponseEntity<String> removeUser(@PathVariable("userId") @ApiParam("회원 Id") String userId) {
		
		logger.debug("회원 삭제 실행");
		String msg = SUCCESS;
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			if (userService.removeUser(userId) == 1) {
				logger.info("회원 삭제 성공");
			} else {
				logger.error("회원 삭제 실패");
				msg = FAIL;
			}
		} catch (Exception e) {
			logger.error("회원 삭제 실패 : {}", e);
			msg = FAIL;
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<String>(msg, status);
	}
	
	@ApiOperation(value = "아이디 중복 체크", notes = "계정 생성 시 아이디 중복 체크를 진행합니다", response = String.class)
	@GetMapping("/check")
	public ResponseEntity<String> checkUserIdDuple(@RequestParam("userId") @ApiParam("회원 Id") String userId) {
		
		String msg = SUCCESS;
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			if (userService.checkUserIdDuple(userId)) {
				logger.info("유저 아이디 중복 없음!");
			} else {
				logger.error("유저 아이디 중복됨");
				msg = FAIL;
			}
		} catch (Exception e) {
			logger.error("회원 중복 검사 실패 : {}", e);
			msg = FAIL;
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<String>(msg, status);
	}
	
}

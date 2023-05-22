package com.ssafy.tourmate.service;

import org.springframework.stereotype.Service;

import com.ssafy.tourmate.dto.UserDto;
import com.ssafy.tourmate.mapper.UserMapper;

@Service
public class UserService {
	
	private UserMapper userMapper;
	
	private UserService(UserMapper mapper) {
		this.userMapper = mapper;
	}

	public UserDto login(String id, String pw) {
		
		if (id == null || pw == null)  {
			return null;
		}
		
		return userMapper.login(id, pw);
	}

	public UserDto getUserInfo(String id) {
		return userMapper.getUser(id);
	}

	public int modifyUserInfo(UserDto userInfo) {
		return userMapper.updateUser(userInfo);
	}
	
	public int removeUser(String id) {
		return userMapper.deleteUser(id);
	}

	public int registUser(UserDto userInfo) {
		return userMapper.insertUser(userInfo);
	}

	public void saveRefreshToken(String userId, String refreshToken) {
		userMapper.saveRefreshToken(userId, refreshToken);
	}
	
	public String getRefreshToken(String userId) {
		return userMapper.getRefreshToken(userId);
	}
	
	public void deleteRefreshToken(String userId) {
		userMapper.deleteRefreshToken(userId, null);
	}
	
	public boolean checkUserIdDuple(String userId) {
		return userMapper.checkUserIdDuple(userId);
	}

}

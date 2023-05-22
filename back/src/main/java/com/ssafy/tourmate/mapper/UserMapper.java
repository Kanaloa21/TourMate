package com.ssafy.tourmate.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.tourmate.dto.UserDto;

@Mapper
public interface UserMapper {
	public UserDto login(String id, String pw);

	public UserDto getUser(String id);

	public int updateUser(UserDto userInfo);
	
	public int deleteUser(String id);

	public int insertUser(UserDto userInfo);

	public void saveRefreshToken(String userId, String refreshToken);
	
	public String getRefreshToken(String userId);
	
	public void deleteRefreshToken(String userId, String refreshToken);
	
	public boolean checkUserIdDuple(String userId);
}

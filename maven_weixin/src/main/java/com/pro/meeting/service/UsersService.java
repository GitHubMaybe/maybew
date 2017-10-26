package com.pro.meeting.service;
import java.util.List;

import com.pro.meeting.bean.Users;

public interface UsersService {
	public Users findByWidByOpenid(String openid);
	
	public void updateUserByEmail(String email,String wid);
	
	public Users findByEmail(String email);
	
	public List<Users> findAll();
	
	//根据ID查询数据
	public Users getUid(String uid);

	public void add(Users users);

	public void delete(String uid);

	public void update(Users users);
}

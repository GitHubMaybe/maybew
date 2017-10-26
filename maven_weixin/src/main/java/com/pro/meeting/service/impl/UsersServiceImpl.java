package com.pro.meeting.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pro.meeting.bean.Users;
import com.pro.meeting.service.UsersService;
import com.pro.meeting.springdata.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService {
	@Autowired
	UsersRepository URepository;

	@Override
	public Users findByWidByOpenid(String openid) {
		// TODO Auto-generated method stub
		return URepository.findByWidByOpenid(openid);
	}

	@Transactional
	@Override
	public void updateUserByEmail(String email, String wid) {
		// TODO Auto-generated method stub
		// WeiUser weiUser= weiUserServiceImpl.findByOpenid(openid);
		URepository.updateUserByEmail(email, wid);
	}

	@Override
	public Users findByEmail(String email) {
		// TODO Auto-generated method stub
		return URepository.findByEmail(email);
	}

	@Override
	public List<Users> findAll() {
		return URepository.findAll();
		// TODO Auto-generated method stub

	}

	@Override
	public void add(Users users) {
		// TODO Auto-generated method stub
		URepository.save(users);
	}

	@Override
	public void delete(String uid) {
		// TODO Auto-generated method stub
		URepository.delete(uid);
	}

	@Transactional
	@Override
	public void update(Users users) {
		// TODO Auto-generated method stub
		URepository.update(users.getUname()
				, users.getProvince()
				, users.getCity()
				, users.getTelphone()
				, users.getUid());
	}

	@Override
	public Users getUid(String uid) {
		// TODO Auto-generated method stub
		return URepository.findOne(uid);
	}
}

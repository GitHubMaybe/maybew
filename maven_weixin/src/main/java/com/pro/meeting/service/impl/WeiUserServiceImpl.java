package com.pro.meeting.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.meeting.bean.WeiUser;
import com.pro.meeting.springdata.WeiUserRepository;

@Service
public class WeiUserServiceImpl {
	@Autowired
	WeiUserRepository wRepository;

	public void addUser(WeiUser weiUser) {
		weiUser.setWid(UUID.randomUUID().toString());
		wRepository.save(weiUser);
	}

	public WeiUser findByOpenid(String openid) {
		return wRepository.findByOpenid(openid);
	}
}

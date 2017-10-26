package com.pro.meeting.springdata;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.meeting.bean.WeiUser;

public interface WeiUserRepository extends JpaRepository<WeiUser, String> {
	//根据openid获取实体对象
    public WeiUser findByOpenid(String openid);
}

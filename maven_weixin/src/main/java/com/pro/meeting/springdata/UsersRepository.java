package com.pro.meeting.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pro.meeting.bean.Users;

public interface UsersRepository extends JpaRepository<Users, String> {
    //根据openid 找到wid
	@Query("from Users where wid=(select wid from WeiUser where openid=?1)")
	public Users findByWidByOpenid(String openid);

	// 根据email更新
	@Modifying
	@Query("update Users set wid=:wid where email=:email")
	public void updateUserByEmail(@Param("email") String email, @Param("wid") String wid);

	// 根据email找到users
	public Users findByEmail(String email);
	
	@Modifying
	@Query("update Users set uname=?1,province=?2,city=?3,telphone=?4 where uid=?5")
	public int update(String uname,String province,String city,String telphone,String uid);
}

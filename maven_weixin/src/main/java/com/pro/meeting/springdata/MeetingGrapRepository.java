package com.pro.meeting.springdata;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.pro.meeting.bean.MeetingGrap;

public interface MeetingGrapRepository extends JpaRepository<MeetingGrap, String>  {

	public List<MeetingGrap> findByUid(String uid);

	//就选你
	/*
	    -- 就选你 通过 PID UID
		-- 匹配成功 的： 通过PID和UID进行修改该状态为 2
		-- 其它的 通过PID查询，且UID!=UID 将其状态改为1
		-- 同学思路好：
		-- 第一步：根据PID，将状态全部改 1   update meetinggrap set gstatus=1 where pid=?
		-- 第二步，根据PID和UID，将其状态改为2
	 */
	@Modifying
	@Query(value=" update meetinggrap set gstatus=1,gtime=?1  where pid=?2",nativeQuery=true)
	public int updateStatusByPid(Date gtime,String pid);
	
	@Modifying
	@Query(value="update meetinggrap set gstatus=2  where pid=?1 and uid=?2",nativeQuery=true)
    public int updateStatusByPidAndUid(String pid,String uid);
}

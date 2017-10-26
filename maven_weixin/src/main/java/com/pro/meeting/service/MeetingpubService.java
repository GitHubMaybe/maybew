package com.pro.meeting.service;

import java.util.List;

import com.pro.meeting.bean.MeetingPub;
import com.pro.meeting.bean.MeetingpubRank;

public interface MeetingpubService {

	public void save(MeetingPub meetingPub);

	// 发布的会议
	public List<MeetingPub> findMeetingpubListByUid(String uid);

	// 通过id 找到meetingpub
	public MeetingPub findById(String pid);

	// 发单排行榜
	public List<MeetingpubRank> findMeetingpubRank();

	// 根据类型查询列表数据
	public List<MeetingPub> findAllByGrapByPtype(String ptype,String uid);
	
	//我的抢单列表
	public  List<MeetingPub> findMeetingpubGrap(String uid);
	//选择讲者  去匹配抢单
	public List<MeetingpubRank> findGrapBypid(String pid);
}

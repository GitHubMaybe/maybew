package com.pro.meeting.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pro.meeting.bean.MeetingPub;
import com.pro.meeting.bean.MeetingpubRank;
import com.pro.meeting.service.MeetingpubService;
import com.pro.meeting.springdata.MeetingpubRepository;

@Service
public class MeetingpubServiceImpl implements MeetingpubService {
	@Autowired
	MeetingpubRepository meetingpubRepository;

	@Override
	public void save(MeetingPub meetingPub) {
		// TODO Auto-generated method stub
		meetingPub.setPid(UUID.randomUUID().toString());
		meetingPub.setCurrDate(new Date());
		// 生成会议编号
		String pubCode = meetingpubPcode(meetingPub.getPtime());
		meetingPub.setPcode(pubCode);
		meetingpubRepository.save(meetingPub);
	}

	public String meetingpubPcode(String ptime) {
		String resultStr = "";
		String strsub = ptime.substring(0, 10);

		strsub = strsub.replaceAll("-", "");

		String result = meetingpubRepository.findMaxPcode(strsub);

		if (!"".equals(result) && result != null) {
			resultStr = (Long.parseLong(result) + 1) + "";

		} else {
			resultStr = strsub + "001";
		}
		return resultStr;

	}

	// 通过uid获取发布的会议 并进行排序
	@Override
	public List<MeetingPub> findMeetingpubListByUid(String uid) {
		// TODO Auto-generated method stub
		return meetingpubRepository.findByUidOrderByPcodeDesc(uid);

	}

	@Override
	public MeetingPub findById(String pid) {
		// TODO Auto-generated method stub
		return meetingpubRepository.findOne(pid);
	}

	// 发单排行榜
	public List<MeetingpubRank> findMeetingpubRank() {

		List<Object[]> listobj = meetingpubRepository.findMeetingpubRank();
		List<MeetingpubRank> list = new ArrayList<MeetingpubRank>();
		for (int i = 0; i < listobj.size(); i++) {
			Object[] objects = listobj.get(i);
			MeetingpubRank mRank = new MeetingpubRank();
			mRank.setUid(objects[0] + "");
			mRank.setCount(objects[1] + "");
			mRank.setNickname(objects[2].toString());
			mRank.setHeadimgurl(objects[3].toString());
			list.add(mRank);
		}
		return list;

	}

	// 根据类型查询列表数据
	@Override
	public List<MeetingPub> findAllByGrapByPtype(String ptype, String uid) {

		if ("可抢单".equals(ptype)) {
			return meetingpubRepository.findAllByGrap(uid);
		} else {
			return meetingpubRepository.findAllByGrapByPtype(ptype, uid);
		}
	}

	// 我的抢单列表
	@Override
	public List<MeetingPub> findMeetingpubGrap(String uid) {
		// TODO Auto-generated method stub
		return meetingpubRepository.findMeetingpubGrap(uid);
	}
	
	//选择讲者  去匹配抢单
	public List<MeetingpubRank> findGrapBypid(String pid){
		List<Object[]> listObj=meetingpubRepository.findGrapBypid(pid);	
		List<MeetingpubRank> list=new ArrayList<MeetingpubRank>();
		for(int i=0;i<listObj.size();i++){
			Object[] objArr=listObj.get(i);
			MeetingpubRank mRank=new MeetingpubRank();
			
			mRank.setField1(objArr[0].toString());
			mRank.setField2(objArr[1].toString());
			mRank.setField3(objArr[2].toString());
			mRank.setField4(objArr[3].toString());
			mRank.setField5(objArr[4].toString());
			mRank.setField6(objArr[5].toString());
			list.add(mRank);
		}
		return list;
	}

}

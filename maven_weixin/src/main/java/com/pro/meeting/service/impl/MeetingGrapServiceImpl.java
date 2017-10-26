package com.pro.meeting.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pro.meeting.bean.MeetingGrap;
import com.pro.meeting.bean.MeetingPub;
import com.pro.meeting.service.MeetingGrapService;
import com.pro.meeting.springdata.MeetingGrapRepository;
import com.pro.meeting.springdata.MeetingpubRepository;

@Service
public class MeetingGrapServiceImpl implements MeetingGrapService {

	@Autowired
	MeetingGrapRepository meetingGrapRepository;
	@Autowired
	MeetingpubRepository meetingpubRepository;

	// 抢单列表添加
	public void add(MeetingGrap meetingGrap) {
		meetingGrap.setGid(UUID.randomUUID().toString());
		meetingGrap.setCurrDate(new Date());
		meetingGrap.setGstatus(0);
		MeetingPub meetingPub = meetingpubRepository.findOne(meetingGrap.getPid()) ;
		meetingGrap.setMeetingPub(meetingPub);
		meetingGrapRepository.save(meetingGrap);
	}

	
	// 抢单
	public MeetingGrap findAllMeetinggrap() {

		MeetingGrap meetingGrap = meetingGrapRepository.findOne("cf2ec76f-173e-4b06-b56a-fe16dd7bc601");
		return meetingGrap;
	}

	// 我的抢单
	public List<MeetingGrap> findByUid(String uid) {
		return meetingGrapRepository.findByUid(uid);

	}
	
	//就选你功能
		@Transactional
		public int updateGrapChoose(String pid,String uid) {
		    //第一步:
			int num1=meetingGrapRepository.updateStatusByPid(new Date(), pid);
			if(num1<1){
				//回滚事务
				System.out.println(1/0);
			}
			//第二步：
			int num2=meetingGrapRepository.updateStatusByPidAndUid(pid, uid);
			if(num2<1){
				//回滚事务
				System.out.println(1/0);
				throw new RuntimeException("第二步失败");
			}
			return 1;
		}

}

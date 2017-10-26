package com.pro.meeting.web.meeting;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pro.meeting.bean.MeetingGrap;
import com.pro.meeting.bean.MeetingPub;
import com.pro.meeting.bean.Users;
import com.pro.meeting.service.MeetingpubService;
import com.pro.meeting.service.UsersService;
import com.pro.meeting.service.impl.MeetingGrapServiceImpl;

@Controller
@RequestMapping("meetinggrap")
public class MeetingGrapController {
	@Autowired
	MeetingpubService meetingpubService;

	@Autowired
	UsersService usersService;
	
	@Autowired
	MeetingGrapServiceImpl mServiceImpl;
	//点击抢单按钮，进入 抢单添加页面
	@RequestMapping("addGrap")
	public String addMeetinggrap(@RequestParam("pid") String pid, @RequestParam("uid") String uid,
			Map<String, Object> map) {
		// 先获取发单表信息
		MeetingPub meetingPub = meetingpubService.findById(pid);
		map.put("meetingPub", meetingPub);
       
		// 发单的用户
		Users users = usersService.getUid(meetingPub.getUid());
		map.put("users", users);
		map.put("uid", uid);
		return "/pages/weixin/meetingGrap/grapInfo.jsp";

	}

	//抢单添加功能 
	@RequestMapping("addGrapTo")
	public String addMeetinggrapTo(MeetingGrap meetingGrap) {
		mServiceImpl.add(meetingGrap);
		return "/wx/isMeetingGrap";
	}

	
	// 我的抢单
	@ResponseBody
	@RequestMapping("findAllMeetinggrap")
	public MeetingGrap findAllMeetinggrap() {
		return mServiceImpl.findAllMeetinggrap();
	}

	//
	@ResponseBody
	@RequestMapping("findByUid")
	public List<MeetingGrap> findByUid(@RequestParam("uid") String uid) {
		List<MeetingGrap> list = mServiceImpl.findByUid(uid);
		return list;
	}
	
	//就选你
	@ResponseBody
	@RequestMapping("updateGrapChoose")
   public String updateGrapChoose(@RequestParam("pid")final String pid,@RequestParam("uid") final String uid){
	 int num=0;  
	  try{
		num=mServiceImpl.updateGrapChoose(pid, uid);
	  }catch(Exception e){
		 System.out.println(e.getMessage());
	  }
	 return num+"";
   }
}

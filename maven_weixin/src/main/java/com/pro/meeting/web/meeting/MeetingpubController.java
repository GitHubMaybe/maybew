package com.pro.meeting.web.meeting;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.pro.meeting.bean.MeetingPub;
import com.pro.meeting.bean.MeetingpubRank;
import com.pro.meeting.service.MeetingpubService;

@Controller
@RequestMapping("meetingpub")
public class MeetingpubController {
	@Autowired
	MeetingpubService mService;
  //添加会议
	@ResponseBody
	@RequestMapping("add")
	public String add(MeetingPub meetingPub) {
		mService.save(meetingPub);
		return "1";
	}
   //我发布的会议
	@ResponseBody
	@RequestMapping("findMeetingpub")
	public List<MeetingPub> findMeetingpubListByUid(@RequestParam("uid") String uid) {
		return mService.findMeetingpubListByUid(uid);
	}

	// 根据pid找到会议详情
	@RequestMapping("findMeetingBypid")
	public String findMeetingpubBypid(@RequestParam("pid") String pid, Map<String, Object> map) {
		MeetingPub meetingPub = mService.findById(pid);
		map.put("meetingPub", meetingPub);
		System.out.println(meetingPub);
		return "/pages/weixin/meetingpub/pubInfo.jsp";

	}
                                     
	// 可抢单的会议列表
	@ResponseBody
	@RequestMapping("findAllByGrap")
	public List<MeetingPub> findAllByGrap(@RequestParam("ptype") String ptype, @RequestParam("uid") String uid) {
		return mService.findAllByGrapByPtype(ptype, uid);

	}

	// 发单排行榜
	@RequestMapping("findRank")
	public String findRank(Map<String, Object> map) {
		List<MeetingpubRank> list = mService.findMeetingpubRank();
		map.put("list", list);
		return "/pages/weixin/meetingpub/rank.jsp";
	}
	//选择讲者 去决定匹配那个抢单
	@ResponseBody
	@RequestMapping("findGrapBypid")
	public List<MeetingpubRank> findGrapBypid(String pid){
		return 	mService.findGrapBypid(pid);
	}

}

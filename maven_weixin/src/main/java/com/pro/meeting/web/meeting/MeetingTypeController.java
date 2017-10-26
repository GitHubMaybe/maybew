package com.pro.meeting.web.meeting;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pro.meeting.bean.MeetingType;
import com.pro.meeting.service.MeetingTypeService;

@Controller
@RequestMapping("meetingtype")
public class MeetingTypeController {

	@Autowired
	MeetingTypeService mService;

	@ResponseBody
	@RequestMapping("findAll")
	public List<MeetingType> findAll() {
		return mService.findAllByStatus(1);
	}
}

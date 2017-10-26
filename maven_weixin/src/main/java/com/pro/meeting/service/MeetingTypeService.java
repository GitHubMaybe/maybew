package com.pro.meeting.service;

import java.util.List;

import com.pro.meeting.bean.MeetingType;

public interface MeetingTypeService {

	public List<MeetingType> findAllByStatus(Integer status);
	
}
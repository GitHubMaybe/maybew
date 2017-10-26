package com.pro.meeting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.meeting.bean.MeetingType;
import com.pro.meeting.service.MeetingTypeService;
import com.pro.meeting.springdata.MeetingTypeRepository;

@Service
public class MeetingTypeServiceImpl implements MeetingTypeService {
	@Autowired
	MeetingTypeRepository mRepository;

	@Override
	public List<MeetingType> findAllByStatus(Integer status) {
		// TODO Auto-generated method stub	
		return mRepository.findByStatusOrderByOrdemum(1);
	}

}

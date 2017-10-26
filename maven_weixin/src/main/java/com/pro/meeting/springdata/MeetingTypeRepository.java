package com.pro.meeting.springdata;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.meeting.bean.MeetingType;

public interface  MeetingTypeRepository extends JpaRepository<MeetingType, String>{
  
	
	public  List<MeetingType> findByStatusOrderByOrdemum(Integer status);
		
	
}

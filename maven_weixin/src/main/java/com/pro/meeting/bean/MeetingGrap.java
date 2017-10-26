package com.pro.meeting.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;






@Table
@Entity
public class MeetingGrap {
	@Id
	private String gid;
	private String gremark;// 详情描述
	private Date currDate;// 发布时间
	
	@Transient
	private String pid;// 发单ID
	private int gstatus;// 抢单状态
	private Date gtime;// 匹配时间
	private String uid;// 关联用户

	@ManyToOne
	@JoinColumn(name="pid")
	private MeetingPub meetingPub;

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getGremark() {
		return gremark;
	}

	public void setGremark(String gremark) {
		this.gremark = gremark;
	}

	public Date getCurrDate() {
		return currDate;
	}

	public void setCurrDate(Date currDate) {
		this.currDate = currDate;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public int getGstatus() {
		return gstatus;
	}

	public void setGstatus(int gstatus) {
		this.gstatus = gstatus;
	}

	public Date getGtime() {
		return gtime;
	}

	public void setGtime(Date gtime) {
		this.gtime = gtime;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public MeetingPub getMeetingPub() {
		return meetingPub;
	}

	public void setMeetingPub(MeetingPub meetingPub) {
		this.meetingPub = meetingPub;
	}
	
	

}

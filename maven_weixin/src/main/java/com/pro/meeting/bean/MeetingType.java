package com.pro.meeting.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class MeetingType {
	@Id
	private String tid;
	private String tname;// 主题名称
	private int status;// 状态
	private Date currDate;// 插入时间
	private int ordemum;// 排序

	public MeetingType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MeetingType(String tid, String tname, int status, Date currDate, int ordemum) {
		super();
		this.tid = tid;
		this.tname = tname;
		this.status = status;
		this.currDate = currDate;
		this.ordemum = ordemum;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCurrDate() {
		return currDate;
	}

	public void setCurrDate(Date currDate) {
		this.currDate = currDate;
	}

	public int getOrdemum() {
		return ordemum;
	}

	public void setOrdemum(int ordemum) {
		this.ordemum = ordemum;
	}

	@Override
	public String toString() {
		return "MeetingType [tid=" + tid + ", tname=" + tname + ", status=" + status + ", currDate=" + currDate
				+ ", ordemum=" + ordemum + "]";
	}

}

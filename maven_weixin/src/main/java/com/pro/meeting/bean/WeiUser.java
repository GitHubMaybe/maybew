package com.pro.meeting.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class WeiUser {
	@Id
	private String wid;
	private String openid;
	private String nickname;// 昵称
	private String sex;
	private String language;
	private String country;// 国家
	private String province;// 省份
	private String city;// 城市
	private String headimgurl;// 头像
	private String subscribe;// 关注状态

	public WeiUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WeiUser(String wid, String openid, String nickname, String sex, String language, String country,
			String province, String city, String headimgurl, String subscribe) {
		super();
		this.wid = wid;
		this.openid = openid;
		this.nickname = nickname;
		this.sex = sex;
		this.language = language;
		this.country = country;
		this.province = province;
		this.city = city;
		this.headimgurl = headimgurl;
		this.subscribe = subscribe;
	}

	public String getWid() {
		return wid;
	}

	public void setWid(String wid) {
		this.wid = wid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}

	@Override
	public String toString() {
		return "WeiUser [wid=" + wid + ", openid=" + openid + ", nickname=" + nickname + ", sex=" + sex + ", language="
				+ language + ", country=" + country + ", province=" + province + ", city=" + city + ", headimgurl="
				+ headimgurl + ", subscribe=" + subscribe + "]";
	}
}

package com.pro.weixin.api.userinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.meeting.bean.WeiUser;
import com.pro.meeting.service.impl.WeiUserServiceImpl;
import com.pro.weixin.api.accessToken.AccessTokenThread;
import com.pro.weixin.util.WeixinUtil;

import net.sf.json.JSONObject;

@Service
public class UserInfoApi {
	@Autowired
	WeiUserServiceImpl wImpl;

	public void WeiUserInfo(String openid) {
		// 获取json对象
		JSONObject jsonObject = getUserInfo(openid);
		WeiUser wUser2 = wImpl.findByOpenid(openid);
		if (wUser2 != null) {
		} else {
			// 把JSONObject解析成WeiUser 插入数据
			WeiUser weiUser = new WeiUser();
			weiUser.setCity(jsonObject.getString("city"));
			weiUser.setCountry(jsonObject.getString("country"));
			weiUser.setHeadimgurl(jsonObject.getString("headimgurl"));
			weiUser.setLanguage(jsonObject.getString("language"));
			weiUser.setNickname(jsonObject.getString("nickname"));
			weiUser.setOpenid(jsonObject.getString("openid"));
			weiUser.setProvince(jsonObject.getString("province"));
			weiUser.setSex(jsonObject.getString("sex"));
			weiUser.setSubscribe(jsonObject.getString("subscribe"));
			wImpl.addUser(weiUser);
		}
	}
	// 微信公众号接口
	// 获取用户基本信息（包括UnionID机制）

	// 通过微信API返回JSONObject
	public  JSONObject getUserInfo(String openid) {
		 String USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+AccessTokenThread.accessToken_s+"&openid="+openid+"&lang=zh_CN ";
		JSONObject jsonObject = WeixinUtil.httpRequest(USER_INFO_URL, "GET", null);
		System.out.println(jsonObject);
		return jsonObject;
	}
}

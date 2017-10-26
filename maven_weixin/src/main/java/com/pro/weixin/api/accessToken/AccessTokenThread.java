package com.pro.weixin.api.accessToken;

import com.pro.weixin.main.MenuManager;
import com.pro.weixin.pojo.AccessToken;
import com.pro.weixin.util.WeixinUtil;

import net.sf.json.JSONObject;

public class AccessTokenThread extends Thread {
	// 让外部直接来访问
	public static String accessToken_s;

	@Override
	public void run() {
		while (true) {
			System.out.println("线程启动了");
			AccessToken accessTokenObj = getAccessToken(MenuManager.appId, MenuManager.appSecret);
			accessToken_s = accessTokenObj.getToken();
			try {
				Thread.sleep(3600*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 获取access_token的接口地址（GET） 限200（次/天）
	public final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	/**
	 * 获取access_token 
	 * @param appid 凭证
	 * @param appsecret 密钥
	 * @return
	 */
	public static AccessToken getAccessToken(String appid, String appsecret) {
		AccessToken accessToken = null;

		String requestUrl = ACCESS_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret);
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);
		System.out.println("获取凭证：" + jsonObject.toString());
		// 如果请求成功
		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
				// System.out.println(accessToken.getExpiresIn());
			} catch (Exception e) {
				accessToken = null;
				// 获取token失败
				e.printStackTrace();
			}
		}
		return accessToken;
	}
}

package com.pro.meeting.web.weixin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.pro.meeting.bean.Users;
import com.pro.meeting.service.UsersService;
import com.pro.weixin.main.MenuManager;
import com.pro.weixin.util.WeixinUtil;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("wx")
public class WeiXinController {
	@Autowired
	UsersService usersService;

	@RequestMapping("isAuthLogin")
	public void isAuthLogin(HttpServletResponse response) throws IOException {
		// 用户同意授权 获取code
		String pathUrl = MenuManager.REAL_URL + "wx/isAuthLoginInvoke";
		try {
			pathUrl = URLEncoder.encode(pathUrl, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" + "appid=" + MenuManager.appId
				+ "&redirect_uri=" + pathUrl + "&response_type=code" + "&scope=snsapi_base"
				+ "&state=java1111#wechat_redirect";
		response.sendRedirect(url);
	}

	@RequestMapping("isAuthLoginInvoke")
	public String isAuthLoginInvoke(HttpServletRequest request) {
		// 第二步：通过code换取网页授权access_token
		String code = request.getParameter("code");
		// 获取code后，请求以下链接获取access_token
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" + "appid=" + MenuManager.appId + "&secret="
				+ MenuManager.appSecret + "&code=" + code + "&grant_type=authorization_code";

		JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", null);

		String openid = jsonObject.getString("openid");
		request.setAttribute("openid", openid);
		Users users = usersService.findByWidByOpenid(openid);

		if (users != null) {
			request.setAttribute("users", users);
			return "/pages/weixin/users/userupdate.jsp";
		}
		return "/pages/weixin/users/login.jsp";
	}

	// 会议抢单
	@RequestMapping("isMeetingGrap")
	public void isMeetingGrap(HttpServletResponse response) throws IOException {
		// 用户同意授权 获取code
		String pathUrl = MenuManager.REAL_URL + "wx/isMeetingGrapInvoke";
		try {
			pathUrl = URLEncoder.encode(pathUrl, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" + "appid=" + MenuManager.appId
				+ "&redirect_uri=" + pathUrl + "&response_type=code" + "&scope=snsapi_base"
				+ "&state=java1111#wechat_redirect";
		response.sendRedirect(url);
	}

	@RequestMapping("isMeetingGrapInvoke")
	public String isMeetingGrapInvoke(HttpServletRequest request) {
		// 第二步：通过code换取网页授权access_token
		String code = request.getParameter("code");
		// 获取code后，请求以下链接获取access_token
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" + "appid=" + MenuManager.appId + "&secret="
				+ MenuManager.appSecret + "&code=" + code + "&grant_type=authorization_code";

		JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", null);
		String openid = jsonObject.getString("openid");
		request.setAttribute("openid", openid);
		Users users = usersService.findByWidByOpenid(openid);
		if (users != null) {
			return "/pages/weixin/meetingGrap/grap.jsp?roleId="+users.getRolesName()+"&uid="+users.getUid();
		}
		return "/pages/weixin/users/login.jsp";

	}

}

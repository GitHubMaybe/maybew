package com.pro.weixin.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pro.weixin.pojo.AccessToken;
import com.pro.weixin.pojo.Button;
import com.pro.weixin.pojo.CommonButton;
import com.pro.weixin.pojo.ComplexButton;
import com.pro.weixin.pojo.Menu;
import com.pro.weixin.pojo.ViewButton;
import com.pro.weixin.util.WeixinUtil;

/**
 * 菜单管理器类
 * 
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);
	/***
	 * 自定义菜单的创建步骤 1、找到AppId和AppSecret。自定义菜单申请成功后，在“高级功能”-“开发模式”-“接口配置信息”的最后两项就是；
	 * 2、根据AppId和AppSecret，以https get方式获取访问特殊接口所必须的凭证access_token；
	 * 3、根据access_token，将json格式的菜单数据通过https post方式提交。
	 */
	public final static String REAL_URL = "http://17948j0g00.iok.la/maven_weixin/"; // 个人花生壳
	// public final static String REAL_URL =
	// "http://wxmobsa.yidatec.com/weixin/"; //正式号服务器

	public final static String appId = "wx73a038b4f823bd1e";
	public final static String appSecret = "4445502a1d4b92df6b595d7696e07346";

	public static void resultMenu() {
		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
		if (null != at) {
			// 调用接口创建菜单
			int result = WeixinUtil.createMenu(getMenu(), at.getToken());

			// 判断菜单创建结果
			if (0 == result)
				log.info("菜单创建成功！");
			else
				log.info("菜单创建失败，错误码：" + result);
		}
	}

	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = MenuManager.appId;
		// 第三方用户唯一凭证密钥
		String appSecret = MenuManager.appSecret;
		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
		if (null != at) {
			// 调用接口创建菜单
			int result = WeixinUtil.createMenu(getMenu(), at.getToken());

			// 判断菜单创建结果
			if (0 == result)
				log.info("菜单创建成功！");
			else
				log.info("菜单创建失败，错误码：" + result);
		}
	}

	/**
	 * 组装菜单数据
	 * @return
	 */
	private static Menu getMenu() {
		CommonButton btn50 = new CommonButton();
		btn50.setName("会议发布");
		btn50.setType("click");
		btn50.setKey("MEETING_PUB");

		ViewButton btn51 = new ViewButton();
		btn51.setName("会议抢单");
		btn51.setType("view");
		btn51.setUrl(REAL_URL + "wx/isMeetingGrap");
		// -------------------------------------------------------

		CommonButton btn61 = new CommonButton(); // 返回图文消息
		btn61.setName("昨日日报");
		btn61.setType("click");
		btn61.setKey("61");
		
		ViewButton btn62 = new ViewButton();
		btn62.setName("发单排行榜");
		btn62.setType("view");
		btn62.setUrl(REAL_URL + "meetingpub/findRank");
		// ------------------------------------------------------------
		CommonButton btn70 = new CommonButton(); // 返回图文消息
		btn70.setName("联系我们");
		btn70.setType("click");
		btn70.setKey("70");

		CommonButton btn71 = new CommonButton(); // 返回图文消息
		btn71.setName("版本信息");
		btn71.setType("click");
		btn71.setKey("71");

		ViewButton btn72 = new ViewButton();
		btn72.setName("个人中心");
		btn72.setType("view");
		btn72.setUrl(REAL_URL + "wx/isAuthLogin");

		// ###############################################一级子菜单
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("会议菜单");
		mainBtn1.setSub_button(new Button[] { btn50, btn51 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("公告板"); //
		mainBtn2.setSub_button(new Button[] { btn61, btn62 });

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("系统消息");// btn31, btn32, btn33,
		mainBtn3.setSub_button(new Button[] {btn72, btn70, btn71 });

		/**
		 * 这是公众号目前的菜单结构，每个一级菜单都有二级菜单项<br>
		 * 
		 * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });
		return menu;
	}
}

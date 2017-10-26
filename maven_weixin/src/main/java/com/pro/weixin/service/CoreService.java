package com.pro.weixin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pro.weixin.main.MenuManager;
import com.pro.weixin.util.MessageUtil;
import com.pro.meeting.bean.Users;
import com.pro.meeting.service.UsersService;
import com.pro.weixin.api.userinfo.UserInfoApi;
import com.pro.weixin.bean.resp.Article;
import com.pro.weixin.bean.resp.NewsMessage;
import com.pro.weixin.bean.resp.TextMessage;

@Service
public class CoreService {
   @Autowired
   UserInfoApi userInfoApi;
	@Autowired
	UsersService usersService;
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";

			// xml请求解析 调用消息工具类MessageUtil解析微信发来的xml格式的消息，解析的结果放在HashMap里；
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id） 下面三行代码是： 从HashMap中取出消息中的字段；
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 回复文本消息 组装要返回的文本消息对象；
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			// 由于href属性值必须用双引号引起，这与字符串本身的双引号冲突，所以要转义
			// textMessage.setContent("欢迎访问<a
			// href=\"http://www.baidu.com/index.php?tn=site888_pg\">百度</a>!");
			// 将文本消息对象转换成xml字符串
			respMessage = MessageUtil.textMessageToXml(textMessage);
			/**
			 * 演示了如何接收微信发送的各类型的消息，根据MsgType判断属于哪种类型的消息；
			 */

//			//得到openid 获取用户相关信息
//			 userInfoApi.WeiUserInfo(fromUserName);
			
			// 接收用户发送的文本消息内容
			String content = requestMap.get("Content");
			// System.out.println("用户消息:"+content);

			// 创建图文消息
			NewsMessage newsMessage = new NewsMessage();
			newsMessage.setToUserName(fromUserName);
			newsMessage.setFromUserName(toUserName);
			newsMessage.setCreateTime(new Date().getTime());
			newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
			newsMessage.setFuncFlag(0);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				respContent = "别发了  我不认识你";
				//respContent = "您发送的是文本消息！";
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					//得到openid 获取用户相关信息
					 userInfoApi.WeiUserInfo(fromUserName);
					 respContent = "欢迎欢迎 热烈欢迎";
					//respContent = "欢迎关注会议达人服务号，在这里你可以根据权限完成会议发布、参与抢单等功能";
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					String eventKey = requestMap.get("EventKey");

					if (eventKey.equals("MEETING_PUB")) {
						List<Article> articleList = new ArrayList<Article>();	
						//1验证用户是否绑定（认证） 根据OPEND查询 得到wid，根据wid查Users
					Users users=usersService.findByWidByOpenid(fromUserName);
					// 单图文消息
					Article article = new Article();
					if(users==null){ //未登录
						article.setTitle("您还未登录");
						article.setDescription("您好，您还没有进行登录绑定，暂无权限操作");
						article.setPicUrl(
								"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502703265970&di=e9eb676c01bac824615dc23bc319129e&imgtype=0&src=http%3A%2F%2Fupload.gezila.com%2Fdata%2F20160518%2F5651463541784.jpg");
						article.setUrl(MenuManager.REAL_URL+"pages/weixin/users/login.jsp");
					}else{//已登录
						//抢单组
						if("2".equals(users.getRolesName())){
							article.setTitle("权限不够 ");
							article.setDescription("您好，权限不够无法操作");
							article.setPicUrl(
									"http://image.so.com/v?q=微笑图片大全&cmsid=df38a0a4b61c1e061cfa9157a3dc30a0&cmran=0&cmras=0&i=1&cmg=62a0086e2a441764e5ce2faf3316adb9&src=360pic_strong&z=1#q=%E5%BE%AE%E7%AC%91%E5%9B%BE%E7%89%87%E5%A4%A7%E5%85%A8&i=1&src=360pic_strong&z=1&lightboxindex=0&id=fcd159f73586c0ac459f28d7035b20e2&multiple=0&itemindex=0&dataindex=0&prevsn=0&currsn=0&jdx=0");
							article.setUrl("https://www.baidu.com/");
						//发单组	
						}else if("1".equals(users.getRolesName())){
							article.setTitle("欢迎您，"+users.getUname());
							article.setDescription("开始你的表演");
							article.setPicUrl(
									"http://img-cdn.35pic.com/54/42/99/49B8B56C50.jpg!w1024/crop/0x0s30s30");
							article.setUrl(MenuManager.REAL_URL+"pages/weixin/meetingpub/pub.jsp?uid="+users.getUid());	
						}
					}
					
						articleList.add(article);
						// 设置图文消息个数
						newsMessage.setArticleCount(articleList.size());
						// 设置图文消息
						newsMessage.setArticles(articleList);
						// 将图文消息对象转换为XML字符串
						respMessage = MessageUtil.newsMessageToXml(newsMessage);
						return respMessage;
					}
					
					else if (eventKey.equals("11")) {
						respContent = "天气预报菜单项被点击！";

					}
					// 版本信息
					else if (eventKey.equals("72")) {
						respContent = "版本信息：\n" + "1.1增加了用户登录功能\n" + "1.2增加了用户修改功能\n" + "1.3增加了使用规则功能\n";
					}
					// 联系我们
					else if (eventKey.equals("73")) {
						respContent = "如有疑问：\n" + "请联系官方电话\n" + "电话：10086\t\n";
					}
					// 使用规则 70
					/*
					 * 根据openid查询 Users表
					 * 如果Users表为空，==》返回一个图文消息(请点击图文进行页面）=》跳到登录页面
					 */
					else if (eventKey.equals("70")) {

						List<Article> articleList = new ArrayList<Article>();
						// 根据openid查询Users表

						// 单图文消息
						Article article = new Article();
						article.setTitle("JAVA1702使用规则");
						article.setDescription("要遵守法律法规则 、图文标题的描述信息....");
						article.setPicUrl(
								"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png");
						article.setUrl("https://www.baidu.com/");

						articleList.add(article);

						// 设置图文消息个数
						newsMessage.setArticleCount(articleList.size());
						// 设置图文消息
						newsMessage.setArticles(articleList);
						// 将图文消息对象转换为XML字符串
						respMessage = MessageUtil.newsMessageToXml(newsMessage);
						return respMessage;
					} else if (eventKey.equals("14")) {
						List<Article> articleList = new ArrayList<Article>();
						// 单图文消息
						Article article = new Article();
						article.setTitle("JAVA1702图文标题");
						article.setDescription("java1702图文标题的描述信息");
						article.setPicUrl(
								"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png");
						article.setUrl("https://www.baidu.com/");

						articleList.add(article);
						// 设置图文消息个数
						newsMessage.setArticleCount(articleList.size());
						// 设置图文消息
						newsMessage.setArticles(articleList);
						// 将图文消息对象转换为XML字符串
						respMessage = MessageUtil.newsMessageToXml(newsMessage);
						return respMessage;
					}
					// 返回多图文，其中第二个是跳转到一个登录页面
					else if (eventKey.equals("15")) {
						List<Article> articleList = new ArrayList<Article>();
						// 单图文消息
						Article article = new Article();
						article.setTitle("JAVA1702图文标题");
						article.setDescription("java1702图文标题的描述信息");
						article.setPicUrl(
								"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png");
						article.setUrl("https://www.baidu.com/");

						// 第二个图文消息
						Article a1 = new Article();
						a1.setTitle("用户登录");
						a1.setDescription("本平台，用户登录人员都需经过邮箱正规验证，否则无法登录..");
						a1.setPicUrl(MenuManager.REAL_URL + "images/car.jpg");
						a1.setUrl(MenuManager.REAL_URL + "pages/users/login.jsp");// 跳登录页面

						articleList.add(article);
						articleList.add(a1);
						// 设置图文消息个数
						newsMessage.setArticleCount(articleList.size());
						// 设置图文消息
						newsMessage.setArticles(articleList);
						// 将图文消息对象转换为XML字符串
						respMessage = MessageUtil.newsMessageToXml(newsMessage);
						return respMessage;

					} else if (eventKey.equals("21")) {
						respContent = "每天十辐图功能正在研发中...请稍侯";
					} else if (eventKey.equals("22")) {
						respContent = "四六级功能正在研发中...请稍侯";
					}
				}
			}

			// 组装要返回的文本消息对象；
			textMessage.setContent(respContent);
			// 调用消息工具类MessageUtil将要返回的文本消息对象TextMessage转化成xml格式的字符串；
			respMessage = MessageUtil.textMessageToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respMessage;
	}
}

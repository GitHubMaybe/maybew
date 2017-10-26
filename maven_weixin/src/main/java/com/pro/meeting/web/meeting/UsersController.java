package com.pro.meeting.web.meeting;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.pro.meeting.bean.Users;
import com.pro.meeting.bean.WeiUser;
import com.pro.meeting.service.UsersService;
import com.pro.meeting.service.impl.WeiUserServiceImpl;

@Controller
@RequestMapping("users")
public class UsersController {

	@Autowired
	UsersService usersService;

	@Autowired
	WeiUserServiceImpl wImpl;

	@ResponseBody
	@RequestMapping("login")
	public String login(final String email, String openid) {
		WeiUser weiUser = wImpl.findByOpenid(openid);
		System.out.println("---------------------------" + weiUser);
		if (weiUser == null) {
			return "0";
		}
		Users users = usersService.findByEmail(email);
		if (users == null) {
			return "2";
		}
		if (users.getWid() != null && !"".equals(users.getWid())) {
			return "3";
		}
		usersService.updateUserByEmail(email, weiUser.getWid());
		return "1";
	}

	@RequestMapping("findAll")
	public String findAll(Map<String, Object> map) {
		List<Users> list = usersService.findAll();
		map.put("list", list);
		return "/pages/weixin/users/userlist.jsp";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(Users users) {
		users.setUid(UUID.randomUUID().toString());
		usersService.add(users);
		return "redirect:/pages/weixin/users/userlist.jsp";
	}

	@RequestMapping(value = "{uid}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("uid") String uid) {
		usersService.delete(uid);
		return "redirect:/users/findAll";
	}

	@ResponseBody
	@RequestMapping("update")
	public String update(Users users) {
		usersService.update(users);
		return "1";
	}
	
	// @RequestMapping(value = "update", method = RequestMethod.PUT)
	// public String update(Users users) {
	// usersService.update(users);
	// return "redirect:/users/findAll";
	// }
	
}

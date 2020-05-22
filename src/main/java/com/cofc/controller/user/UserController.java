package com.cofc.controller.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cofc.pojo.BackUser;
import com.cofc.pojo.video.UserBean;
import com.cofc.service.video.UserBeanService;
import com.cofc.util.BaseUtil;
import com.cofc.util.JsonUtil;

/**
 * 后台用户相关接口
 * 
 * @author chen
 *
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseUtil {
	@Resource
	private UserBeanService userService;

	public static Logger log = Logger.getLogger("UserController");

	// 跳转后台用户列表
	@RequestMapping("/toUserList")
	public ModelAndView toShowUserList(ModelAndView modelView, HttpServletRequest request) throws ParseException {
		BackUser bu = (BackUser) request.getSession().getAttribute("loginer");

		log.info("进入后台用户列表的jsp页面");
		// List<UserCommon> userList = usercService.getUserIdList();
		modelView.addObject("loginPlat", bu.getLoginPlat());// 传给前台模板：空值显示百享园，不空显示相对应的
		modelView.addObject("appList", null);
		// modelView.addObject("userList", userList);
		modelView.setViewName("user/userList");
		return modelView;
	}

	// 跳转后台管理员列表
	@RequestMapping("/toManagerList")
	public ModelAndView toShowManagerList(ModelAndView modelView) throws ParseException {
		log.info("进入后台管理员列表的jsp页面");
		modelView.setViewName("userManage/managerList");
		return modelView;
	}

	// $.ajax查询用户列表
	@RequestMapping("/showUserList")
	public void showUserList(HttpServletRequest request, HttpServletResponse response, Integer page, Integer limit)
			throws ParseException {
		System.out.println("page:" + page + "|limit:" + limit);
		List<UserBean> userList = userService.getUsers(page, limit);
		int rowsCount = 100;
		output(response, JsonUtil.buildJsonByTotalCount(userList, rowsCount));
	}

	@RequestMapping("/userDetails")
	public ModelAndView showUserDetails(HttpServletRequest request, ModelAndView modelView, Integer userId,
			Integer pageNo) {

		UserBean user = userService.getUserByUserId(userId);
		// log.info("积分" + user.getIntegral());
		// UserCommon currUser = usercService.getUserByUserId(userId);
		String imgHtml = "";
//			if (user.get != null) {
//				String[] imgArrray = user.getCardImage().split(",");
//				for (String img : imgArrray) {
//					imgHtml += "<img src='" + img + "' style='width:640px;height:320px;'/>";
//				}
//				if (imgHtml != "") {
//					imgHtml += "<div style='font-size:30px;margin-top:5px;width:640px'><a href='../download.do?files="
//							+ user.getCardImage() + "&nickName=" + user.getNickName() + "&realName="
//							+ user.getRealName() + "' style='text-decoration:underline'>全部下载</a></div>";
//				}
//			}
		modelView.setViewName("userManage/userDetails");
		modelView.addObject("currUser", user);
		modelView.addObject("page", pageNo);
		modelView.addObject("imgHtml", imgHtml);
		return modelView;
	}

}

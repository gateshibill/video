package com.cofc.intercepter;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cofc.pojo.BackUser;
import com.cofc.pojo.video.UserBean;
import com.cofc.util.JsonUtil;

//import com.cofc.pojo.BackUser;
public class LoginHandlerIntercepter implements HandlerInterceptor {
	private static Logger logger = Logger.getLogger("LoginHandlerIntercepter");
	private static Map<String, String> userTokenMap = new HashMap<String, String>();

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// 说明处在编辑的页面
		BackUser currUser = (BackUser) request.getSession().getAttribute("loginer");
		String userAgent = request.getHeader("user-agent");
		System.out.println("request.getHeader(\"user-agent\"):" + request.getHeader("user-agent"));

		if (userAgent.contains("dart:io")) {
			String token = request.getParameter("token");
			System.out.println("token:" + token);
			return true;
//			if (validate(token)) {
//				// 登陆成功的用户
//				System.out.println("logined");
//				return true;
//			} else {
//				// 没有登陆，转向登陆界面
//				// request.getRequestDispatcher("/login.jsp").forward(request, response);
//				System.out.println("unlogin");
//				output(response, JsonUtil.buildSuccessJson("405", "no right"));
//				return false;
//			}
		} else {
			if (currUser != null) {
				// 登陆成功的用户
				return true;
			} else {
				// 没有登陆，转向登陆界面
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				return false;
			}
		}
	}

	protected void output(HttpServletResponse response, String jsonResult) {
		try {
			response.setContentType("application/json; charset=UTF-8");
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
			response.setHeader("Access-Control-Allow-Credentials", "true");
			response.setContentLength(jsonResult.getBytes("UTF-8").length);
			logger.debug("response json: " + jsonResult);
			// response.getOutputStream().write(jsonResult.getBytes());
			// printStream.println(result);
			PrintWriter printWriter = response.getWriter();
			printWriter.write(jsonResult);
			printWriter.flush();
			// response.getOutputStream().
		} catch (Exception e) {
			logger.error("Error output json data to the client!!!orginal json=" + jsonResult, e);
		}
	}

	public static void putUserToken(String key, String value) {
		userTokenMap.put(key, value);
	}

	public static void getUserToken(String key) {
		userTokenMap.get(key);
	}

	public static Boolean validate(String token) {
		return userTokenMap.values().contains(token);
	}
}

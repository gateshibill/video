package com.cofc.controller.admin;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.cofc.pojo.BackUser;
import com.cofc.pojo.video.Column;
import com.cofc.pojo.video.Program;
import com.cofc.pojo.video.Source;
import com.cofc.pojo.video.SportsType;
import com.cofc.pojo.video.Topic;
import com.cofc.pojo.video.UserBean;
import com.cofc.pojo.video.Program;
import com.cofc.service.video.ProgramService;
import com.cofc.util.BaseUtil;
import com.cofc.util.JsonUtil;
import com.cofc.util.wxpay.WXPayUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/program")
public class ProgramController extends BaseUtil {
	public static Logger log = Logger.getLogger("ProgramController");

	@Resource
	private ProgramService programService;

	// 跳转后台用户列表
	@RequestMapping("/toProgramList")
	public ModelAndView toProgramList(ModelAndView modelView, HttpServletRequest request) throws ParseException {
		BackUser bu = (BackUser) request.getSession().getAttribute("loginer");

		log.info("进入后台用户列表的jsp页面");
		// List<UserCommon> userList = usercService.getUserIdList();
		modelView.addObject("loginPlat", bu.getLoginPlat());// 传给前台模板：空值显示百享园，不空显示相对应的
		modelView.addObject("appList", null);
		// modelView.addObject("userList", userList);
		modelView.setViewName("program/programList");
		return modelView;
	}

	// 获得电视频道
	@RequestMapping("/getProgramById")
	public ModelAndView getProgramByIdshowUserDetails(HttpServletRequest request, ModelAndView modelView, Integer id,
			Integer pageNo) {
		Program program = programService.getProgramById(id);

		String imgHtml = "";
		modelView.setViewName("program/programDetails");
		modelView.addObject("program", program);
		modelView.addObject("page", pageNo);
		modelView.addObject("imgHtml", imgHtml);
		return modelView;
	}

	// 获得电视频道
	@RequestMapping("/getProgramList")
	public void getProgramList(HttpServletRequest request, HttpServletResponse response, String name, Integer channelId,
			Integer page, Integer limit) throws IOException {
		if (page == null || page < 1) {
			page = 1;
		}
		if (limit == null) {
			limit = 10;
		}

		List<Program> programList = programService.getProgramListByAssemble(name, channelId, (page - 1) * limit, limit);
		System.out.println("programList:" + programList.size());
		int rowsCount = programService.getProgramCountByAssemble(name, channelId);
		output(response, JsonUtil.buildJsonByTotalCount(programList, rowsCount));
	}

	// 更新频道
	@RequestMapping("/updateProgram")
	public void updateProgram(HttpServletRequest request, HttpServletResponse response, Program program)
			throws IOException {

		System.out.println("program:" + program.getName());
		try {
			programService.updateProgram(program);
			output(response, JsonUtil.buildFalseJson("0", "修改成功"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			output(response, JsonUtil.buildFalseJson("405", "修改失败"));
		}
	}

}

package com.cofc.controller.anchor;

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
import com.cofc.pojo.video.Anchor;

import com.cofc.pojo.video.Carousel;

import com.cofc.pojo.video.Column;
import com.cofc.pojo.video.Program;
import com.cofc.pojo.video.Source;
import com.cofc.pojo.video.SportsType;
import com.cofc.pojo.video.Topic;
import com.cofc.pojo.video.UserBean;
import com.cofc.pojo.video.VodBean;
import com.cofc.pojo.video.VodType;
import com.cofc.service.video.AnchorService;

import com.cofc.service.video.CarouselService;
import com.cofc.service.video.AnchorService;
import com.cofc.service.video.ColumnService;
import com.cofc.service.video.ProgramService;
import com.cofc.service.video.SportsTypeService;
import com.cofc.service.video.TopicService;
import com.cofc.service.video.UserBeanService;
import com.cofc.service.video.VodBeanService;
import com.cofc.service.video.VodTypeService;
import com.cofc.util.BaseUtil;
import com.cofc.util.JsonUtil;
import com.cofc.util.wxpay.WXPayUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/anchor")
public class AnchorController extends BaseUtil {
	public static Logger log = Logger.getLogger("AnchorController");
	
	@Resource
	private AnchorService anchorService;

	// 跳转后台用户列表
	@RequestMapping("/toAnchorList")
	public ModelAndView toAnchorList(ModelAndView modelView, HttpServletRequest request) throws ParseException {
		BackUser bu = (BackUser) request.getSession().getAttribute("loginer");

		log.info("进入后台用户列表的jsp页面");
		// List<UserCommon> userList = usercService.getUserIdList();
		modelView.addObject("loginPlat", bu.getLoginPlat());// 传给前台模板：空值显示百享园，不空显示相对应的
		modelView.addObject("appList", null);
		// modelView.addObject("userList", userList);
		modelView.setViewName("anchor/anchorList");
		return modelView;
	}
	

	// 获得电视频道
	@RequestMapping("/getAnchorById")
	public ModelAndView getAnchorByIdshowUserDetails(HttpServletRequest request, ModelAndView modelView, Integer id,
			Integer pageNo){
		Anchor anchor = anchorService.getAnchorById(id);

		String imgHtml = "";
		modelView.setViewName("anchor/anchorDetails");
		modelView.addObject("anchor", anchor);
		modelView.addObject("page", pageNo);
		modelView.addObject("imgHtml", imgHtml);
		return modelView;
	}
	// 获得电视频道
	@RequestMapping("/getAnchorList")
	public void getAnchorList(HttpServletRequest request, HttpServletResponse response, String name, Integer status,Integer page,Integer limit) throws IOException {
		if (page == null || page < 1) {
			page = 1;
		}
		if (limit == null) {
			limit = 10;
		}

		List<Anchor> anchorList = anchorService.getAnchorListByAssemble(name,status,(page-1) * limit, limit);
		System.out.println("anchorList:" + anchorList.size());
		int rowsCount = anchorService.getAnchorCountByAssemble(name,status);
		output(response, JsonUtil.buildJsonByTotalCount(anchorList, rowsCount));
	}

	// 更新频道
	@RequestMapping("/updateAnchor")
	public void updateAnchor(HttpServletRequest request, HttpServletResponse response,Anchor anchor) throws IOException {
		//InputStream is = request.getInputStream();
		//String message = IOUtils.toString(is, "UTF-8");
		//System.out.println("message:" + message);
		System.out.println("anchor:" + anchor.getName());
		try {
			anchorService.updateAnchor(anchor);
			output(response, JsonUtil.buildFalseJson("0", "修改成功"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			output(response, JsonUtil.buildFalseJson("405", "修改失败"));
		}
	}	
	
}

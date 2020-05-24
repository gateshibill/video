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
import com.cofc.pojo.video.VodBean;
import com.cofc.service.video.VodBeanService;
import com.cofc.util.BaseUtil;
import com.cofc.util.JsonUtil;
import com.cofc.util.wxpay.WXPayUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/vod")
public class VodController extends BaseUtil {
	public static Logger log = Logger.getLogger("VodBeanController");
	
	@Resource
	private VodBeanService vodBeanService;

	// 跳转后台用户列表
	@RequestMapping("/toVodList")
	public ModelAndView toVodBeanList(ModelAndView modelView, HttpServletRequest request) throws ParseException {
		BackUser bu = (BackUser) request.getSession().getAttribute("loginer");

		log.info("进入后台用户列表的jsp页面");
		// List<UserCommon> userList = usercService.getUserIdList();
		modelView.addObject("loginPlat", bu.getLoginPlat());// 传给前台模板：空值显示百享园，不空显示相对应的
		modelView.addObject("appList", null);
		// modelView.addObject("userList", userList);
		modelView.setViewName("vod/vodList");
		return modelView;
	}
	

	// 获得电视频道
	@RequestMapping("/getVodBeanById")
	public ModelAndView getVodBeanByIdshowUserDetails(HttpServletRequest request, ModelAndView modelView, Integer id,
			Integer pageNo){
		VodBean vod = vodBeanService.selectByPrimaryKey(id);

		String imgHtml = "";
		modelView.setViewName("vod/vodDetails");
		modelView.addObject("vod", vod);
		modelView.addObject("page", pageNo);
		modelView.addObject("imgHtml", imgHtml);
		return modelView;
	}
	// 获得电视频道
	@RequestMapping("/getVodBeanList")
	public void getVodBeanList(HttpServletRequest request, HttpServletResponse response, String vodName, Integer typeId,Integer typeId1,Integer groupId,Integer page,Integer limit) throws IOException {
		if (page == null || page < 1) {
			page = 1;
		}
		if (limit == null) {
			limit = 10;
		}

		List<VodBean> vodList = vodBeanService.getVodListByAssemble(vodName, typeId, typeId1, groupId, (page-1) * limit, limit);
		System.out.println("vodList:" + vodList.size());
		int rowsCount = vodBeanService.getVodCountByAssemble(vodName, typeId, typeId1, groupId);
		output(response, JsonUtil.buildJsonByTotalCount(vodList, rowsCount));
	}

	// 更新频道
	@RequestMapping("/updateVodBean")
	public void updateVodBean(HttpServletRequest request, HttpServletResponse response,VodBean vod) throws IOException {

		System.out.println("vod:" + vod.getVodName());
		try {
			vodBeanService.updateByPrimaryKey(vod);
			output(response, JsonUtil.buildFalseJson("0", "修改成功"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			output(response, JsonUtil.buildFalseJson("405", "修改失败"));
		}
	}	
	
}

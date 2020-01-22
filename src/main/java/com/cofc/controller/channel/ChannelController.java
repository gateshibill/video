package com.cofc.controller.channel;

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
import com.cofc.pojo.video.Channel;
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
import com.cofc.service.video.ChannelService;
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
@RequestMapping("/channel")
public class ChannelController extends BaseUtil {
	public static Logger log = Logger.getLogger("ChannelController");
	
	@Resource
	private ChannelService channelService;

	// 跳转后台用户列表
	@RequestMapping("/toChannelList")
	public ModelAndView toChannelList(ModelAndView modelView, HttpServletRequest request) throws ParseException {
		BackUser bu = (BackUser) request.getSession().getAttribute("loginer");

		log.info("进入后台用户列表的jsp页面");
		// List<UserCommon> userList = usercService.getUserIdList();
		modelView.addObject("loginPlat", bu.getLoginPlat());// 传给前台模板：空值显示百享园，不空显示相对应的
		modelView.addObject("appList", null);
		// modelView.addObject("userList", userList);
		modelView.setViewName("channel/channelList");
		return modelView;
	}
	
	// 添加或更新电视频道，根据频道名和来源更新
	@RequestMapping("/addChannel")
	public void addChannel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		InputStream is = request.getInputStream();
		String message = IOUtils.toString(is, "UTF-8");
		System.out.println("message:" + message);
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
			Channel channel = gson.fromJson(message, Channel.class);
			System.out.println("channel:" + channel);
			Channel tmp = channelService.getChannelByAssemble(channel);
			if (null == tmp) {
				channel.setAppId(1);
				channel.setRank(0);
				channel.setDelay(0);
				channel.setReserveUrls("");
				channel.setRecycleUrls("");
				channel.setLogoUrl("");
				channel.setPosterUrl("");
				channel.setContent("");
				channel.setType(0);
				channel.setRcmd(0);
				channel.setGroupId(0);
				channel.setCountry("");
				channel.setLanguage("");
				channel.setLastTime(new Date());

				channelService.addChannel(channel);
			} else {
				tmp.setPlayUrl(channel.getPlayUrl());
				channelService.updateChannel(tmp);
			}
			output(response, JsonUtil.buildSuccessJson("0", "success"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			output(response, JsonUtil.buildSuccessJson("405", "error"));
		}
	}
	// 获得电视频道
	@RequestMapping("/getChannelById")
	public ModelAndView getChannelByIdshowUserDetails(HttpServletRequest request, ModelAndView modelView, Integer id,
			Integer pageNo){
		Channel channel = channelService.getChannelById(id);

		String imgHtml = "";
		modelView.setViewName("channel/channelDetails");
		modelView.addObject("channel", channel);
		modelView.addObject("page", pageNo);
		modelView.addObject("imgHtml", imgHtml);
		return modelView;
	}
	// 获得电视频道
	@RequestMapping("/getChannelList")
	public void getChannelList(HttpServletRequest request, HttpServletResponse response, Integer type,
			Integer groupId, Integer page,Integer status,String name, Integer limit) throws IOException {
		if (page == null || page < 1) {
			page = 1;
		}
		if (limit == null) {
			limit = 10;
		}
		if (null == groupId) {
			groupId = 0;
		}
		if (null == type) {
			type = 0;
		}
		List<Channel> channelList = channelService.getChannelListByAssemble(type, groupId, status,name,(page-1) * limit, limit);
		System.out.println("channelList:" + channelList.size());
		int rowsCount = channelService.getChannelCountByAssemble(type, groupId, status,name);
		output(response, JsonUtil.buildJsonByTotalCount(channelList, rowsCount));
	}

	// 更新频道
	@RequestMapping("/updateChannel")
	public void updateChannel(HttpServletRequest request, HttpServletResponse response,Channel channel) throws IOException {
		//InputStream is = request.getInputStream();
		//String message = IOUtils.toString(is, "UTF-8");
		//System.out.println("message:" + message);
		System.out.println("channel:" + channel.getName());
		try {
			channelService.updateChannel(channel);
			output(response, JsonUtil.buildFalseJson("0", "修改成功"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			output(response, JsonUtil.buildFalseJson("405", "修改失败"));
		}
	}	
	
	// 获得电视频道
	@RequestMapping("/getRcmdChannels")
	public void getRcmdChannels(HttpServletRequest request, HttpServletResponse response, Integer page, Integer limit)
			throws IOException {
		if (page == null || page < 0) {
			page = 0;
		}
		if (limit == null) {
			limit = 10;
		}

		List<Channel> channelList = channelService.getChannelListByRcmd(page, limit);
		System.out.println("channelList:" + channelList.size());
		output(response, JsonUtil.buildCustomJson("0", "succes", channelList));
	}

}

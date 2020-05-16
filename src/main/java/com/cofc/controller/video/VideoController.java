package com.cofc.controller.video;

import java.io.IOException;
import java.io.InputStream;
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
import org.jivesoftware.smack.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.cofc.pojo.video.Anchor;

import com.cofc.pojo.video.Carousel;
import com.cofc.pojo.video.Channel;
import com.cofc.pojo.video.Column;
import com.cofc.pojo.video.Picture;
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
import com.cofc.service.video.PictureService;
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
@RequestMapping("/video")
public class VideoController extends BaseUtil {

	@Resource
	private ColumnService columnService;

	@Resource
	private UserBeanService userService;
	@Resource
	private TopicService topicService;
	@Resource
	private PictureService pictureService;
	@Resource
	private VodBeanService vodService;
	@Resource
	private ChannelService channelService;
	@Resource
	private ProgramService programService;
	@Resource
	private AnchorService anchorService;
	@Resource
	private VodTypeService vodTypeService;
	@Resource
	private SportsTypeService sportsTypeService;
	@Resource
	private CarouselService carouselpeService;

	public static Logger log = Logger.getLogger("ClientController");

	// 获取热点节目
	@RequestMapping("/getCarousels")
	public void getCarousels(HttpServletResponse response) throws IOException {

		List<Carousel> list = carouselpeService.getCarouselList();
		System.out.println("programList:" + list.size());
		output(response, JsonUtil.buildCustomJson("0", "succes", list));
	}

	// 获取VOD
	@RequestMapping("/getVodTypes")
	public void getVodTypes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<VodType> vts = vodTypeService.getVodTypeList();
		output(response, JsonUtil.buildCustomJson("0", "succes", vts));
	}

	@RequestMapping("/getSportsTypes")
	public void getSportsTypes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<SportsType> sts = sportsTypeService.getSportsTypeList();
		output(response, JsonUtil.buildCustomJson("0", "succes", sts));
	}

	// 增加主播
	@RequestMapping("/addSportsType")
	public void addSportsType(HttpServletRequest request, HttpServletResponse response) throws IOException {
		InputStream is = request.getInputStream();
		String message = IOUtils.toString(is, "UTF-8");
		System.out.println("message:" + message);
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
			SportsType sportsType = gson.fromJson(message, SportsType.class);
			System.out.println("sportsType:" + sportsType);
			sportsTypeService.addSportsType(sportsType);
			output(response, JsonUtil.buildSuccessJson("0", "success"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			output(response, JsonUtil.buildSuccessJson("405", "error"));
		}
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
	public void getChannelById(HttpServletRequest request, HttpServletResponse response, Integer id)
			throws IOException {
		Channel channel = channelService.getChannelById(id);
		output(response, JsonUtil.objectToJson("0", channel));
	}

	// 获得电视频道
	@RequestMapping("/getChannels")
	public void getChannels(HttpServletRequest request, HttpServletResponse response, Integer type, Integer groupId,
			Integer page, Integer limit) throws IOException {
		if (page == null || page < 0) {
			page = 0;
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
		List<Channel> channelList = channelService.getChannelListByType(type, groupId, page * limit, limit);
		System.out.println("channelList:" + channelList.size());
		output(response, JsonUtil.buildCustomJson("0", "succes", channelList));
	}

	// 更新和增加电视频道
	@RequestMapping("/updateChannel")
	public void updateChannel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		InputStream is = request.getInputStream();
		String message = IOUtils.toString(is, "UTF-8");
		System.out.println("message:" + message);
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
			Channel channel = gson.fromJson(message, Channel.class);
			Channel cl = channelService.getChannelById(channel.getId());
			if (null != cl) {
				cl.setPlayUrl(channel.getPlayUrl());
				// cl.setLogoUrl(channel.getLogoUrl());
				// cl.setLastTime(channel.getLastTime());
				channelService.updateChannel(cl);
			} else {
				channelService.addChannel(channel);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			output(response, JsonUtil.buildSuccessJson("405", "error"));
		}

		output(response, JsonUtil.buildSuccessJson("0", "succes"));
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

	// 增加主播
	@RequestMapping("/addAnchor")
	public void addAnchor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		InputStream is = request.getInputStream();
		String message = IOUtils.toString(is, "UTF-8");
		System.out.println("message:" + message);
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
			Anchor anchor = gson.fromJson(message, Anchor.class);
			System.out.println("anchor:" + anchor);
			anchorService.addAnchor(anchor);
			output(response, JsonUtil.buildSuccessJson("0", "success"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			output(response, JsonUtil.buildSuccessJson("405", "error"));
		}
	}

	// 更新主播
	@RequestMapping("/updateAnchor")
	public void updateAnchor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		InputStream is = request.getInputStream();
		String message = IOUtils.toString(is, "UTF-8");
		System.out.println("message:" + message);
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
			Anchor anchor = gson.fromJson(message, Anchor.class);
			System.out.println("anchor:" + anchor);

			Anchor anchorTmp = anchorService.getAnchorByAssemble(anchor.getName(), anchor.getPosterUrl());
			if (null == anchorTmp) {
				anchorService.addAnchor(anchor);
			} else {
				anchorService.updateAnchor(anchor);
			}
			output(response, JsonUtil.buildSuccessJson("0", "success"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			output(response, JsonUtil.buildSuccessJson("405", "error"));
		}
	}

	// 获取主播列表
	@RequestMapping("/getAnchors")
	public void getAnchors(HttpServletRequest request, HttpServletResponse response, Integer type, Integer page,
			Integer limit) throws IOException {
		if (page == null || page < 0) {
			page = 0;
		}
		if (limit == null) {
			limit = 10;
		}
		List<Anchor> anchorList = anchorService.getAnchorList(page * limit, limit);
		output(response, JsonUtil.buildCustomJson("0", "succes", anchorList));
	}

	// 获得电视频道节目单
	@RequestMapping("/addProgram")
	public void addProgram(HttpServletRequest request, HttpServletResponse response) throws IOException {
		InputStream is = request.getInputStream();
		String message = IOUtils.toString(is, "UTF-8");
		System.out.println("message:" + message);
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
			Program program = gson.fromJson(message, Program.class);
			System.out.println("program:" + program);
			programService.addProgram(program);
			output(response, JsonUtil.buildSuccessJson("0", "success"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			output(response, JsonUtil.buildSuccessJson("405", "error"));
		}
	}

	// 更新联赛赛程,没有则添加
	@RequestMapping("/updateLeagueProgram")
	public void updateLeagueProgram(HttpServletRequest request, HttpServletResponse response) throws IOException {
		InputStream is = request.getInputStream();
		String message = IOUtils.toString(is, "UTF-8");
		System.out.println("message:" + message);
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
			Program program = gson.fromJson(message, Program.class);
			System.out.println("program:" + program);
			Program tmp = programService.getLeagueProgram(program);
			if (null == tmp) {
				programService.addProgram(program);
				output(response, JsonUtil.buildSuccessJson("0", "add success"));
			} else {
				programService.updateLeagueProgram(program);
				output(response, JsonUtil.buildSuccessJson("0", "update success"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			output(response, JsonUtil.buildSuccessJson("405", "error"));
		}
	}

	// 更新节目单比分
	@RequestMapping("/updateProgramScore")
	public void updateProgramScore(HttpServletRequest request, HttpServletResponse response) throws IOException {
		InputStream is = request.getInputStream();
		String message = IOUtils.toString(is, "UTF-8");
		System.out.println("message:" + message);
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
			Program program = gson.fromJson(message, Program.class);
			System.out.println("program:" + program);
			programService.updateProgramScore(program);
			output(response, JsonUtil.buildSuccessJson("0", "success"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			output(response, JsonUtil.buildSuccessJson("405", "error"));
		}
	}

	// 获得电视频道节目单
	@RequestMapping("/getPrograms")
	public void getPrograms(HttpServletRequest request, HttpServletResponse response, Integer type, Integer page,
			Integer limit) throws IOException {
		if (page == null || page < 0) {
			page = 0;
		}
		if (limit == null) {
			limit = 10;
		}
		List<Program> programList = programService.getPrograms(type, page * limit, limit);
		output(response, JsonUtil.buildCustomJson("0", "succes", programList));
	}

	// 根据今天后的日期获取节目单
	@RequestMapping("/getProgramsByDay")
	public void getProgramsByDay(HttpServletRequest request, HttpServletResponse response, Integer day, Integer page,
			Integer limit) throws IOException {
		if (page == null || page < 0) {
			page = 0;
		}
		if (limit == null) {
			limit = 10;
		}
		List<Program> programList = programService.getProgramsByDay(day, page * limit, limit);
		System.out.println("programList:" + programList.size());
		output(response, JsonUtil.buildCustomJson("0", "succes", programList));
	}

	// 根据活动获取节目单
	@RequestMapping("/getProgramsByEvent")
	public void getProgramsByEvent(HttpServletRequest request, HttpServletResponse response, String event, Integer page,
			Integer limit) throws IOException {
		if (page == null || page < 0) {
			page = 0;
		}
		if (limit == null) {
			limit = 10;
		}
		List<Program> programList = programService.getProgramsByEvent(event, page * limit, limit);
		System.out.println("programList:" + programList.size());
		output(response, JsonUtil.buildCustomJson("0", "succes", programList));
	}

	// 根据活动获取节目单
	@RequestMapping("/delProgramsByEvent")
	public void delProgramsByEvent(HttpServletRequest request, HttpServletResponse response, String event)
			throws IOException {
		programService.delProgramsByEvent(event);
		output(response, JsonUtil.buildSuccessJson("0", "success"));
	}

	// 获取当前节目，优先赛事节目
	@RequestMapping("/getCurrentPrograms")
	public void getCurrentPrograms(HttpServletRequest request, HttpServletResponse response, String event, Integer page,
			Integer limit) throws IOException {
		if (page == null || page < 0) {
			page = 0;
		}
		if (limit == null) {
			limit = 10;
		}
		List<Program> programList = programService.getCurrentPrograms(page * limit, limit);
		System.out.println("programList:" + programList.size());
		output(response, JsonUtil.buildCustomJson("0", "succes", programList));
	}

	// 获取热点赛事节目，之前是用来获取篮球世界杯当前节目，
	@RequestMapping("/getHotPrograms")
	public void getHotPrograms(HttpServletRequest request, HttpServletResponse response, String event, Integer page,
			Integer limit) throws IOException {
		if (page == null || page < 0) {
			page = 0;
		}
		if (limit == null) {
			limit = 10;
		}
		List<Program> programList = programService.getHotPrograms(page * limit, limit);
		System.out.println("programList:" + programList.size());
		output(response, JsonUtil.buildCustomJson("0", "succes", programList));
	}

	// 获得联赛日程，默认未前一天，往后一周时间，也可以用来获得热门节目，event='热门'
	@RequestMapping("/getLeaguePrograms")
	public void getLeaguePrograms(HttpServletResponse response, String event, Integer page, Integer limit)
			throws IOException {
		if (page == null || page < 0) {
			page = 0;
		}
		if (limit == null) {
			limit = 10;
		}
		List<Program> programList = programService.getLeaguePrograms(60, event, page * limit, limit);
		System.out.println("programList:" + programList.size());
		output(response, JsonUtil.buildCustomJson("0", "succes", programList));
	}

	// 获取VOD
	@RequestMapping("/getVods")
	public void getVods(HttpServletRequest request, HttpServletResponse response, Integer typeId, Integer typeId1,
			Integer groupId, Integer page, Integer limit) throws IOException {
		if (page == null || page < 0) {
			page = 0;
		}
		if (limit == null) {
			limit = 10;
		}
		List<VodBean> vbs = vodService.getVods(typeId, (typeId1 == null ? 0 : typeId1), (groupId == null ? 0 : groupId),
				page * limit, limit);
		output(response, JsonUtil.buildCustomJson("0", "succes", vbs));
	}

	// 获取VOD ex
	@RequestMapping("/getVodsEx")
	public void getVodsEx(HttpServletRequest request, HttpServletResponse response, Integer typeId, Integer typeId1,
			Integer groupId, Integer page, Integer limit) throws IOException {
		if (page == null || page < 0) {
			page = 0;
		}
		if (limit == null) {
			limit = 10;
		}
		VodBean vod = new VodBean();
		vod.setVodName("");
		List<VodBean> vbs = vodService.getVodsEx(vod, page * limit, limit);
		output(response, JsonUtil.buildCustomJson("0", "succes", vbs));
	}

	// 获取VOD
	@RequestMapping("/getSportsVods")
	public void getSportsVods(HttpServletRequest request, HttpServletResponse response, Integer typeId, Integer typeId1,
			Integer groupId, Integer page, Integer limit) throws IOException {
		if (page == null || page < 0) {
			page = 0;
		}
		if (limit == null) {
			limit = 10;
		}
		List<VodBean> vbs = vodService.getSportsVods(typeId, (typeId1 == null ? 0 : typeId1),
				(groupId == null ? 0 : groupId), page * limit, limit);
		output(response, JsonUtil.buildCustomJson("0", "succes", vbs));
	}

	// 模糊查询
	@RequestMapping("/fuzzyQueryVods")
	public void fuzzyQueryVods(HttpServletRequest request, HttpServletResponse response, String condition, Integer page,
			Integer limit) throws IOException {
		if (page == null || page < 0) {
			page = 0;
		}
		if (limit == null) {
			limit = 10;
		}
		List<VodBean> vbs = vodService.fuzzyQueryVods(condition, page * limit, limit);
		// output(response, JsonUtil.buildJson(vbs));
		output(response, JsonUtil.buildCustomJson("0", "succes", vbs));
	}

	// 添加视频，提供给爬虫接口
	@RequestMapping("/addVod")
	public void addVod(HttpServletRequest request, HttpServletResponse response) throws IOException {
		InputStream is = request.getInputStream();
		String message = IOUtils.toString(is, "UTF-8");
		System.out.println("message:" + message);
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
			VodBean vod = gson.fromJson(message, VodBean.class);
			//System.out.println("vod:" + vod);
			VodBean tmp = vodService.getVodByNameAndDirector(vod.getVodName(), vod.getVodDirector(),vod.getTvSerialNumber());

			// 根据爬取关键词，给影片分类
			if (null != vod.getVodClass() && vod.getVodClass().length() > 0) {
				List<VodType> vts = vodTypeService.getVodTypeList();
				Short typeId = 1;// 默认为电影
				for (VodType vt : vts) {
					if (vt.getTypeName().contains(vod.getVodClass()) || vod.getVodClass().contains(vt.getTypeName())) {
						typeId = vt.getTypeId();
						break;
					}
				}
				vod.setTypeId(typeId);
			}
			if (null == tmp) {
				vodService.insert(vod);
				System.out.println("插入成功");
			} else {
				vodService.updatePlayUrlByNameAndDirector(vod);
				System.out.println("更新成功");
			}
			output(response, JsonUtil.buildSuccessJson("0", "success"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			output(response, JsonUtil.buildSuccessJson("405", "error"));
		}
	}

	// 1.增加电视剧，走addVod接口
	// 2.增加电视剧子集，走addVod接口
	// 3.获取电视剧列表
	@RequestMapping("/getTvSerials")
	public void getTvSerials(HttpServletResponse response, Integer page, Integer limit) {
		System.out.println("getTvSerial():" + page + "/" + limit);
		if (page == null || page < 0) {
			page = 0;
		}
		if (limit == null) {
			limit = 10;
		}
		List<VodBean> list = vodService.getTvSerials(page, limit);
		output(response, JsonUtil.buildCustomJson("0", "succes", list));
	}
	//获取一部连续剧的整个续集
	@RequestMapping("/getTvSequels")
	public void getTvSequels(HttpServletResponse response, String vodTv,Integer page, Integer limit) {
		System.out.println("getTvSequels():" + vodTv+"/"+page + "/" + limit);
		if (null == vodTv) {
			output(response, JsonUtil.buildFalseJson("1", "参数不正确"));
			return;
		}
		if (page == null || page < 0) {
			page = 0;
		}
		if (limit == null) {
			limit = 10;
		}
		List<VodBean> list = vodService.getTvSequels(vodTv,page, limit);
		output(response, JsonUtil.buildCustomJson("0", "succes", list));
	}

	// 4.获取电视子集
	@RequestMapping("/getTvSerial")
	public void getTvSerial(HttpServletResponse response, String vodTv, Integer tvSerialNumber, Integer page,
			Integer limit) {
		System.out.println("getTvSerial():" + vodTv + "/" + tvSerialNumber);
		if (null == vodTv || null == tvSerialNumber) {
			output(response, JsonUtil.buildFalseJson("1", "参数不正确"));
			return;
		}
		if (page == null || page < 0) {
			page = 0;
		}
		if (limit == null) {
			limit = 10;
		}
		VodBean vb = vodService.getTvSerial(vodTv, tvSerialNumber, page, limit);
		output(response, JsonUtil.objectToJson("0", vb));
	}
	
	// 跳转后台用户列表
	@RequestMapping("/play")
	public ModelAndView play(ModelAndView modelView, HttpServletRequest request,String url) {
		System.out.println("play(): url" + url);
		// List<UserCommon> userList = usercService.getUserIdList();
		modelView.addObject("url",url);// 
		// modelView.addObject("userList", userList);
		modelView.setViewName("video/play");
		return modelView;
	}

	// 根据过期日常删除指定源的内容
	@RequestMapping("/delVods")
	public void delVods(HttpServletResponse response, String source, Integer expire) throws IOException {
		vodService.deleteBySourceAndExpire(source, expire);
		output(response, JsonUtil.buildSuccessJson("0", "success"));
	}

	// 获取推荐内容
	@RequestMapping("/getRcmdVods")
	public void getRcmdVods(HttpServletRequest request, HttpServletResponse response) throws IOException {
		InputStream is = request.getInputStream();
		String message = IOUtils.toString(is, "UTF-8");
		System.out.println("message:" + message);
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
			VodBean vod = gson.fromJson(message, VodBean.class);
			System.out.println("vod:" + vod.getVodActor() + "|" + vod.getVodDirector() + "|" + vod.getTypeId());
			List<VodBean> vbs = vodService.getRcmdVods(vod.getVodActor(), vod.getVodDirector(), vod.getTypeId(), 0, 10);
			if (vbs == null || vbs.isEmpty()) {// 防止没有推荐内容
				vbs = vodService.getVods(0, 0, 0, 0, 10);
			}
			Collections.shuffle(vbs);// 临时方案
			output(response, JsonUtil.buildCustomJson("0", "success", vbs));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			output(response, JsonUtil.buildFalseJson("405", "failure"));
		}
	}

	// 添加文章
	@RequestMapping("/addTopic")
	public void addTopic(HttpServletRequest request, HttpServletResponse response) throws IOException {
		InputStream is = request.getInputStream();
		String message = IOUtils.toString(is, "UTF-8");
		System.out.println("message:" + message);
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
			Topic topic = gson.fromJson(message, Topic.class);
			System.out.println("topic:" + topic);
			Topic tmp = topicService.getTopicByAssemble(topic.getTopicName(), topic.getSource());
			if (null == tmp) {
				topicService.addTopic(topic);
			} else {
				topicService.updateTopic(topic);
			}
			output(response, JsonUtil.buildSuccessJson("0", "success"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			output(response, JsonUtil.buildSuccessJson("405", "error"));
		}
	}

	// 获取文章
	@RequestMapping("/getTopics")
	public void getTopics(HttpServletResponse response, Integer topicType, Integer page, Integer limit)
			throws IOException {
		if (page == null || page < 0) {
			page = 0;
		}
		if (limit == null) {
			limit = 10;
		}
		List<Topic> tpcs = topicService.getTopicListByType((null == topicType ? 0 : topicType), page * limit, limit);
		// output(response, JsonUtil.buildJson(vbs));
		output(response, JsonUtil.buildCustomJson("0", "succes", tpcs));
	}

	// 获取花絮图片
	@RequestMapping("/getPictures")
	public void getPictures(HttpServletResponse response, Integer type, Integer page, Integer limit)
			throws IOException {
		if (page == null || page < 1) {
			page = 1;
		}
		if (limit == null) {
			limit = 10;
		}
		List<Picture> pcs = pictureService.getPictureList(type, (page - 1) * limit, limit);
		output(response, JsonUtil.buildCustomJson("0", "succes", pcs));
	}

	// 添加花絮
	@RequestMapping("/addPicture")
	public void addPicture(HttpServletRequest request, HttpServletResponse response) throws IOException {
		InputStream is = request.getInputStream();
		String message = IOUtils.toString(is, "UTF-8");
		System.out.println("message:" + message);
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
			Picture picture = gson.fromJson(message, Picture.class);
			System.out.println("topic:" + picture);
			// Topic tmp =
			// topicService.getTopicByAssemble(picture.getTopicName(),
			// picture.getSource());
			// if (null == tmp) {
			// pictureService.addTopic(picture);
			// } else {
			pictureService.addPicture(picture);
			// }
			output(response, JsonUtil.buildSuccessJson("0", "success"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			output(response, JsonUtil.buildSuccessJson("405", "error"));
		}
	}

}

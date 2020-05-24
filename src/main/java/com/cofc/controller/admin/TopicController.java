package com.cofc.controller.admin;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cofc.pojo.BackUser;
import com.cofc.pojo.video.Topic;
import com.cofc.service.video.TopicService;
import com.cofc.util.BaseUtil;
import com.cofc.util.JsonUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/topic")
public class TopicController extends BaseUtil {
	public static Logger log = Logger.getLogger("topicController");

	@Resource
	private TopicService topicService;
	
	@RequestMapping("/goList")
	private ModelAndView gotopicListJsp(ModelAndView modelView, HttpServletRequest request, Topic desc) {

		log.info("进入发现列表的jsp页面");
		modelView.addObject("appList", null);
		//modelView.addObject("typeList", topicList);
		modelView.addObject("desc", desc);
		modelView.setViewName("/topic/topicList");
		return modelView;
	}


	@RequestMapping("/showDataList")
	public void showtopicList(HttpServletRequest request, HttpServletResponse response, Integer page, Integer limit)
					throws ParseException {
		BackUser bu = (BackUser) request.getSession().getAttribute("loginer");
		request.getSession().setAttribute("currdescoPage", page);
		int count = 100;

		List<Topic> topicList = topicService.getTopicList((page-1)*limit, limit);
		output(response, JsonUtil.buildJsonByTotalCount(topicList, count));
	}

//	@RequestMapping("/sxjtopic")
//	public String shangxiajiatopic(Topic descov) {
//		topicService.updateTopicInfo(descov);
//		return "redirect:./showDataList.do";
//	}


	@RequestMapping("/topicDetails")
	public ModelAndView showtopicDetails(ModelAndView modelView, Integer topicId) {
		Topic common = topicService.getTopicByTopicId(topicId);

		modelView.addObject("common", common);
		return modelView;
	}

//	/**
//	 * 发布文章
//	 * 
//	 * @param request
//	 * @param response
//	 */
//	@RequestMapping(value = "/addNewtopic", method = RequestMethod.POST)
//	@ResponseBody
//	public void addNewtopic(HttpServletRequest request, HttpServletResponse response, Topic dc) {
//		ApplicationCommon currapp = applicationService.getApplicationById(dc.getLoginPlat());
//		String topicTitle = request.getParameter("topicTitle");
//		if (topicTitle != null && topicTitle != "") {
//			dc.settopicTitle(topicTitle);
//		}
//		dc.settopicType(dc.gettopicType());
//		dc.setIsPass(1);
//		dc.setIsShangJia(1);
//		dc.setPublishTime(new Date());
//		dc.setPublisherId(currapp.getApplicationOwner());
//		if (dc.getLoginPlat() != null) {
//			dc.setLoginPlat(dc.getLoginPlat());
//		}
//		if (dc.gettopicDetails() != null && dc.gettopicDetails() != "") {
//			dc.settopicDetails(dc.gettopicDetails());
//		}
//		dc.setPariseCount(0);
//		dc.setReadCount(0);
//		dc.setJoinedCount(0);
//		dc.setCollectCount(0);
//		// dc.setIsTop(1);//默认置顶
//		if (dc.getIsRecommend() == 1) {
//			dc.setRecommendWay(dc.getRecommendWay());
//		}
//		if (dc.getExpiryDate() == null) {
//			dc.setExpiryDate(String.valueOf(1));
//		}
//		try {
//			topicService.addNewDesCommon(dc);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		// 更新推荐表
//		Thread thread = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				recommendService2.updatetopicRecommendBytopicID(dc);
//			}
//		});
//		thread.start();
//
//		Topic dc2 = topicService.gettopicById(dc.gettopicId());
//		if (dc2 == null) {
//			output(response, JsonUtil.buildFalseJson("1", "新增失败"));
//		} else {
//			output(response, JsonUtil.buildFalseJson("0", "新增成功"));
//		}
//	}
//
//	// 删除发布
//	@RequestMapping("/dealtopic")
//	public void dealtopic(HttpServletResponse response, Integer topicId, HttpServletRequest request) {
//		Topic topic = topicService.gettopicById(topicId);
//		// BackUser bu = (BackUser)
//		// request.getSession().getAttribute("loginer");
//		// ApplicationCommon currapp =
//		// applicationService.getApplicationById(topic.getLoginPlat());
//		// UserRole userRole = userRoleService.getUserRoleById(bu.getUserId());
//		// String[] rolesarr = userRole.getRoleId().split(",");
//		// boolean isSuperm = false;
//		// for (String role : rolesarr) {
//		// while ("1".equals(role)) {
//		// isSuperm = true;
//		// break;
//		// }
//		// }
//		// if (!currapp.getApplicationOwner().equals(bu.getUserId()) &&
//		// !isSuperm) {
//		// output(response, JsonUtil.buildFalseJson("2", "系统检测到你没有删除发布的权限!"));
//		// } else {
//		try {
//			topicService.deleteTopic(topic);
//			output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
//		} catch (Exception e) {
//			output(response, JsonUtil.buildFalseJson("1", "删除失败!"));
//		}
//		// }
//	}
//
	// 添加文章
	@RequestMapping("/updateTopic")
	public void updateTopic(HttpServletRequest request, HttpServletResponse response,Topic topic) throws IOException {
		//InputStream is = request.getInputStream();
		//String message = IOUtils.toString(is, "UTF-8");
		//System.out.println("message:" + message);
		System.out.println("tpc:" + topic.getTopicName());
		try {
			//Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
		//	Topic topic = gson.fromJson(message, Topic.class);
			System.out.println("topic:" + topic);
			topicService.updateTopic(topic);

			output(response, JsonUtil.buildFalseJson("0", "修改成功"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			output(response, JsonUtil.buildFalseJson("405", "修改失败"));
		}
	}


//
//	public static String guoHtml(String string) {
//		// 替换replaceAll
//		String content = string.replace("<\\s*img\\s+([^>]*)\\s*>", " style='max-width:100%;width:100%' ");
//		return content;
//	}
//
//	// 删除图片
//	@RequestMapping("/updateImage")
//	public void updateImage(HttpServletResponse response, Integer topicId, int index) {
//		Topic desc = topicService.gettopicById(topicId);
//		String[] myImage = desc.gettopicImage().split(",");
//		myImage[index - 1] = myImage[myImage.length - 1];
//		// 数组缩容
//		myImage = Arrays.copyOf(myImage, myImage.length - 1);
//		String my = "";
//		for (int i = 0; i < myImage.length; i++) {
//			my += myImage[i] + ",";
//		}
//		if (myImage.length > 0) {
//			my.substring(0, my.length() - 1);
//		}
//		desc.setUpdateTime(new Date());
//		desc.settopicImage(my);
//		topicService.updateTopicInfo(desc);
//		output(response, JsonUtil.buildFalseJson("0", "上传图片成功"));
//	}


}

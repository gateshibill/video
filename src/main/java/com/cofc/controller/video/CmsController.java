package com.cofc.controller.video;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cofc.pojo.video.Source;
import com.cofc.util.BaseUtil;
import com.cofc.util.JsonUtil;

@Controller
@RequestMapping("/video")
public class CmsController extends BaseUtil {

	
	public static Logger log = Logger.getLogger("ActionReportController");

	// 1.视频上传，
	@RequestMapping("/reportSourceBehavior")
	public void reportSourceBehavior(HttpServletRequest request,HttpServletResponse response) throws IOException {       
		//fgb.setIp(getIpAddr(request));
//		log.info(fgb.getId() + "(" + fgb.getName() + ")" + "上报:" + fgb.getType() + "|"
//				+ fgb.getContent() + "|" + fgb.getTime());
		
		//fgb.setCreateTime(new Date());
		//SourceBehaviorService.addSourceBehavior(fgb);
		output(response, JsonUtil.buildSuccessJson("0", "succes"));
	}
	
	// 2.媒资编辑
	@RequestMapping("/editAsset")
	public void editAsset(HttpServletRequest request,HttpServletResponse response, Source fg) throws IOException {

		output(response, JsonUtil.bulidObjectJson(fg));
	}
	
	// 3.栏目编辑，内容顺序调整
	@RequestMapping("/editColumn")
	public void editColumn(HttpServletRequest request,HttpServletResponse response, Source fg) throws IOException {

		output(response, JsonUtil.bulidObjectJson(fg));
	}
	
	// 4.内容上下架
	@RequestMapping("/editMoive")
	public void editMoive(HttpServletRequest request,HttpServletResponse response, Source fg) throws IOException {

		output(response, JsonUtil.bulidObjectJson(fg));
	}
	
}

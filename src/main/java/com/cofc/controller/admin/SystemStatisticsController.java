package com.cofc.controller.admin;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.Order;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cofc.pojo.BackUser;
import com.cofc.service.behavior.ClientActionService;
import com.cofc.util.BaseUtil;

@Controller
@RequestMapping("/systemcount")
public class SystemStatisticsController extends BaseUtil {
	@Resource
    private ClientActionService clientAtionService; 

	@RequestMapping("/gomain")
	public ModelAndView goMain(HttpServletRequest request, ModelAndView mv) {
		BackUser bu = (BackUser) request.getSession().getAttribute("loginer");
		int userCount = 88;
		int channelCount = 421;
		int vodCount = 34;
		int anchorCount = 654;
		int programCount = 86;
		int picCount = 857;
		int assetCount = 96;
		int newsCount = 97;

		mv.addObject("userCount",userCount);
		mv.addObject("channelCount",channelCount);
		mv.addObject("vodCount",vodCount);
		mv.addObject("anchorCount",anchorCount);
		mv.addObject("programCount",programCount);
		mv.addObject("picCount",picCount);
		mv.addObject("assetCount",assetCount);
		mv.addObject("newsCount",newsCount);
		mv.addObject("loginPlat",bu.getLoginPlat());
	    mv.setViewName("../../main");
		return mv;
	}
}

package com.cofc.controller.video;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cofc.pojo.video.ChannelType;
import com.cofc.pojo.video.Column;
import com.cofc.pojo.video.VodType;
import com.cofc.service.video.ChannelTypeService;
import com.cofc.service.video.ColumnService;
import com.cofc.service.video.SportsTypeService;
import com.cofc.util.BaseUtil;
import com.cofc.util.JsonUtil;

@Controller
@RequestMapping("/column")
public class ColumnController extends BaseUtil{
	public static Logger log = Logger.getLogger(ColumnController.class);	
	@Autowired
	private ColumnService columnService;
	@Resource
	private ChannelTypeService channelTypeService;

	// 获取VOD
	@RequestMapping("/getChannelTypes")
	public void getChannelTypes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<ChannelType> cts = channelTypeService.getChannelTypeList();
		output(response, JsonUtil.buildCustomJson("0", "succes", cts));
	}
	@RequestMapping(value="getColumnList",method=RequestMethod.POST)
	public void getColumnList(HttpServletResponse response,Column column,Integer page,
			Integer limit){
		if(page == null){
			page = 1;
		}
		if(limit == null){
			limit = 12;
		}
		int totalCount = columnService.getColumnCount(column);
		List<Column> lists = columnService.getList(column, (page-1)*limit, limit);
		output(response,JsonUtil.buildJsonByTotalCount(lists, totalCount));
	}
	@RequestMapping(value="add",method=RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request,ModelAndView mv){
		return mv;
	}
	@RequestMapping(value="doAdd",method=RequestMethod.POST)
	public void doAdd(HttpServletResponse response,Column column){
		try {
			columnService.addColumn(column);
			output(response,JsonUtil.buildFalseJson("0", "添加成功"));
		} catch (Exception e) {
			output(response,JsonUtil.buildFalseJson("1", "添加失败"));
		}
	}
	@RequestMapping(value="edit",method=RequestMethod.GET)
	public ModelAndView edit(HttpServletRequest request,ModelAndView mv,Integer id){
		Column column = columnService.getInfoById(id);
		mv.addObject("column", column);
		return mv;
	}
	@RequestMapping(value="doEdit",method=RequestMethod.POST)
	public void doEdit(HttpServletResponse response,Column column){
		try {
			columnService.updateColumn(column);
			output(response,JsonUtil.buildFalseJson("0", "编辑成功"));
		} catch (Exception e) {
			output(response,JsonUtil.buildFalseJson("1", "编辑失败"));
		}
	}
	@RequestMapping(value="del",method=RequestMethod.POST)
	public void del(HttpServletResponse response,Integer id){
		try {
			columnService.delColumn(id);
			output(response,JsonUtil.buildFalseJson("0", "删除成功"));
		} catch (Exception e) {
			output(response,JsonUtil.buildFalseJson("1", "删除失败"));
		}
	}
}

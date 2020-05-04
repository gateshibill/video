package com.cofc.controller.video;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.cofc.pojo.video.Channel;
import com.cofc.pojo.video.Source;
import com.cofc.service.video.SourceService;
import com.cofc.util.BaseUtil;
import com.cofc.util.ExcelReader;
import com.cofc.util.JsonUtil;
import com.cofc.util.NetworkUtil;


@Controller
@RequestMapping("/video")
public class SourceController extends BaseUtil {
	@Resource
	private SourceService sourceService;
	public static Logger log = Logger.getLogger(SourceController.class);

	// 1.添加节点资源
	@RequestMapping("/addSource")
	public void addSource(HttpServletRequest request, HttpServletResponse response, Source source) throws IOException {
		String ip=NetworkUtil.getIpAddr(request);
		Source s= sourceService.getSourceListByIpAndLocalIpAndVodIdAndPiece(ip, source.getLocalIp(),source.getVodId(), source.getPiece());
		if(null!=s)
		{
			s.setLastTime(new Date());
			s.setStatus(0);
			sourceService.updateSource(s);
		}else {
			source.setIp(ip);
		    sourceService.addSource(source);
		}
		 System.out.println(source.toString());
		output(response, JsonUtil.buildSuccessJson("0", "success"));
	}

	// 2.更新节点资源
	@RequestMapping("/updateSource")
	public void updateSource(HttpServletRequest request, HttpServletResponse response, Source source)
			throws IOException {
		Source s = sourceService.getSourceById(source.getId());
		s.setLastTime(new Date());
		s.setStatus(0);
		sourceService.updateSource(s);
		output(response, JsonUtil.bulidObjectJson(s));
	}

	// 3.获取可用节点资源
	@RequestMapping("/getAvailabeSourceList")
	public void getAvailabeSourceList(HttpServletRequest request, HttpServletResponse response, Source source)
			throws IOException {
		//这里可以做时间过滤，当前时间10分钟没有更新的作为无效处理，数据量大后影响查询效率。	
		String ip=NetworkUtil.getIpAddr(request);		
		List<Source> list = sourceService.getSourceListByIpAndVodIdAndPiece(ip,source.getVodId(), source.getPiece(), 0, 10);
		for(Source s:list)
		{
			s.setStatus(1);//表示同一网络
		}
		List<Source> list1 = sourceService.getSourceListByNoIpAndVodIdAndPiece(ip,source.getVodId(), source.getPiece(), 0, 10);
		//优先放同一网络的节点
		list.addAll(list1);
		output(response, JsonUtil.buildCustomJson("0", "success", list));
	}
	
	@RequestMapping("/importExcelChannels")
    public void importExcelChannels(HttpServletResponse response,MultipartFile file) {
        // 检查前台数据合法性
        if (null == file || file.isEmpty()) {
        	log.warn("上传的Excel商品数据文件为空！上传时间：" + new Date());
        	output(response, JsonUtil.buildSuccessJson("1", "上传文件为空"));
            return ;
        }
        
		  log.info("importExcelChannels() 上传文件："+file.getName()+"/"+file.getSize() );

        try {
            // 解析Excel
            List<Channel> list = ExcelReader.readExcel(file);
           for(Channel channel:list) {
        	 log.debug(channel.detail());  
           }
            // todo 进行业务操作
        	output(response, JsonUtil.buildSuccessJson("0", "success"));
            return ;
        } catch (Exception e) {
        	log.warn("上传的Excel商品数据文件为空！上传时间：" + new Date());
        	output(response, JsonUtil.buildSuccessJson("2", "解析文件异常"));
        }

    }
}

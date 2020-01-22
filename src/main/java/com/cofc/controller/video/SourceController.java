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
import com.cofc.pojo.video.Source;
import com.cofc.service.video.SourceService;
import com.cofc.util.BaseUtil;
import com.cofc.util.JsonUtil;


@Controller
@RequestMapping("/video")
public class SourceController extends BaseUtil {
	@Resource
	private SourceService sourceService;
	public static Logger log = Logger.getLogger(SourceController.class);

	// 1.添加节点资源
	@RequestMapping("/addSource")
	public void addSource(HttpServletRequest request, HttpServletResponse response, Source source) throws IOException {
		String ip=getIpAddr(request);
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
		String ip=getIpAddr(request);		
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
	
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for"); 
        System.out.println("x-forwarded-for ip: " + ip);
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {  
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if( ip.indexOf(",")!=-1 ){
                ip = ip.split(",")[0];
            }
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
            System.out.println("Proxy-Client-IP ip: " + ip);
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
            System.out.println("WL-Proxy-Client-IP ip: " + ip);
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_CLIENT_IP");  
            System.out.println("HTTP_CLIENT_IP ip: " + ip);
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
            System.out.println("HTTP_X_FORWARDED_FOR ip: " + ip);
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("X-Real-IP");  
            System.out.println("X-Real-IP ip: " + ip);
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
            System.out.println("getRemoteAddr ip: " + ip);
        } 
        System.out.println("获取客户端ip: " + ip);
        return ip;  
    }
}

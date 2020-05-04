/**
 * 
 */
package com.cofc.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class NetworkUtil {
	final static String BASE = "http://www.baidu.com";

	public static long checkConnectBaidu() {
		return checkConnect(BASE);
	}

	public static long checkConnect(String connurl) {
		URL url;
		HttpURLConnection con;
		int state = -1;
		int urlCount = 0;
		long time = 0;
		// 判断连接是否存在
		if (connurl == null || connurl.length() == 0) {
			throw new RuntimeException("url为空");
		}
		// 连接3次，若是3次都连不上，就抛出异常
		while (urlCount < 3) {
			try {
				// 创建url对象
				long start = new Date().getTime();
				url = new URL(connurl);
				// 打开url连接
				con = (HttpURLConnection) url.openConnection();
				// 获取链接后的状态值
				state = con.getResponseCode();
				long end = new Date().getTime();
				time = end - start;
				System.out.println(state);
				if (state == 200) {
					System.out.println("可用:"+connurl);
					return time;
				} else {
					System.out.println("不可用:"+connurl);
					return 0;
				}
				// break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				urlCount++;
				if (urlCount == 2) {
					// throw new RuntimeException("采集已连接不上");
					System.out.println("url不可用:"+connurl);
					return -1;
				}
				continue;
			}
		}
		return -1;
	}

    public static String getIpAddr(HttpServletRequest request) {
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String connurl = "https://edge163.stream.highwebmedia.com/live-hls/amlst:mila_-sd-cb7fb0b0874591926bcd013f77380ce4745992f7d726971829ef279eb8cd3d52_trns_h264/chunklist_w391471118_b4596000_t64RlBTOjYwLjA=.m3u8";
		System.out.println(NetworkUtil.checkConnect("http://www.baidu.com"));

	}
}

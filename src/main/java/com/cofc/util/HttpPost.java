package com.cofc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.jivesoftware.smackx.bookmarks.BookmarkedConference;
import org.jivesoftware.smackx.bookmarks.Bookmarks;

import com.alibaba.fastjson.JSONObject;

public class HttpPost {
	public static String sendPost(String url, String param) {
		System.out.println("url-" + url);

		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			System.out.println("readUrl-" + realUrl);

			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			// 获取URLConnection对象对应的输出流
			OutputStreamWriter outWriter = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
			out = new PrintWriter(outWriter);
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
				// System.out.print(ex.toString());
			}
		}
		System.out.println("返回！" + result);
		return result;
	}

	public static void main(String[] args) {
		String response = HttpPost.sendPost("http://www.huaren.tv/live/ajaxplay",
				"tvid=101750171144001&part=101802264792005&seo=live");
		       //tvid=221944305497019&part=221944305497019&seo=sportvs
		JSONObject jsonObj = JSONObject.parseObject(response);
		JSONObject jsonObj1 = (JSONObject) jsonObj.get("data");
		String url = (String) jsonObj1.get("url");
		System.out.println(url);
		// url.isEmpty()

		// List<String> list = Arrays.asList(ds);
		// String []data= {};
		// System.out.println(data.length);
		// String []ds=url.split(",");
	}
}

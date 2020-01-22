package com.cofc.timer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import com.cofc.pojo.video.Channel;
import com.cofc.service.video.ChannelService;
import com.cofc.util.NetworkUtil;

public class CheckChannelTimer {

	@Resource
	private ChannelService channelService;

	public static Logger log = Logger.getLogger("CheckChannelTimer");

	public void check() {
		List<Channel> channelList = channelService.getChannelList(0, 3000);
		log.info("check() size:" + channelList.size());
		for (Channel channel : channelList) {
			log.info("Channel:" + channel.getName() + "|" + channel.getPlayUrl());
			long time = NetworkUtil.checkConnectBaidu();
			if (time < 1) {
				try {
					Thread.sleep(3000);
					time = NetworkUtil.checkConnectBaidu();
					if (time < 1) {
						log.info("server network is not available");
						break;
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					break;
				}
			}

			List<String> urlList = new ArrayList<String>();
			urlList.add(channel.getPlayUrl());
			String reserveUrlstmp = channel.getReserveUrls();
			if (null == reserveUrlstmp || "" == reserveUrlstmp) {
				if(null==channel.getPlayUrl()||channel.getPlayUrl()=="") {
					continue;
				}
				long delay = conntectAverageTime(channel.getPlayUrl());
				channel.setDelay(delay);
				channelService.updateChannel(channel);
				continue;
			}
			String[] reserveArray = reserveUrlstmp.replace(";", ",").split(",");
			List<String> reserveList = new ArrayList<>(Arrays.asList(reserveArray));
			urlList.addAll(reserveList);

			long average = 3000;
			for (String url : urlList) {
				if(url.trim().length()<5) {
					continue;
				}
				long tmp = conntectAverageTime(url);
				System.out.println("average:" + tmp + "|" + url);
				if (tmp < average) {
					channel.setPlayUrl(url);
					average = tmp;
				}
			}
			String reserveUrls = "";
			urlList.remove(channel.getPlayUrl());
			for (String url : urlList) {
				if(url.trim().length()<5) {
					continue;
				}
				reserveUrls +=  url.trim()+",";
			}
			channel.setDelay(average);
			channel.setReserveUrls(reserveUrls);
			channelService.updateChannel(channel);
		}
	}

	private static long conntectAverageTime(String url) {
		long total = 0;
		long average = 0;
		for (int i = 0; i < 3; i++) {
			long time = NetworkUtil.checkConnect(url);
			if (time <= 0) {
				time = 10000;
			}
			total += time;
		}
		average = total / 3;
		return average;
	}

	public static void main(String[] args) {

	}
}

package com.cofc.timer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import com.cofc.pojo.video.Anchor;
import com.cofc.service.video.AnchorService;
import com.cofc.util.NetworkUtil;

public class CheckAnchorTimer {

	final static  String BASE="http://www.baidu.com";
	@Resource
	private AnchorService anchorService;

	public static Logger log = Logger.getLogger("CheckAnchorTimer");

	public void check() {
		List<Anchor> anchorList = anchorService.getAnchorList(0, 100);
		log.info("check() size:"+anchorList.size());
		for (Anchor anchor : anchorList) {
			log.info("anchor:"+anchor.getName()+"|"+anchor.getPlayUrl());
			long time = NetworkUtil.checkConnect(BASE);
			if (time < 1) {
				try {
					Thread.sleep(3000);
					time = NetworkUtil.checkConnect(BASE);
					if (time < 1) {
						log.info("server network is not available");
						break;
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			time = NetworkUtil.checkConnect(anchor.getPlayUrl());
			if (time < 1) {
				anchor.setStatus(0);
				//anchorService.updateAnchor(anchor);
				log.info("anchor delete:"+anchor.getId());
				anchorService.delAnchor(anchor.getId());;
			}
		}
	}

	public static void main(String[] args) {
		Collection <String> sss=new ArrayList();
		for(String s:sss) {
			Byte[] avator;	
		}
	
	}
}

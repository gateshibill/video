package com.cofc.service.video;

import java.util.List;

import com.cofc.pojo.video.ChannelType;

public interface ChannelTypeService {
	public void addChannelType(ChannelType channelType);
	public void updateChannelType(ChannelType channelType);
	public List<ChannelType> getChannelTypeList();
	
}

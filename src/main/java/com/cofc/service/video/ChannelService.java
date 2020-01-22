package com.cofc.service.video;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cofc.pojo.video.Channel;

public interface ChannelService {
	public void addChannel(Channel channel);

	public void updateChannel(Channel Channel);

	public void delChannel(@Param("id") Integer id);

	public Channel getChannelById(@Param("id") Integer id);

	public int getChannelCount();

	public Channel getChannelByAssemble(Channel channel);

	public List<Channel> getChannelList(@Param("page") Integer page, @Param("limit") Integer limit);

	public List<Channel> getChannelListByRcmd(@Param("page") Integer page, @Param("limit") Integer limit);

	public List<Channel> getChannelListByType(@Param("type") Integer type, @Param("groupId") Integer groupId,
			@Param("page") Integer page, @Param("limit") Integer limit);

	public List<Channel> getChannelListByAssemble(@Param("type") Integer type, @Param("groupId") Integer groupId,
			@Param("status") Integer status, @Param("name") String name, @Param("page") Integer page,
			@Param("limit") Integer limit);
	public int getChannelCountByAssemble(@Param("type") Integer type, @Param("groupId") Integer groupId,
			@Param("status") Integer status, @Param("name") String name);
}

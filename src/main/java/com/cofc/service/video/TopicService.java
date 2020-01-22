package com.cofc.service.video;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cofc.pojo.video.Topic;

public interface TopicService {
	public void addTopic(Topic topic);
	public void updateTopic(Topic topic);
	public void delTopic(@Param("topicId")Integer topicId);
	public Topic getTopicByTopicId(@Param("topicId")Integer topicId);
	public Topic getTopicByAssemble(@Param("topicName")String name,@Param("source")String posterUrl);	
	public int getTopicCount();
	public List<Topic> getTopicList(@Param("page")Integer page,@Param("limit")Integer limit);
	public List<Topic> getTopicListByType(@Param("topicType")Integer topicType,@Param("page")Integer page,@Param("limit")Integer limit);
	
}

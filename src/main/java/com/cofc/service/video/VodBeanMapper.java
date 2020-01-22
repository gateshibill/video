package com.cofc.service.video;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cofc.pojo.video.VodBean;

public interface VodBeanMapper {
	int deleteByPrimaryKey(Integer vodId);

	int insert(VodBean record);

	int deleteBySourceAndExpire(@Param("source") String source, @Param("expire") Integer expire);

	int insertSelective(VodBean record);

	VodBean selectByPrimaryKey(Integer vodId);

	VodBean getVodByNameAndDirector(@Param("vodName") String vodName, @Param("vodDirector") String vodDirector);

	List<VodBean> getVods(@Param("typeId") Integer typeId, @Param("typeId1") Integer typeId1,
			@Param("groupId") Integer groupId, @Param("page") Integer page, @Param("limit") Integer limit);
	List<VodBean> getSportsVods(@Param("typeId") Integer typeId, @Param("typeId1") Integer typeId1,
			@Param("groupId") Integer groupId, @Param("page") Integer page, @Param("limit") Integer limit);

	List<VodBean> fuzzyQueryVods(@Param("condition") String condition, @Param("page") Integer page,
			@Param("limit") Integer limit);
	
	List<VodBean> getRcmdVods(@Param("actor") String actor,@Param("director") String director,@Param("typeId") Short typeId, @Param("page") Integer page,
			@Param("limit") Integer limit);

	int updateByPrimaryKeySelective(VodBean record);

	int updateByPrimaryKeyWithBLOBs(VodBean record);

	int updateByPrimaryKey(VodBean record);

	int updatePlayUrlByNameAndDirector(VodBean record);
}
package com.cofc.service.video;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cofc.pojo.video.Source;

public interface SourceService {
	public void addSource(Source source);
	public void updateSource(Source source);
	public void delSource(@Param("id")Integer id);
	public Source getSourceById(@Param("id")Integer id);
	public int getSourceCount(@Param("Source")Source source);
	public List<Source> getSourceList(@Param("Source") Source source,@Param("page")Integer page,@Param("limit")Integer limit);
	public List<Source> getSourceListByVodIdAndPiece(@Param("vodId")Integer vodId,@Param("piece")String piece,@Param("page")Integer page,@Param("limit")Integer limit);	
	public Source getSourceListByIpAndLocalIpAndVodIdAndPiece(@Param("ip")String ip,@Param("localIp")String localIp,@Param("vodId")Integer vodId,@Param("piece")String piece);	
	//查询非同一IP的source
	public List<Source> getSourceListByNoIpAndVodIdAndPiece(@Param("ip")String ip,@Param("vodId")Integer vodId,@Param("piece")String piece,@Param("page")Integer page,@Param("limit")Integer limit);
	//查询同一IP的source
	public List<Source> getSourceListByIpAndVodIdAndPiece(@Param("ip")String ip,@Param("vodId")Integer vodId,@Param("piece")String piece,@Param("page")Integer page,@Param("limit")Integer limit);	
}
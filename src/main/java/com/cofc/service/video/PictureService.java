package com.cofc.service.video;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cofc.pojo.video.Picture;

public interface PictureService {
	public void addPicture(Picture Picture);
	public void updatePicture(Picture Picture);
	public void delPicture(@Param("id")Integer id);
	public Picture getPictureById(@Param("id")Integer id);
	public int getPictureCount(@Param("picture")Picture picture);
	public List<Picture> getPictureList(@Param("type") Integer type,@Param("page")Integer page,@Param("limit")Integer limit);
}

package com.cofc.service.video;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cofc.pojo.video.Anchor;

public interface AnchorService {
	public void addAnchor(Anchor anchor);

	public void updateAnchor(Anchor anchor);

	public void delAnchor(@Param("id") Integer id);

	public Anchor getAnchorById(@Param("id") Integer id);

	public Anchor getAnchorByAssemble(@Param("name") String name, @Param("posterUrl") String posterUrl);

	public List<Anchor> getAnchorListByAssemble(@Param("name") String name, @Param("status") Integer status,
			@Param("page") Integer page, @Param("limit") Integer limit);

	public int getAnchorCountByAssemble(@Param("name") String name, @Param("status") Integer status);

	public int getAnchorCount();

	public List<Anchor> getAnchorList(@Param("page") Integer page, @Param("limit") Integer limit);

	public List<Anchor> getAnchorListByType(@Param("type") Integer type, @Param("subType") Integer subType,
			@Param("page") Integer page, @Param("limit") Integer limit);

}

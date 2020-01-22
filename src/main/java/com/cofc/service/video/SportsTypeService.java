package com.cofc.service.video;

import java.util.List;

import com.cofc.pojo.video.SportsType;

public interface SportsTypeService {
	public void addSportsType(SportsType sportsType);
	public void updateSportsType(SportsType sportsType);
	public List<SportsType> getSportsTypeList();
	
}

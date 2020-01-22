package com.cofc.service.video;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cofc.pojo.video.Program;

public interface ProgramService {
	public void addProgram(Program program);
	public void updateProgram(Program program);
	public void updateLeagueProgram(Program program);
	public void updateProgramScore(Program program);
	public void delProgram(@Param("id")Integer id);
	public Program getProgramById(@Param("id")Integer id);
	public Program getLeagueProgram(Program program);
	public int getProgramCount();
	public List<Program> getProgramList(@Param("page")Integer page,@Param("limit")Integer limit);
	public List<Program> getPrograms(@Param("type")Integer type,@Param("page")Integer page,@Param("limit")Integer limit);
	public List<Program> getProgramsByDay(@Param("day")Integer day,@Param("page")Integer page,@Param("limit")Integer limit);
	public List<Program> getLeaguePrograms(@Param("day")Integer day,@Param("event")String event,@Param("page")Integer page,@Param("limit")Integer limit);
	public List<Program> getProgramsByEvent(@Param("event")String event,@Param("page")Integer page,@Param("limit")Integer limit);
	public void delProgramsByEvent(@Param("event")String event);	
	public List<Program> getCurrentPrograms(@Param("page")Integer page,@Param("limit")Integer limit);	
	public List<Program> getHotPrograms(@Param("page")Integer page,@Param("limit")Integer limit);	
	
	public List<Program> getProgramListByAssemble(@Param("name")String name,@Param("channelId")Integer channelId,@Param("page")Integer page,@Param("limit")Integer limit);
	public int getProgramCountByAssemble(@Param("name")String name,@Param("channelId")Integer channelId);
}

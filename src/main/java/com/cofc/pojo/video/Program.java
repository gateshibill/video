package com.cofc.pojo.video;

import java.util.Date;

public class Program {
	private int id;// vod_id
	private int appId=0;//
	private int channelId;//
	private String channelName;
	private String name;// 
	private int type=0;//
	private int rcmd=0;

	private int subType=0;
    private String event;
	private String playUrl="";//
	private String posterUrl="";// 海报，多个用‘;’隔开
	private String source;
	private String content="";// vod_content
	private Date startTime;
	private Date endTime;
	private String host;
	private String homeTeam="";//
	private String guestTeam="";//
	private String homeTeamLogoUrl="";//
	private String guestTeamLogoUrl="";//
	private int homeTeamScore=-1;//负数表述比赛未开始，前端不显示
	private int guestTeamScore=-1;//
	private String playbackUrl="";//
	private String betUrl="";//
	private Date createTime=new Date();
	private Date lastTime=new Date();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getRcmd() {
		return rcmd;
	}

	public void setRcmd(int rcmd) {
		this.rcmd = rcmd;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSubType() {
		return subType;
	}

	public void setSubType(int subType) {
		this.subType = subType;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getPlayUrl() {
		return playUrl;
	}

	public void setPlayUrl(String playUrl) {
		this.playUrl = playUrl;
	}

	public String getPosterUrl() {
		return posterUrl;
	}

	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getGuestTeam() {
		return guestTeam;
	}

	public void setGuestTeam(String guestTeam) {
		this.guestTeam = guestTeam;
	}

	public String getHomeTeamLogoUrl() {
		return homeTeamLogoUrl;
	}

	public void setHomeTeamLogoUrl(String homeTeamLogoUrl) {
		this.homeTeamLogoUrl = homeTeamLogoUrl;
	}

	public String getGuestTeamLogoUrl() {
		return guestTeamLogoUrl;
	}

	public void setGuestTeamLogoUrl(String guestTeamLogoUrl) {
		this.guestTeamLogoUrl = guestTeamLogoUrl;
	}


	public int getHomeTeamScore() {
		return homeTeamScore;
	}

	public void setHomeTeamScore(int homeTeamScore) {
		this.homeTeamScore = homeTeamScore;
	}

	public int getGuestTeamScore() {
		return guestTeamScore;
	}

	public void setGuestTeamScore(int guestTeamScore) {
		this.guestTeamScore = guestTeamScore;
	}

	public String getPlaybackUrl() {
		return playbackUrl;
	}

	public void setPlaybackUrl(String playbackUrl) {
		this.playbackUrl = playbackUrl;
	}

	public String getBetUrl() {
		return betUrl;
	}

	public void setBetUrl(String betUrl) {
		this.betUrl = betUrl;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

}

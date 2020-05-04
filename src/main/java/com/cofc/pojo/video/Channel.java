package com.cofc.pojo.video;

import java.util.Date;

public class Channel {
	private int id;// vod_id
	private int appId=1;// 
	private int rank=0;
	private String name="";// 
	private String category="";// 
	private long delay=0;
	private String level="";//高清/标清
	private String playUrl="";//
	private String reserveUrls="";//
	private String recycleUrls="";//	
	private String posterUrl="";// 海报，多个用‘;’隔开
	private String logoUrl="";
	private String content="";// 
	private int type=0;//  type:1:sports,2:entertainment,3:news,4:military;5.finance,6.child,7.music,8.drama,9:travle,10:other;
	private int groupId=0;//地区china,2,kat,3:abroad,4:satellite,5 cctv,
	private int rcmd=0;
	private int status=0;
	private String country="";// 区域
	private String language="";// 语种
	private Date createTime=new Date();
	private Date lastTime=new Date();
	private String source="";
	private String tvid;
	private String parts;
	private String seo;
	private String serverUrl;
	
	public String detail() {
		StringBuffer sb= new StringBuffer();
		sb.append("id:" + id +"|");
		sb.append("name:" + name +"|");
		sb.append("playUrl:" + playUrl +"|");
		sb.append("posterUrl:" + posterUrl +"|");
		return sb.toString();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAppId() {
		return appId;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlayUrl() {
		return playUrl;
	}
	public void setPlayUrl(String playUrl) {
		this.playUrl = playUrl;
	}
	public String getReserveUrls() {
		return reserveUrls;
	}
	public void setReserveUrls(String reserveUrls) {
		this.reserveUrls = reserveUrls;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getRcmd() {
		return rcmd;
	}
	public void setRcmd(int rcmd) {
		this.rcmd = rcmd;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
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
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	public String getRecycleUrls() {
		return recycleUrls;
	}
	public void setRecycleUrls(String recycleUrls) {
		this.recycleUrls = recycleUrls;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getDelay() {
		return delay;
	}
	public void setDelay(long delay) {
		this.delay = delay;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTvid() {
		return tvid;
	}
	public void setTvid(String tvid) {
		this.tvid = tvid;
	}
	public String getParts() {
		return parts;
	}
	public void setParts(String parts) {
		this.parts = parts;
	}
	
	public String getSeo() {
		return seo;
	}
	public void setSeo(String seo) {
		this.seo = seo;
	}
	public String getServerUrl() {
		return serverUrl;
	}
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

}

package com.cofc.pojo.video;

import java.math.BigDecimal;
import java.util.Date;

public class VodBean {
	private Integer vodId;

	private Short typeId;

	private Short typeId1;

	private Short groupId;

	private String vodName;

	private String vodSub="";

	private String vodEn="";

	private Boolean vodStatus;

	private String vodLetter="";

	private String vodColor="";

	private String vodTag="";

	private String vodClass="";

	private String vodPic="";

	private String vodPicThumb="";

	private String vodPicSlide="";

	private String vodActor="";

	private String vodDirector="";

	private String vodWriter="";

	private String vodBehind="";

	private String vodBlurb="";

	private String vodRemarks="";

	private String vodPubdate="";

	private Integer vodTotal=0;

	private String vodSerial="";

	private String vodTv="";

	private String vodWeekday="";

	private String vodArea="";

	private String vodLang="";

	private String vodYear="";

	private String vodVersion="";

	private String vodState="";

	private String vodAuthor="";

	private String vodJumpurl="";

	private String vodTpl="";

	private String vodTplPlay="";

	private String vodTplDown="";

	private Boolean vodIsend=false;

	private Boolean vodLock=false;

	private Boolean vodLevel=false;

	private Short vodPointsPlay=0;

	private Short vodPointsDown=0;

	private Integer vodHits=0;

	private Integer vodHitsDay=0;

	private Integer vodHitsWeek=0;

	private Integer vodHitsMonth=0;

	private String vodDuration="";

	private Integer vodUp=0;

	private Integer vodDown=0;

	private BigDecimal vodScore=new BigDecimal(0.00);

	private Integer vodScoreAll=0;

	private Integer vodScoreNum=0;

	private Integer vodTime=0;

	private Integer vodTimeAdd=0;

	private Integer vodTimeHits=0;

	private Integer vodTimeMake=0;

	private Short vodTrysee=0;

	private Integer vodDoubanId=0;

	private BigDecimal vodDoubanScore=new BigDecimal(0.00);

	private String vodReurl="";

	private String vodRelVod="";

	private String vodRelArt="";

	private String vodPlayFrom="";

	private String vodPlayServer="";

	private String vodPlayNote="";

	private String vodDownFrom="";

	private String vodDownServer="";

	private String vodDownNote="";

	private String vodPwd="";

	private String vodPwdUrl="";

	private String vodPwdPlay="";

	private String vodPwdPlayUrl="";

	private String vodPwdDown="";

	private String vodPwdDownUrl="";

	private Boolean vodCopyright=false;

	private Short vodPoints=0;

	private String vodContent="";

	private String vodPlayUrl="";

	private String vodDownUrl="";
	private Date createTime=new Date();	
	private Date lastTime;
	private String hostTeam;
	private String guestTeam;
	private String source;
	private int hot;
	
	public Integer getVodId() {
		return vodId;
	}

	public void setVodId(Integer vodId) {
		this.vodId = vodId;
	}

	public Short getTypeId() {
		return typeId;
	}

	public void setTypeId(Short typeId) {
		this.typeId = typeId;
	}

	public Short getTypeId1() {
		return typeId1;
	}

	public void setTypeId1(Short typeId1) {
		this.typeId1 = typeId1;
	}

	public Short getGroupId() {
		return groupId;
	}

	public void setGroupId(Short groupId) {
		this.groupId = groupId;
	}

	public String getVodName() {
		return vodName;
	}

	public void setVodName(String vodName) {
		this.vodName = vodName == null ? null : vodName.trim();
	}

	public String getVodSub() {
		return vodSub;
	}

	public void setVodSub(String vodSub) {
		this.vodSub = vodSub == null ? null : vodSub.trim();
	}

	public String getVodEn() {
		return vodEn;
	}

	public void setVodEn(String vodEn) {
		this.vodEn = vodEn == null ? null : vodEn.trim();
	}

	public Boolean getVodStatus() {
		return vodStatus;
	}

	public void setVodStatus(Boolean vodStatus) {
		this.vodStatus = vodStatus;
	}

	public String getVodLetter() {
		return vodLetter;
	}

	public void setVodLetter(String vodLetter) {
		this.vodLetter = vodLetter == null ? null : vodLetter.trim();
	}

	public String getVodColor() {
		return vodColor;
	}

	public void setVodColor(String vodColor) {
		this.vodColor = vodColor == null ? null : vodColor.trim();
	}

	public String getVodTag() {
		return vodTag;
	}

	public void setVodTag(String vodTag) {
		this.vodTag = vodTag == null ? null : vodTag.trim();
	}

	public String getVodClass() {
		return vodClass;
	}

	public void setVodClass(String vodClass) {
		this.vodClass = vodClass == null ? null : vodClass.trim();
	}

	public String getVodPic() {
		return vodPic;
	}

	public void setVodPic(String vodPic) {
		this.vodPic = vodPic == null ? null : vodPic.trim();
	}

	public String getVodPicThumb() {
		return vodPicThumb;
	}

	public void setVodPicThumb(String vodPicThumb) {
		this.vodPicThumb = vodPicThumb == null ? null : vodPicThumb.trim();
	}

	public String getVodPicSlide() {
		return vodPicSlide;
	}

	public void setVodPicSlide(String vodPicSlide) {
		this.vodPicSlide = vodPicSlide == null ? null : vodPicSlide.trim();
	}

	public String getVodActor() {
		return vodActor;
	}

	public void setVodActor(String vodActor) {
		this.vodActor = vodActor == null ? null : vodActor.trim();
	}

	public String getVodDirector() {
		return vodDirector;
	}

	public void setVodDirector(String vodDirector) {
		this.vodDirector = vodDirector == null ? null : vodDirector.trim();
	}

	public String getVodWriter() {
		return vodWriter;
	}

	public void setVodWriter(String vodWriter) {
		this.vodWriter = vodWriter == null ? null : vodWriter.trim();
	}

	public String getVodBehind() {
		return vodBehind;
	}

	public void setVodBehind(String vodBehind) {
		this.vodBehind = vodBehind == null ? null : vodBehind.trim();
	}

	public String getVodBlurb() {
		return vodBlurb;
	}

	public void setVodBlurb(String vodBlurb) {
		this.vodBlurb = vodBlurb == null ? null : vodBlurb.trim();
	}

	public String getVodRemarks() {
		return vodRemarks;
	}

	public void setVodRemarks(String vodRemarks) {
		this.vodRemarks = vodRemarks == null ? null : vodRemarks.trim();
	}

	public String getVodPubdate() {
		return vodPubdate;
	}

	public void setVodPubdate(String vodPubdate) {
		this.vodPubdate = vodPubdate == null ? null : vodPubdate.trim();
	}

	public Integer getVodTotal() {
		return vodTotal;
	}

	public void setVodTotal(Integer vodTotal) {
		this.vodTotal = vodTotal;
	}

	public String getVodSerial() {
		return vodSerial;
	}

	public void setVodSerial(String vodSerial) {
		this.vodSerial = vodSerial == null ? null : vodSerial.trim();
	}

	public String getVodTv() {
		return vodTv;
	}

	public void setVodTv(String vodTv) {
		this.vodTv = vodTv == null ? null : vodTv.trim();
	}

	public String getVodWeekday() {
		return vodWeekday;
	}

	public void setVodWeekday(String vodWeekday) {
		this.vodWeekday = vodWeekday == null ? null : vodWeekday.trim();
	}

	public String getVodArea() {
		return vodArea;
	}

	public void setVodArea(String vodArea) {
		this.vodArea = vodArea == null ? null : vodArea.trim();
	}

	public String getVodLang() {
		return vodLang;
	}

	public void setVodLang(String vodLang) {
		this.vodLang = vodLang == null ? null : vodLang.trim();
	}

	public String getVodYear() {
		return vodYear;
	}

	public void setVodYear(String vodYear) {
		this.vodYear = vodYear == null ? null : vodYear.trim();
	}

	public String getVodVersion() {
		return vodVersion;
	}

	public void setVodVersion(String vodVersion) {
		this.vodVersion = vodVersion == null ? null : vodVersion.trim();
	}

	public String getVodState() {
		return vodState;
	}

	public void setVodState(String vodState) {
		this.vodState = vodState == null ? null : vodState.trim();
	}

	public String getVodAuthor() {
		return vodAuthor;
	}

	public void setVodAuthor(String vodAuthor) {
		this.vodAuthor = vodAuthor == null ? null : vodAuthor.trim();
	}

	public String getVodJumpurl() {
		return vodJumpurl;
	}

	public void setVodJumpurl(String vodJumpurl) {
		this.vodJumpurl = vodJumpurl == null ? null : vodJumpurl.trim();
	}

	public String getVodTpl() {
		return vodTpl;
	}

	public void setVodTpl(String vodTpl) {
		this.vodTpl = vodTpl == null ? null : vodTpl.trim();
	}

	public String getVodTplPlay() {
		return vodTplPlay;
	}

	public void setVodTplPlay(String vodTplPlay) {
		this.vodTplPlay = vodTplPlay == null ? null : vodTplPlay.trim();
	}

	public String getVodTplDown() {
		return vodTplDown;
	}

	public void setVodTplDown(String vodTplDown) {
		this.vodTplDown = vodTplDown == null ? null : vodTplDown.trim();
	}

	public Boolean getVodIsend() {
		return vodIsend;
	}

	public void setVodIsend(Boolean vodIsend) {
		this.vodIsend = vodIsend;
	}

	public Boolean getVodLock() {
		return vodLock;
	}

	public void setVodLock(Boolean vodLock) {
		this.vodLock = vodLock;
	}

	public Boolean getVodLevel() {
		return vodLevel;
	}

	public void setVodLevel(Boolean vodLevel) {
		this.vodLevel = vodLevel;
	}

	public Short getVodPointsPlay() {
		return vodPointsPlay;
	}

	public void setVodPointsPlay(Short vodPointsPlay) {
		this.vodPointsPlay = vodPointsPlay;
	}

	public Short getVodPointsDown() {
		return vodPointsDown;
	}

	public void setVodPointsDown(Short vodPointsDown) {
		this.vodPointsDown = vodPointsDown;
	}

	public Integer getVodHits() {
		return vodHits;
	}

	public void setVodHits(Integer vodHits) {
		this.vodHits = vodHits;
	}

	public Integer getVodHitsDay() {
		return vodHitsDay;
	}

	public void setVodHitsDay(Integer vodHitsDay) {
		this.vodHitsDay = vodHitsDay;
	}

	public Integer getVodHitsWeek() {
		return vodHitsWeek;
	}

	public void setVodHitsWeek(Integer vodHitsWeek) {
		this.vodHitsWeek = vodHitsWeek;
	}

	public Integer getVodHitsMonth() {
		return vodHitsMonth;
	}

	public void setVodHitsMonth(Integer vodHitsMonth) {
		this.vodHitsMonth = vodHitsMonth;
	}

	public String getVodDuration() {
		return vodDuration;
	}

	public void setVodDuration(String vodDuration) {
		this.vodDuration = vodDuration == null ? null : vodDuration.trim();
	}

	public Integer getVodUp() {
		return vodUp;
	}

	public void setVodUp(Integer vodUp) {
		this.vodUp = vodUp;
	}

	public Integer getVodDown() {
		return vodDown;
	}

	public void setVodDown(Integer vodDown) {
		this.vodDown = vodDown;
	}

	public BigDecimal getVodScore() {
		return vodScore;
	}

	public void setVodScore(BigDecimal vodScore) {
		this.vodScore = vodScore;
	}

	public Integer getVodScoreAll() {
		return vodScoreAll;
	}

	public void setVodScoreAll(Integer vodScoreAll) {
		this.vodScoreAll = vodScoreAll;
	}

	public Integer getVodScoreNum() {
		return vodScoreNum;
	}

	public void setVodScoreNum(Integer vodScoreNum) {
		this.vodScoreNum = vodScoreNum;
	}

	public Integer getVodTime() {
		return vodTime;
	}

	public void setVodTime(Integer vodTime) {
		this.vodTime = vodTime;
	}

	public Integer getVodTimeAdd() {
		return vodTimeAdd;
	}

	public void setVodTimeAdd(Integer vodTimeAdd) {
		this.vodTimeAdd = vodTimeAdd;
	}

	public Integer getVodTimeHits() {
		return vodTimeHits;
	}

	public void setVodTimeHits(Integer vodTimeHits) {
		this.vodTimeHits = vodTimeHits;
	}

	public Integer getVodTimeMake() {
		return vodTimeMake;
	}

	public void setVodTimeMake(Integer vodTimeMake) {
		this.vodTimeMake = vodTimeMake;
	}

	public Short getVodTrysee() {
		return vodTrysee;
	}

	public void setVodTrysee(Short vodTrysee) {
		this.vodTrysee = vodTrysee;
	}

	public Integer getVodDoubanId() {
		return vodDoubanId;
	}

	public void setVodDoubanId(Integer vodDoubanId) {
		this.vodDoubanId = vodDoubanId;
	}

	public BigDecimal getVodDoubanScore() {
		return vodDoubanScore;
	}

	public void setVodDoubanScore(BigDecimal vodDoubanScore) {
		this.vodDoubanScore = vodDoubanScore;
	}

	public String getVodReurl() {
		return vodReurl;
	}

	public void setVodReurl(String vodReurl) {
		this.vodReurl = vodReurl == null ? null : vodReurl.trim();
	}

	public String getVodRelVod() {
		return vodRelVod;
	}

	public void setVodRelVod(String vodRelVod) {
		this.vodRelVod = vodRelVod == null ? null : vodRelVod.trim();
	}

	public String getVodRelArt() {
		return vodRelArt;
	}

	public void setVodRelArt(String vodRelArt) {
		this.vodRelArt = vodRelArt == null ? null : vodRelArt.trim();
	}

	public String getVodPlayFrom() {
		return vodPlayFrom;
	}

	public void setVodPlayFrom(String vodPlayFrom) {
		this.vodPlayFrom = vodPlayFrom == null ? null : vodPlayFrom.trim();
	}

	public String getVodPlayServer() {
		return vodPlayServer;
	}

	public void setVodPlayServer(String vodPlayServer) {
		this.vodPlayServer = vodPlayServer == null ? null : vodPlayServer.trim();
	}

	public String getVodPlayNote() {
		return vodPlayNote;
	}

	public void setVodPlayNote(String vodPlayNote) {
		this.vodPlayNote = vodPlayNote == null ? null : vodPlayNote.trim();
	}

	public String getVodDownFrom() {
		return vodDownFrom;
	}

	public void setVodDownFrom(String vodDownFrom) {
		this.vodDownFrom = vodDownFrom == null ? null : vodDownFrom.trim();
	}

	public String getVodDownServer() {
		return vodDownServer;
	}

	public void setVodDownServer(String vodDownServer) {
		this.vodDownServer = vodDownServer == null ? null : vodDownServer.trim();
	}

	public String getVodDownNote() {
		return vodDownNote;
	}

	public void setVodDownNote(String vodDownNote) {
		this.vodDownNote = vodDownNote == null ? null : vodDownNote.trim();
	}

	public String getVodPwd() {
		return vodPwd;
	}

	public void setVodPwd(String vodPwd) {
		this.vodPwd = vodPwd == null ? null : vodPwd.trim();
	}

	public String getVodPwdUrl() {
		return vodPwdUrl;
	}

	public void setVodPwdUrl(String vodPwdUrl) {
		this.vodPwdUrl = vodPwdUrl == null ? null : vodPwdUrl.trim();
	}

	public String getVodPwdPlay() {
		return vodPwdPlay;
	}

	public void setVodPwdPlay(String vodPwdPlay) {
		this.vodPwdPlay = vodPwdPlay == null ? null : vodPwdPlay.trim();
	}

	public String getVodPwdPlayUrl() {
		return vodPwdPlayUrl;
	}

	public void setVodPwdPlayUrl(String vodPwdPlayUrl) {
		this.vodPwdPlayUrl = vodPwdPlayUrl == null ? null : vodPwdPlayUrl.trim();
	}

	public String getVodPwdDown() {
		return vodPwdDown;
	}

	public void setVodPwdDown(String vodPwdDown) {
		this.vodPwdDown = vodPwdDown == null ? null : vodPwdDown.trim();
	}

	public String getVodPwdDownUrl() {
		return vodPwdDownUrl;
	}

	public void setVodPwdDownUrl(String vodPwdDownUrl) {
		this.vodPwdDownUrl = vodPwdDownUrl == null ? null : vodPwdDownUrl.trim();
	}

	public Boolean getVodCopyright() {
		return vodCopyright;
	}

	public void setVodCopyright(Boolean vodCopyright) {
		this.vodCopyright = vodCopyright;
	}

	public Short getVodPoints() {
		return vodPoints;
	}

	public void setVodPoints(Short vodPoints) {
		this.vodPoints = vodPoints;
	}

	public String getVodContent() {
		return vodContent;
	}

	public void setVodContent(String vodContent) {
		this.vodContent = vodContent;
	}

	public String getVodPlayUrl() {
		return vodPlayUrl;
	}

	public void setVodPlayUrl(String vodPlayUrl) {
		this.vodPlayUrl = vodPlayUrl;
	}

	public String getVodDownUrl() {
		return vodDownUrl;
	}

	public void setVodDownUrl(String vodDownUrl) {
		this.vodDownUrl = vodDownUrl;
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

	public String getHostTeam() {
		return hostTeam;
	}

	public void setHostTeam(String hostTeam) {
		this.hostTeam = hostTeam;
	}

	public String getGuestTeam() {
		return guestTeam;
	}

	public void setGuestTeam(String guestTeam) {
		this.guestTeam = guestTeam;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getHot() {
		return hot;
	}

	public void setHot(int hot) {
		this.hot = hot;
	}

}
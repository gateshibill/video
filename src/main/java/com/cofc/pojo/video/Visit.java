package com.cofc.pojo.video;

import java.util.Date;

public class Visit {
   private int visitId;
   private int userId;
   private String deviceId;
   private int visitIp;
   private String publicIp;
   private String localIp;
   private int visitLy;
   private Date createTime;   
   private Date visitTime;
public int getVisitId() {
	return visitId;
}
public void setVisitId(int visitId) {
	this.visitId = visitId;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public String getDeviceId() {
	return deviceId;
}
public void setDeviceId(String deviceId) {
	this.deviceId = deviceId;
}
public int getVisitIp() {
	return visitIp;
}
public void setVisitIp(int visitIp) {
	this.visitIp = visitIp;
}
public String getPublicIp() {
	return publicIp;
}
public void setPublicIp(String publicIp) {
	this.publicIp = publicIp;
	
}
public String getLocalIp() {
	return localIp;
}
public void setLocalIp(String localIp) {
	this.localIp = localIp;
}
public int getVisitLy() {
	return visitLy;
}
public void setVisitLy(int visitLy) {
	this.visitLy = visitLy;
}
public Date getVisitTime() {
	return visitTime;
}
public void setVisitTime(Date visitTime) {
	this.visitTime = visitTime;
}
public Date getCreateTime() {
	return createTime;
}
public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}

   
}

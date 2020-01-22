package com.cofc.pojo.behavior;

import java.util.Date;



public class PeerAction {
	private int id;
	private int appId;
	private int userId;
	private String deviceId;
	private String deviceBrand;	
	private String clientIp;
	private String log;
	private int pnodeId;
	private String fromIp;
	private String VodName;
	private String piece;
	private String text;
	private int size;
	private String protocol;
	private Date createTime;
	private Date endTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	
	public int getAppId() {
		return appId;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceBrand() {
		return deviceBrand;
	}
	public void setDeviceBrand(String deviceBrand) {
		this.deviceBrand = deviceBrand;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public int getPnodeId() {
		return pnodeId;
	}
	public void setPnodeId(int pnodeId) {
		this.pnodeId = pnodeId;
	}
	public String getFromIp() {
		return fromIp;
	}
	public void setFromIp(String fromIp) {
		this.fromIp = fromIp;
	}
	public String getVodName() {
		return VodName;
	}
	public void setVodName(String vodName) {
		VodName = vodName;
	}
	public String getPiece() {
		return piece;
	}
	public void setPiece(String piece) {
		this.piece = piece;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}

package com.cofc.pojo.behavior;

import java.util.Date;


/**
 * 记录用户行为 1.分析用户名片访问情况 2.用户分享记录； 可以通过这张表分析出个人对公司、产品,还是个人感兴趣；
 * 
 * @author Administrator
 *
 */
public class ClientAction {
	private int id;
	private int appId;// 应用ID；
	private String deviceId;
	private String deviceBrand;
	private String deviceIp;	
	private int userId;
    private String version;
	private int fromId;// 记录 ，用户是自己进来点击的，还是通过分享进来的，这里可以分享客户的主动性
	private int fromWay;//渠道：0为小程序，1为网站，2为APP,3为公众号
	private int pageId;
	private String pageName;
	private int columnId;
	private String columnName;
	private int objectId;
	private String objectName;
	private int actionId;//1.访问浏览；2.点击;3.下拉；4.上拉；5.点赞；6.评价；7.阅读；8.播放；
	private String actionName;
	private int subActionId;
	private String subActionName;	
	private int superId;
	private Date createTime=new Date();
	
	/**
	 * 1.某部电影的点击次数，时间段点击，用户分布，来源；
	 * 2.某个功能的点击，时间段，用户分布，来源；
	 * 3.某个用户使用的行为，使用哪个功能多；
	 * 4.哪个渠道的用户多，
	 * 5.
	 */

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


	public String getDeviceId() {
		return deviceId;
	}


	public String getDeviceIp() {
		return deviceIp;
	}


	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
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


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}


	public int getFromId() {
		return fromId;
	}


	public void setFromId(int fromId) {
		this.fromId = fromId;
	}


	public int getFromWay() {
		return fromWay;
	}


	public void setFromWay(int fromWay) {
		this.fromWay = fromWay;
	}


	public int getPageId() {
		return pageId;
	}


	public void setPageId(int pageId) {
		this.pageId = pageId;
	}


	public String getPageName() {
		return pageName;
	}


	public void setPageName(String pageName) {
		this.pageName = pageName;
	}


	public int getColumnId() {
		return columnId;
	}


	public void setColumnId(int columnId) {
		this.columnId = columnId;
	}


	public String getColumnName() {
		return columnName;
	}


	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}


	public int getObjectId() {
		return objectId;
	}


	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}


	public String getObjectName() {
		return objectName;
	}


	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}


	public int getActionId() {
		return actionId;
	}


	public void setActionId(int actionId) {
		this.actionId = actionId;
	}


	public String getActionName() {
		return actionName;
	}


	public void setActionName(String actionName) {
		this.actionName = actionName;
	}


	public int getSubActionId() {
		return subActionId;
	}


	public void setSubActionId(int subActionId) {
		this.subActionId = subActionId;
	}


	public String getSubActionName() {
		return subActionName;
	}


	public void setSubActionName(String subActionName) {
		this.subActionName = subActionName;
	}


	public int getSuperId() {
		return superId;
	}


	public void setSuperId(int superId) {
		this.superId = superId;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

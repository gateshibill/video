package com.cofc.controller.video;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.cofc.intercepter.LoginHandlerIntercepter;
import com.cofc.pojo.BackUser;
import com.cofc.pojo.behavior.ClientAction;
import com.cofc.pojo.behavior.ClientLog;
import com.cofc.pojo.behavior.PeerAction;
//import com.cofc.pojo.aida.FaceGame;
//import com.cofc.pojo.aida.FaceGameBehavior;
import com.cofc.pojo.video.Asset;
import com.cofc.pojo.video.Channel;
import com.cofc.pojo.video.Column;
import com.cofc.pojo.video.Program;
import com.cofc.pojo.video.Source;
import com.cofc.pojo.video.UserBean;
import com.cofc.pojo.video.VodBean;
import com.cofc.service.BackRoleService;
import com.cofc.service.BackUserService;
import com.cofc.service.behavior.ClientActionService;
import com.cofc.service.behavior.ClientLogService;
import com.cofc.service.behavior.PeerActionService;
import com.cofc.service.video.AssetService;
import com.cofc.service.video.ChannelService;
import com.cofc.service.video.ColumnService;
import com.cofc.service.video.ProgramService;
import com.cofc.service.video.UserBeanService;
import com.cofc.service.video.VodBeanService;
import com.cofc.util.BaseUtil;
import com.cofc.util.CommonUtil;
import com.cofc.util.JsonUtil;
import com.cofc.util.MD5Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/client")
public class ClientController extends BaseUtil {

	@Resource
	private BackUserService bUserService;

	@Resource
	private BackRoleService backRoleService;

	@Resource
	private AssetService assetService;
	@Resource
	private ColumnService columnService;

	@Resource
	private UserBeanService userService;

	@Resource
	private VodBeanService vodService;
	@Resource
	private ChannelService channelService;
	@Resource
	private ProgramService programService;
	@Resource
	private ClientActionService clientActionService;
	@Resource
	private PeerActionService peerActionService;
	@Resource
	private ClientLogService clientLogService;

	public static Logger log = Logger.getLogger("ClientController");

	// 终端：

	// 1.开机连接,登陆，验证
	@RequestMapping("/startup")
	public void startup(HttpServletRequest request, HttpServletResponse response, Source fgb) throws IOException {
		// fgb.setIp(getIpAddr(request));
//		log.info(fgb.getId() + "(" + fgb.getName() + ")" + "上报:" + fgb.getType() + "|"
//				+ fgb.getContent() + "|" + fgb.getTime());
//		
//		fgb.setCreateTime(new Date());
//		faceGameBehaviorService.addFaceGameBehavior(fgb);
//		output(response, JsonUtil.buildSuccessJson("0", "succes"));
	}

	// 2.获得portal门户和栏目信息
	@RequestMapping("/getColumnList")
	public void getColumnList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Column> columnList = columnService.getColumnList(0);
		output(response, JsonUtil.buildCustomJson("0", "succes", columnList));
	}

	// 2.1 音乐栏目
	@RequestMapping("/getSongColumnList")
	public void getSongSColumnList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Column> columnList = columnService.getColumnList(1);
		output(response, JsonUtil.buildCustomJson("0", "succes", columnList));
	}

	// 2.2 获得电影
	@RequestMapping("/getMovies")
	public void getMovies(HttpServletRequest request, HttpServletResponse response, Integer columnId, Integer page,
			Integer limit) throws IOException {
		if (page == null) {
			page = 0;
		}
		if (limit == null) {
			limit = 10;
		}
		List<Asset> assetList = assetService.getAssetListByColumnId(columnId, page, limit);
		output(response, JsonUtil.buildCustomJson("0", "succes", assetList));
	}

	// 2.3 获得歌曲，
	@RequestMapping("/getSongs")
	public void getSongs(HttpServletRequest request, HttpServletResponse response, Integer columnId)
			throws IOException {
		List<Asset> assetList = assetService.getAssetListByColumnId(columnId, 1, 10);
		output(response, JsonUtil.buildCustomJson("0", "succes", assetList));
	}

	// 4.校验信息有效性,被VS调用
	@RequestMapping("/authToken")
	public void authToken(HttpServletRequest request, HttpServletResponse response, Source fg) throws IOException {

		output(response, JsonUtil.bulidObjectJson(fg));
	}

	// 5.终端心跳，人机交互安全验证，更新有效期
	@RequestMapping("/heartbeat")
	public void heartbeat(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// output(response, JsonUtil.bulidObjectJson(fg));
	}

	// 6.账号生成，通过设备信息生成
	@RequestMapping("/addAccount")
	public void addAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// output(response, JsonUtil.bulidObjectJson(fg));
	}

	// 7.账号增删改查
	@RequestMapping("/getAccount")
	public void getAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// output(response, JsonUtil.bulidObjectJson(fg));
	}

	// 8.观看历史
	@RequestMapping("/getHistoryMoives")
	public void getHistoryMoives(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// output(response, JsonUtil.bulidObjectJson(fg));
	}

	// 8.收藏
	@RequestMapping("/getFavoriteMoives")
	public void getFavoriteMoives(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// output(response, JsonUtil.bulidObjectJson(fg));
	}

	// 9.用户登录
	@RequestMapping("/login")
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		InputStream is = request.getInputStream();
		String message = IOUtils.toString(is, "UTF-8");
		System.out.println("message:" + message);
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
			UserBean ub = gson.fromJson(message, UserBean.class);
			ub.setUserRandom(new Date().toString());
			UserBean user = userService.login(ub.getUserPhone(), ub.getUserEmail(), MD5Util.MD5Encode(ub.getUserPwd(), "utf-8"));
			if (null != user) {
				user.setUserRandom(makeToken());
				LoginHandlerIntercepter.putUserToken(user.getUserId() + "", user.getUserRandom());
				output(response, JsonUtil.objectToJson("200", user));
			} else {
				output(response, JsonUtil.buildFalseJson("201", "user is not exist or password error!"));
			}
		} catch (

		Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			output(response, JsonUtil.buildSuccessJson("405", "error"));
		}
	}

	// 10.用户注册
	@RequestMapping("/register")
	public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		InputStream is = request.getInputStream();
		String message = IOUtils.toString(is, "UTF-8");
		System.out.println("message:" + message);
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
			UserBean ub = gson.fromJson(message, UserBean.class);
			UserBean user = userService.getUserByUserPhone(ub.getUserPhone());
			if (null != user) {
				output(response, JsonUtil.buildSuccessJson("201", "phone have been already registered!"));
				return;
			}
			ub.setUserPwd(MD5Util.MD5Encode(ub.getUserPwd(), "utf-8"));
			userService.insert(ub);
			output(response, JsonUtil.objectToJson("200", ub));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			output(response, JsonUtil.buildSuccessJson("405", "error"));
		}
	}

	// 11.行为上报
	@RequestMapping("/clientReport")
	public void clientReport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		InputStream is = request.getInputStream();
		String message = IOUtils.toString(is, "UTF-8");
		System.out.println("message:" + message);
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
			ClientAction clientAction = gson.fromJson(message, ClientAction.class);
			clientAction.setDeviceIp(CommonUtil.getIpAddr(request));
			clientActionService.insert(clientAction);
			output(response, JsonUtil.buildFalseJson("200", "success"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			output(response, JsonUtil.buildSuccessJson("405", "error"));
		}
	}

	// 12.peer上报
	@RequestMapping("/peerReport")
	public void peerReport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		InputStream is = request.getInputStream();
		String message = IOUtils.toString(is, "UTF-8");
		System.out.println("message:" + message);
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
			PeerAction peerAction = gson.fromJson(message, PeerAction.class);
			peerAction.setClientIp(CommonUtil.getIpAddr(request));
			peerActionService.insert(peerAction);
			output(response, JsonUtil.buildFalseJson("200", "success"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			output(response, JsonUtil.buildSuccessJson("405", "error"));
		}
	}

	// 13.peer上报
	@RequestMapping("/logReport")
	public void logReport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		InputStream is = request.getInputStream();
		String message = IOUtils.toString(is, "UTF-8");
		System.out.println("message:" + message);
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
			ClientLog clientLog = gson.fromJson(message, ClientLog.class);
			clientLog.setClientIp(CommonUtil.getIpAddr(request));
			clientLogService.insert(clientLog);
			output(response, JsonUtil.buildFalseJson("200", "success"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			output(response, JsonUtil.buildSuccessJson("405", "error"));
		}
	}

	public String makeToken() {
		String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";
//	         try {
//	            MessageDigest md = MessageDigest.getInstance("md5");
//	            byte md5[] =  md.digest(token.getBytes());
//	            BASE64Encoder encoder = new BASE64Encoder();
//	            return encoder.encode(md5);
//	        } catch (NoSuchAlgorithmException e) {
//	            // TODO Auto-generated catch block
//	            e.printStackTrace();
//	        }
		return token;
	}
}

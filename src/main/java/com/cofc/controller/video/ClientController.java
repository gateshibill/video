package com.cofc.controller.video;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import com.cofc.pojo.video.ChargeCard;
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
import com.cofc.service.video.ChargeCardService;
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
	@Resource
	private ChargeCardService chargeCardService;

	public static Logger log = Logger.getLogger("ClientController");

	// 充值
	@RequestMapping("/charge")
	public void charge(HttpServletRequest request, HttpServletResponse response, Integer cardId, Integer userId)
			throws IOException {
		log.info("charge() " + cardId + "/" + userId);
		if (null == cardId || null == userId) {
			output(response, JsonUtil.buildFalseJson("1", "卡号和用户ID不能为空"));
			return;
		}
		try {
			// 1.卡是否存在；
			ChargeCard chargeCard = chargeCardService.getChargeCardByCardId(cardId);
			if (null == chargeCard) {
				output(response, JsonUtil.buildFalseJson("2", "卡不存在"));
				return;
			} else if (0 != chargeCard.getUsed()) {
				output(response, JsonUtil.buildFalseJson("3", "卡已经使用过"));
				return;
			} else if (0 != chargeCard.getValidate()) {
				output(response, JsonUtil.buildFalseJson("4", "卡"));
				return;
			}
			// 获取用户当前到期时间，需要在当前时间基础上增加
			UserBean user = userService.getUserByUserId(userId);
			Date expire = user.getVipExpire();
			if (null == expire || expire.before(new Date()))
				;
			{
				expire = new Date();
			}
			int type = chargeCard.getType();

			Calendar calendar = new GregorianCalendar();
			calendar.setTime(expire);
			switch (type) {
			case 0:
				calendar.add(Calendar.DATE, 3);
				break;
			case 1:// 月卡
				calendar.add(Calendar.DAY_OF_MONTH, 1);
				break;
			case 2:// 季卡
				calendar.add(Calendar.DAY_OF_MONTH, 3);
				break;
			case 3:// 年卡
				calendar.add(Calendar.YEAR, 1);
				break;
			default:
				System.out.println("card type is unknown:" + type);
				break;
			}
			expire = calendar.getTime();
			chargeCard.setExpire(expire);
			chargeCard.setUsedTime(new Date());
			chargeCard.setUsed(1);
			chargeCardService.updateChargeCard(chargeCard);

			// 更新用戶到期時間
			user.setVipExpire(expire);
			userService.updateByPrimaryKeySelective(user);

			output(response, JsonUtil.buildFalseJson("200", "success"));
		} catch (Exception e) {
			e.printStackTrace();
			output(response, JsonUtil.buildSuccessJson("405", "error"));
		}
	}

	// 获取用户正在使用的充值卡
	@RequestMapping("/getUsingChargeCard")
	public void getUsingChargeCard(HttpServletRequest request, HttpServletResponse response, Integer userId)
			throws IOException {
		log.info("getUsingChargeCard() " + userId);
		if (null == userId) {
			output(response, JsonUtil.buildFalseJson("1", "卡号和用户ID不能为空"));
			return;
		}
		try {
			ChargeCard chargeCard = chargeCardService.getChargeCardByUserId(userId);
			output(response, JsonUtil.buildSuccessJson("0", "success", chargeCard));
		} catch (Exception e) {
			e.printStackTrace();
			output(response, JsonUtil.buildSuccessJson("405", "error"));
		}
	}

	// 用户登录
	@RequestMapping("/login")
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		InputStream is = request.getInputStream();
		String message = IOUtils.toString(is, "UTF-8");
		System.out.println("message:" + message);
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
			UserBean ub = gson.fromJson(message, UserBean.class);
			// ub.setUserRandom(new Date().toString());
			if (null == ub) {
				output(response, JsonUtil.buildFalseJson("1", "传入用户为空"));
				return;
			}
			UserBean user = null;
			if (null != ub.getUserPhone() || !ub.getUserPhone().isEmpty()) {
				user = userService.login(ub.getUserPhone(), ub.getUserEmail(),
						MD5Util.MD5Encode(ub.getUserPwd(), "utf-8"));

			} else if (null != ub.getUserId()) {
				user = userService.getUserByUserId(ub.getUserId());
			}

			if (null != user) {
				user.setUserRandom(makeToken());
				LoginHandlerIntercepter.putUserToken(user.getUserId() + "", user.getUserRandom());
				output(response, JsonUtil.objectToJson("200", user));
			} else {
				output(response, JsonUtil.buildFalseJson("201", "user is not exist or password error!"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			output(response, JsonUtil.buildSuccessJson("405", "error"));
		}
	}

	// 用户注册
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

	// 访客
	@RequestMapping("/guest")
	public void guest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		InputStream is = request.getInputStream();
		String message = IOUtils.toString(is, "UTF-8");
		System.out.println("message:" + message);
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
			UserBean ub = gson.fromJson(message, UserBean.class);
			if (null == ub) {
				output(response, JsonUtil.buildFalseJson("1", "传入用户为空"));
				return;
			}
			UserBean user = userService.getUserByDeviceId(ub.getDeviceId());
			if (null != user) {
				output(response, JsonUtil.objectToJson("200", user));
				return;
			} else {
				userService.insert(ub);// 通过数据库生成用户ID
				output(response, JsonUtil.objectToJson("200", ub));
			}

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

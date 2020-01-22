package com.cofc.util;

import java.io.IOException;
import java.net.InetAddress;
import java.security.KeyStore;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.ReconnectionManager;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.chat2.IncomingChatMessageListener;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.filter.StanzaIdFilter;
import org.jivesoftware.smack.filter.StanzaTypeFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration.Builder;
import org.jivesoftware.smackx.iqregister.AccountManager;
import org.jivesoftware.smackx.ping.PingFailedListener;
import org.jivesoftware.smackx.ping.PingManager;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.jid.parts.Localpart;

import com.alibaba.fastjson.JSONObject;

import org.jxmpp.jid.DomainBareJid;

public class SmackClient {
	public static void main(String[] args) {

		System.out.println("start");

		// AbstractXMPPConnection connection = null;
		connect();
	}

	private static void connect() {
		try {
			InetAddress inetAddress = InetAddress.getByName("im.gycdn1.com");// 输入openfire 的ip
			SmackConfiguration.DEBUG=true;
			
			XMPPTCPConnectionConfiguration.Builder configBuilder = XMPPTCPConnectionConfiguration.builder();
			//configBuilder.setUsernameAndPassword("yinxiaobao", "123456");// 以存在的账户 登入
			configBuilder.setXmppDomain("myopenfire");
			configBuilder.setHostAddress(inetAddress);
			configBuilder.setPort(5222);
			configBuilder.setSecurityMode(SecurityMode.disabled);
			configBuilder.setCompressionEnabled(true);
			// configBuilder.setSendPresence(false);
			//configBuilder.setResource("android");
			// configBuilder.setKeystoreType(null);
			XMPPTCPConnection connection = new XMPPTCPConnection(configBuilder.build());
			connection.connect();
			connection.setUseStreamManagement(true);
			System.out.println("connect");
					
			connection.login("yinxiaobao", "123456");
			ReconnectionManager reconnectionManager = ReconnectionManager.getInstanceFor(connection);
			//ReconnectionManager.setEnabledPerDefault(true);
			// 重联间隔5秒
			reconnectionManager.setFixedDelay(2);
			reconnectionManager.setReconnectionPolicy(ReconnectionManager.ReconnectionPolicy.FIXED_DELAY );
			reconnectionManager.enableAutomaticReconnection();
			//LocalPreferences localPref = SettingsManager.getLocalPreferences();
			
			
			if (connection.isAuthenticated()) {// 登录成功
				PingManager.setDefaultPingInterval(10);// Ping every 10 seconds
				PingManager myPingManager = PingManager.getInstanceFor(connection);
				System.out.println("pingserver:" + myPingManager.pingMyServer());
				// Set PingListener here to catch connect status
				myPingManager.registerPingFailedListener(new PingFailedListener() {
					@Override
					public void pingFailed() {
						System.out.println("ping failure");
					}
				});

			} else {
				connection.disconnect();
				System.out.println("Authentication failure");
			}

			ChatManager chatManager = ChatManager.getInstanceFor(connection);
			chatManager.addIncomingListener(new IncomingChatMessageListener() {
				@Override
				public void newIncomingMessage(EntityBareJid from, Message message, Chat chat) {
					String msg = "\"" + message.getBody().trim() + "\"";
					System.out.println("New message from " + from + ": " + message.getBody());
					String content = "阿弥陀佛";
					String subject = message.getSubject();
					System.out.println("subject:" + subject);
					if (null==subject||subject.equals("0")) {
						if (msg.contains("你是谁")) {
							content = "我是盈小宝，请问什么可以帮你？";
						} else if (msg.length() > 100) {
							content = "你好啰嗦，说点轻松的";
						} else {
							try {
								String response = HttpGet.sendGet("http://api.qingyunke.com/api.php",
										"key=free&appid=0&msg=" + msg);
								if (null == response) {
									content = "无语...";
								} else {
									System.out.println("response:" + response);
									JSONObject jsonObj = JSONObject.parseObject(response);
									if (null == jsonObj) {
										content = "不怼你...";
									} else {
										content = (String) jsonObj.get("content");
										if (null == content || content.isEmpty()) {
											content = "施主，我有点累了";
										}
									}
								}
								System.out.println(content);
							} catch (Exception e) {
								System.out.println(e.toString());
							}
						}
					} else {
						content = "讨厌，看不懂,我是文化人，认识文字！";
					}
					try {
						chat.send(content);
					} catch (NotConnectedException e) {
						System.out.println(e.toString());
					} catch (InterruptedException e) {
						System.out.println(e.toString());
					}
				}
			});
			StanzaListener packetListener = new StanzaListener() {

				@Override
				public void processStanza(Stanza packet) throws SmackException.NotConnectedException {
					if (packet instanceof Presence) {
						Presence presence = (Presence) packet;

						System.out.println("presence.getFrom():" + presence.getFrom() + "|presence.getType:"
								+ presence.getType().toString());
						String groupJid = presence.getFrom().toString().split("/")[0];
						String[] fromdata = presence.getFrom().toString().split("/");
						String extend = "";
						if (fromdata.length > 1) {
							extend = presence.getFrom().toString().split("/")[1];// 群成员上线
						}
						System.out.println("groupJid:" + groupJid);
						System.out.println("presence.getFrom().asBareJid().toString():"
								+ presence.getFrom().asBareJid().toString());

						// 暂时不需要用户同意
						if (Presence.Type.subscribe == presence.getType()) {
							try {
								Presence subscription = new Presence(Presence.Type.subscribed);
								subscription.setTo(groupJid);
								connection.sendStanza(subscription);;
								System.out.println("add" + presence.getFrom().asBareJid().toString());
							} catch (Exception e) {
								// e.printStackTrace();
								System.out.println("processStanza()" + e.toString());
							}
						}
					}
				}
			};

			// 条件过滤器
			AndFilter filter = new AndFilter(new StanzaTypeFilter(Presence.class));
			// 添加监听
			connection.addAsyncStanzaListener(packetListener, filter);
			heartbeat(connection);
			while (true) {
				try {
					Thread.sleep(30000);
					heartbeat(connection);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
//			if (connection != null)
//				connection.disconnect();
			System.out.println("Exception");
		}
	}

	static void heartbeat( AbstractXMPPConnection connection) {
		System.out.println("heartbeat　" + new Date() + "/" + connection.isAuthenticated());
		Presence subscription = new Presence(Presence.Type.available);
		try {
			//PingManager.setDefaultPingInterval(10);// Ping every 10 seconds
			PingManager myPingManager = PingManager.getInstanceFor(connection);
			System.out.println("pingserver:" + myPingManager.pingMyServer());
			// Set PingListener here to catch connect status
//			myPingManager.registerPingFailedListener(new PingFailedListener() {
//				@Override
//				public void pingFailed() {
//					System.out.println("ping failure");
//					connection.disconnect();
//					connect();
//				}
//			});
//			if (connection.isAuthenticated()) {
//				//connection.sendStanza(subscription);
//			} else if (connection.isConnected()) {
//				connection.disconnect();
//				connect();
//			} else {
//				connect();
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			//connect();
		}
	}

	public static boolean register(AbstractXMPPConnection connection) {
		AccountManager manager = AccountManager.getInstance(connection);
		try {
			Map<String, String> attr = new HashMap();
			attr.put("name", "bb");// 这个是注册用户名，类似qq网名

			Localpart l = Localpart.from("bb");// 这里注册账户名
			manager.sensitiveOperationOverInsecureConnection(true);

			manager.createAccount(l, "123123", attr);// 第一个参数是用户名，密码，用户信息

			// manager.createAccount(username, password);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("注册");
		}
		return false;
	}
}

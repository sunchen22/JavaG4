package com.onlinechat.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.onlinechat.jedis.JedisHandleMessage;
import com.onlinechat.model.ChatMessage;
import com.onlinechat.model.State;

//註冊一個httpServlet:成為一個連線端點
//若使用websocket連線方式,有它自己的連線規則
@ServerEndpoint("/FriendWS/{userName}")// {userName}為變數 //對應前端 var MyPoint 
public class FriendWS {
	// 一對一聊天要分對象傳遞訊息,故使用 Map :要注意有沒有重複問題
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();// 要用來存放連線資訊:連線人與連線內容
	Gson gson = new Gson();
	
	@OnOpen // 好友列表更新 // @PathParam為路徑參數:做路徑分析,讓他抓{userName}當作String userName傳進這個方法內
	public void onOpen(@PathParam("userName") String userName ,Session userSession ) throws IOException {
		// 只要有連線就會自動產生一個Session
		sessionsMap.put(userName, userSession);

		// userNames放著現在所有連上的使用者
		Set<String> userNames = sessionsMap.keySet(); 
		
		State stateMessage = new State("open", userName, userNames); // state只是方便傳送的東西,與資料庫無關
		String stateMessageJson = gson.toJson(stateMessage);// gson.toJson()傳入物件,傳回String

		Collection<Session> sessions = sessionsMap.values();
		for (Session session : sessions) {
			if (session.isOpen()) {
				session.getAsyncRemote().sendText(stateMessageJson);
			}
		}
		//開發者確認資訊用
		String text = String.format("Session ID = %s, connected; userName = %s%nusers: %s", userSession.getId(),userName, userNames);
		System.out.println(text);
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("userSession="+userSession);
		System.out.println("message="+message);
		System.out.println("-----------------------------------------------------------------");
		
		

		
		JsonObject jsonObject = gson.fromJson(message, JsonObject.class);
		String newMsg = gson.toJson(jsonObject);
		System.out.println("newMsg"+newMsg);
		
		ChatMessage chatMessage = gson.fromJson(newMsg, ChatMessage.class); // gson拿到Json資料會參考ChatMessage.class這個類別的內容去找對應資料，並回傳JAVA2物件
		String sender = chatMessage.getSender();
		String receiver = chatMessage.getReceiver();
		String timestamp = chatMessage.getTimestamp();
		
		if ("history".equals(chatMessage.getType())) {  // 獲得歷史訊息
			List<String> historyData = JedisHandleMessage.getHistoryMsg(sender, receiver);
			String historyMsg = gson.toJson(historyData);
			System.out.println("historyMs" + historyMsg +timestamp);
			ChatMessage cmHistory = new ChatMessage("history", sender, receiver, historyMsg, timestamp);
			if (userSession != null && userSession.isOpen()) {
				userSession.getAsyncRemote().sendText(gson.toJson(cmHistory));
				return;
			}
		}
		
		// 接收者同時也是送出者,角色會對調就取不到歷史資料,故要處理角色對調問題:解決方案之一 →兩邊都資料儲存
		Session receiverSession = sessionsMap.get(receiver);
		if (receiverSession != null && receiverSession.isOpen()) {
			receiverSession.getAsyncRemote().sendText(message);
			userSession.getAsyncRemote().sendText(message);
			JedisHandleMessage.saveChatMessage(sender, receiver, message, timestamp);
		}
		System.out.println("Message received: " + message + timestamp);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		String userNameClose = null;
		Set<String> userNames = sessionsMap.keySet();
		for (String userName : userNames) {
			if (sessionsMap.get(userName).equals(userSession)) {
				userNameClose = userName;
				sessionsMap.remove(userName);
				break;
			}
		}

		if (userNameClose != null) {
			State stateMessage = new State("close", userNameClose, userNames);
			String stateMessageJson = gson.toJson(stateMessage);
			Collection<Session> sessions = sessionsMap.values();
			for (Session session : sessions) {
				session.getAsyncRemote().sendText(stateMessageJson);
			}
		}

		String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
				reason.getCloseCode().getCode(), userNames);
		System.out.println(text);
	}
}

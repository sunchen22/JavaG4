package com.onlinechat.jedis;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisHandleMessage {
	// 此範例key的設計為(發送者名稱:接收者名稱)，實際應採用(發送者會員編號:接收者會員編號)

		private static JedisPool pool = util.JedisUtil.getJedisPool();

		public static List<String> getHistoryMsg(String sender, String receiver) {
			String key = new StringBuffer(sender).append(":").append(receiver).toString();
			Jedis jedis = null; 
			jedis = pool.getResource();
			jedis.select(7);  //設定redis的儲存database
			List<String> historyData = jedis.lrange(key, 0, -1); //存入的資料
			jedis.close();
			return historyData;
		}

		public static void saveChatMessage(String sender, String receiver, String message, String timestamp) {
			// 對雙方來說，都要各存著歷史聊天記錄
			String senderKey = new StringBuilder(sender).append(":").append(receiver).toString();
			String receiverKey = new StringBuilder(receiver).append(":").append(sender).toString();

			Jedis jedis = pool.getResource();
			jedis.select(7);  //設定redis的儲存database 
			jedis.rpush(senderKey, message, timestamp);  //A
			jedis.rpush(receiverKey, message, timestamp);  //B
			jedis.close();
		}

	}
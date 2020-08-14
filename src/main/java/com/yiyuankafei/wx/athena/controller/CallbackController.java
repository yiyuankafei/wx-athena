package com.yiyuankafei.wx.athena.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yiyuankafei.wx.athena.entity.wx.Message;
import com.yiyuankafei.wx.athena.util.MessageUtil;
import com.yiyuankafei.wx.athena.util.SHA1Util;

@RestController
@Slf4j
public class CallbackController {
	
	private static final String TOKEN = "athena";
	
	/**
	 * 
	 * 配置地址，GET请求-接入校验
	 */
	@GetMapping("/callback")
	@ResponseBody
	public String callback(String echostr, String timestamp, String nonce,String signature) throws Exception {
		log.info("接收微信认证请求:echostr({}), timestamp({}),nonce({}),singature({})", echostr, timestamp, nonce, signature);
		//将token、timestamp、nonce三个参数进行字典序排序
		String sortStr = sort(TOKEN,timestamp,nonce);
		//将三个参数字符串拼接成一个字符串进行sha1加密 (可逆加密解密函数)
		String sign = SHA1Util.encode(sortStr);
		log.info("签名:" + signature);
		log.info("验签:" + sign);
		//开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
		if (sign.equals(signature)) {
			return echostr;
		}
		return "";
	}
	
	
	/**
	 * 
	 * 配置地址，POST请求-自动回复
	 */
	@PostMapping("/callback")
	@ResponseBody
	public String callback(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String, String> map = MessageUtil.xmlToMap(request);
		log.info("=====accept wx request=====:{}", JSON.toJSONString(map));
		String res = null;
		System.out.println(map);
		String MsgType = map.get("MsgType");
		String ToUserName = map.get("ToUserName");
		String FromUserName = map.get("FromUserName");
		/*String Content = map.get("Content");
		String MsgId = map.get("MsgId");*/
		if (MsgType.equalsIgnoreCase("text")) {
			Message message = new Message(); 
			message.setFromUserName(ToUserName); 
			message.setToUserName(FromUserName); 
			message.setCreateTime(new Date().getTime());
			//返回文字
			message.setMsgType("text"); 
			message.setContent("欢迎访问“旅行戳”公众号\n您可以在这里为您的照片加盖邮戳：\n<a href=\"http://yiyuankafei.natapp1.cc/postMark/page\">上戳</a>" + 
					"\n<a href=\"http://yiyuankafei.natapp1.cc/postMark/jiangling\">查看江岭油菜花实时花况</a>"); 
			//返回图片
			/*message.setMsgType("image");
			Image image = new Image();
			image.setMediaId("Hv2Y74qH4NHhzsndt9GaCYIp9lqa78_3r97Z-bvpXrCoZbI8Or06ELqwkh2nROVZ");
			message.setImage(image);*/
			
			res = MessageUtil.objectToXml(message);
		} else if (MsgType.equalsIgnoreCase("event")) {
			//订阅事件回复
			String event = map.get("Event");
			if (event.equalsIgnoreCase("subscribe")) {
				Message message = new Message(); 
				message.setFromUserName(ToUserName); 
				message.setToUserName(FromUserName); 
				message.setCreateTime(new Date().getTime());
				//返回文字
				message.setMsgType("text"); 
				message.setContent("欢迎订阅“旅行戳”公众号\n您可以在这里为您的照片加盖邮戳：\n<a href=\"http://yiyuankafei.natapp1.cc/postMark/page\">上戳</a>" +
						"\n<a href=\"http://yiyuankafei.natapp1.cc/postMark/jiangling\">查看江岭油菜花实时花况</a>"); 
				res = MessageUtil.objectToXml(message);
			} else if (event.equalsIgnoreCase("CLICK")) {
				String key = map.get("EventKey") + "今日啊歌曲是：六月的雨" ;
				Message message = new Message(); 
				message.setFromUserName(ToUserName); 
				message.setToUserName(FromUserName); 
				message.setCreateTime(new Date().getTime());
				//返回文字
				message.setMsgType("text"); 
				message.setContent(key); 
				res = MessageUtil.objectToXml(message);
			}
		}
		
		return res;
	}
	
	
	/**
	 * 
	 * 参数排列
	 */
	public String sort(String token, String timestamp, String nonce) {
		String[] strArray = { token, timestamp, nonce };
		Arrays.sort(strArray);
		StringBuilder sb = new StringBuilder();
		for (String str : strArray) {
			sb.append(str);
		}
		return sb.toString();
	}

	/**
	 * 
	 * shal签名
	 */
	public String shal(String str) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(str.getBytes());
			byte messageDigest[] = digest.digest();

			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

}

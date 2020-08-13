package com.yiyuankafei.wx.athena.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
		return null;
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

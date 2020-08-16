package com.yiyuankafei.wx.athena.controller;

import lombok.extern.slf4j.Slf4j;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yiyuankafei.wx.athena.common.CommonConstant;
import com.yiyuankafei.wx.athena.entity.wx.QrCodeCreateTemp;

@Controller
@RequestMapping("/qrcode")
@Slf4j
public class QRCodeController {
	
	@ResponseBody
	@RequestMapping("/ticket/temp")
	public String ticketTemp(@RequestBody QrCodeCreateTemp temp) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost("https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + CommonConstant.ACCESS_TOKEN);
		
		StringEntity entity = new StringEntity(JSON.toJSONString(temp), "utf-8");
		log.info("创建二维码:{}", JSON.toJSONString(temp));
		entity.setContentEncoding("UTF-8");
		post.setHeader("Content-type", "application/json");
		post.setEntity(entity);
		CloseableHttpResponse response = client.execute(post);
		
		HttpEntity responseEntity = response.getEntity();
		
		String result = EntityUtils.toString(responseEntity, "UTF-8");
		log.info("===== create qrcode ===== : {}", result);
		return result;
	}
	
	@RequestMapping("/get")
	public String get(String ticket) throws Exception {
		
		return "redirect:" + "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + ticket;
		
	}

}

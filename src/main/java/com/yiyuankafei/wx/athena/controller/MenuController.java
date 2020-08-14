package com.yiyuankafei.wx.athena.controller;

import lombok.extern.slf4j.Slf4j;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yiyuankafei.wx.athena.common.CommonConstant;
import com.yiyuankafei.wx.athena.entity.wx.Menu;
import com.yiyuankafei.wx.athena.entity.wx.SpecialMenu;
import com.yiyuankafei.wx.athena.entity.wx.WxResponse;

@RestController
@RequestMapping("/menu")
@Slf4j
public class MenuController {
	
	@RequestMapping("/create")
	public WxResponse create(@RequestBody Menu menu) throws Exception {
		
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + CommonConstant.ACCESS_TOKEN);
		StringEntity entity = new StringEntity(JSON.toJSONString(menu), "utf-8");
		entity.setContentEncoding("UTF-8");
		post.setHeader("Content-type", "application/json");
		post.setEntity(entity);
		CloseableHttpResponse response = client.execute(post);
		
		HttpEntity responseEntity = response.getEntity();
		
		String result = EntityUtils.toString(responseEntity, "UTF-8");
		log.info("===== create menu ===== : {result}", result);
		WxResponse wxResponse = JSON.parseObject(result, WxResponse.class);
		
		return wxResponse;
		
	}
	
	@RequestMapping("/create/special")
	public WxResponse createSpecial(@RequestBody SpecialMenu menu) throws Exception {
		
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost("https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=" + CommonConstant.ACCESS_TOKEN);
		StringEntity entity = new StringEntity(JSON.toJSONString(menu), "utf-8");
		log.info("创建个性化菜单:{}", JSON.toJSONString(menu));
		entity.setContentEncoding("UTF-8");
		post.setHeader("Content-type", "application/json");
		post.setEntity(entity);
		CloseableHttpResponse response = client.execute(post);
		
		HttpEntity responseEntity = response.getEntity();
		
		String result = EntityUtils.toString(responseEntity, "UTF-8");
		log.info("===== create special menu ===== : {}", result);
		WxResponse wxResponse = JSON.parseObject(result, WxResponse.class);
		
		return wxResponse;
		
	}
	
	@RequestMapping("/list")
	public String list() throws Exception {
		
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet("https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + CommonConstant.ACCESS_TOKEN);
		CloseableHttpResponse response = client.execute(get);
		
		HttpEntity responseEntity = response.getEntity();
		
		String result = EntityUtils.toString(responseEntity, "UTF-8");
		log.info("===== create special menu ===== : {}", result);
		
		return result;
		
	}
	
	@RequestMapping("/delete")
	public String delete() throws Exception {
		
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + CommonConstant.ACCESS_TOKEN);
		CloseableHttpResponse response = client.execute(get);
		
		HttpEntity responseEntity = response.getEntity();
		
		String result = EntityUtils.toString(responseEntity, "UTF-8");
		log.info("===== delete menu ===== : {}", result);
		
		return result;
		
	}
}

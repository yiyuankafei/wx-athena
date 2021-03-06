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
import com.yiyuankafei.wx.athena.entity.wx.ListUserTag;
import com.yiyuankafei.wx.athena.entity.wx.SpecialMenuMatch;
import com.yiyuankafei.wx.athena.entity.wx.TagUser;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

	@RequestMapping("/tag/add")
	public String tagAdd(@RequestBody TagUser tagUser) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost("https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=" + CommonConstant.ACCESS_TOKEN);
		StringEntity entity = new StringEntity(JSON.toJSONString(tagUser), "utf-8");
		log.info("添加用户tag:{}", JSON.toJSONString(tagUser));
		entity.setContentEncoding("UTF-8");
		post.setHeader("Content-type", "application/json");
		post.setEntity(entity);
		CloseableHttpResponse response = client.execute(post);
		
		HttpEntity responseEntity = response.getEntity();
		
		String result = EntityUtils.toString(responseEntity, "UTF-8");
		log.info("===== add tag user ===== : {}", result);
		return result;
	}
	
	@RequestMapping("/tag/delete")
	public String tagDelete(@RequestBody TagUser tagUser) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost("https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token=" + CommonConstant.ACCESS_TOKEN);
		StringEntity entity = new StringEntity(JSON.toJSONString(tagUser), "utf-8");
		log.info("删除用户tag:{}", JSON.toJSONString(tagUser));
		entity.setContentEncoding("UTF-8");
		post.setHeader("Content-type", "application/json");
		post.setEntity(entity);
		CloseableHttpResponse response = client.execute(post);
		
		HttpEntity responseEntity = response.getEntity();
		
		String result = EntityUtils.toString(responseEntity, "UTF-8");
		log.info("===== delete tag user ===== : {}", result);
		return result;
	}
	
	@RequestMapping("/tag/list")
	public String tagList(@RequestBody ListUserTag listUserTag) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost("https://api.weixin.qq.com/cgi-bin/tags/getidlist?access_token=" + CommonConstant.ACCESS_TOKEN);
		StringEntity entity = new StringEntity(JSON.toJSONString(listUserTag), "utf-8");
		log.info("查看用户tag列表: {}", JSON.toJSONString(listUserTag));
		entity.setContentEncoding("UTF-8");
		post.setHeader("Content-type", "application/json");
		post.setEntity(entity);
		CloseableHttpResponse response = client.execute(post);
		
		HttpEntity responseEntity = response.getEntity();
		
		String result = EntityUtils.toString(responseEntity, "UTF-8");
		log.info("===== list tag user ===== : {}", result);
		return result;
	}
	
	@RequestMapping("/tag/rel")
	public String tagDelete(@RequestBody SpecialMenuMatch specialMenuMatch) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost("https://api.weixin.qq.com/cgi-bin/menu/trymatch?access_token=" + CommonConstant.ACCESS_TOKEN);
		StringEntity entity = new StringEntity(JSON.toJSONString(specialMenuMatch), "utf-8");
		log.info("测试用户绑定菜单:{}", JSON.toJSONString(specialMenuMatch));
		entity.setContentEncoding("UTF-8");
		post.setHeader("Content-type", "application/json");
		post.setEntity(entity);
		CloseableHttpResponse response = client.execute(post);
		
		HttpEntity responseEntity = response.getEntity();
		
		String result = EntityUtils.toString(responseEntity, "UTF-8");
		log.info("===== speicl menu match ===== : {}", result);
		return result;
	}
	
	@RequestMapping("/info")
	public String info(String openId) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + CommonConstant.ACCESS_TOKEN + "&openid=" + openId
				+ "&lang=zh_CN";
		log.info("获取用户信息：{}", url);
		HttpGet get = new HttpGet(url);
		CloseableHttpResponse response = client.execute(get);
		
		HttpEntity responseEntity = response.getEntity();
		
		String result = EntityUtils.toString(responseEntity, "UTF-8");
		log.info("===== user info ===== : {}", result);
		
		return result;
	}
	
}

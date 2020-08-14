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
import com.yiyuankafei.wx.athena.entity.wx.DeleteTag;
import com.yiyuankafei.wx.athena.entity.wx.Tag;

@RestController
@RequestMapping("/tag")
@Slf4j
public class TagController {

	@RequestMapping("/create")
	public String create(@RequestBody Tag tag) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost("https://api.weixin.qq.com/cgi-bin/tags/create?access_token=" + CommonConstant.ACCESS_TOKEN);
		StringEntity entity = new StringEntity(JSON.toJSONString(tag), "utf-8");
		entity.setContentEncoding("UTF-8");
		post.setHeader("Content-type", "application/json");
		post.setEntity(entity);
		CloseableHttpResponse response = client.execute(post);
		
		HttpEntity responseEntity = response.getEntity();
		
		String result = EntityUtils.toString(responseEntity, "UTF-8");
		log.info("===== create tag ===== : {}", result);
		return result;
	}
	
	@RequestMapping("/list")
	public String list() throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet("https://api.weixin.qq.com/cgi-bin/tags/get?access_token=" + CommonConstant.ACCESS_TOKEN);
		CloseableHttpResponse response = client.execute(get);
		
		HttpEntity responseEntity = response.getEntity();
		
		String result = EntityUtils.toString(responseEntity, "UTF-8");
		log.info("===== list tag ===== : {}", result);
		
		return result;
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestBody DeleteTag tag) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost("https://api.weixin.qq.com/cgi-bin/tags/delete?access_token=" + CommonConstant.ACCESS_TOKEN);
		StringEntity entity = new StringEntity(JSON.toJSONString(tag), "utf-8");
		log.info("删除tag：{}", JSON.toJSONString(tag));
		entity.setContentEncoding("UTF-8");
		post.setHeader("Content-type", "application/json");
		post.setEntity(entity);
		CloseableHttpResponse response = client.execute(post);
		
		HttpEntity responseEntity = response.getEntity();
		
		String result = EntityUtils.toString(responseEntity, "UTF-8");
		log.info("===== delete tag ===== : {}", result);
		return result;
	}
	
}

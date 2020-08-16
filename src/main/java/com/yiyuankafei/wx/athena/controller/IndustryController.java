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
import com.yiyuankafei.wx.athena.entity.wx.GetTemplateId;
import com.yiyuankafei.wx.athena.entity.wx.SetIndustry;
import com.yiyuankafei.wx.athena.entity.wx.TemplateMessage;

@RestController	
@RequestMapping("/indus")
@Slf4j
public class IndustryController {
	
	@RequestMapping("/list")
	public String list() throws Exception {
		
		CloseableHttpClient client = HttpClients.createDefault();
		String url = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=" + CommonConstant.ACCESS_TOKEN;
		HttpGet get = new HttpGet(url);
		CloseableHttpResponse response = client.execute(get);
		
		HttpEntity responseEntity = response.getEntity();
		
		String result = EntityUtils.toString(responseEntity, "UTF-8");
		log.info("===== industry  type ===== : {}", result);
		
		return result;
		
	}
	
	@RequestMapping("/set")
	public String set() throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost("https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=" + CommonConstant.ACCESS_TOKEN);
		SetIndustry indus = new SetIndustry("1", "4");
		StringEntity entity = new StringEntity(JSON.toJSONString(indus), "utf-8");
		entity.setContentEncoding("UTF-8");
		post.setHeader("Content-type", "application/json");
		post.setEntity(entity);
		CloseableHttpResponse response = client.execute(post);
		
		HttpEntity responseEntity = response.getEntity();
		
		String result = EntityUtils.toString(responseEntity, "UTF-8");
		log.info("===== set industryType ===== : {}", result);
		return result;
	}
	
	@RequestMapping("/getTemplateId")
	public String getTemplateId(String templateId) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost("https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=" + CommonConstant.ACCESS_TOKEN);
		GetTemplateId getTempalteId = new GetTemplateId(templateId);
		StringEntity entity = new StringEntity(JSON.toJSONString(getTempalteId), "utf-8");
		log.info("获取ID模板:{}", JSON.toJSONString(getTempalteId));
		entity.setContentEncoding("UTF-8");
		post.setHeader("Content-type", "application/json");
		post.setEntity(entity);
		CloseableHttpResponse response = client.execute(post);
		
		HttpEntity responseEntity = response.getEntity();
		
		String result = EntityUtils.toString(responseEntity, "UTF-8");
		log.info("===== get TemplateId ===== : {}", result);
		return result;
	}
	
	@RequestMapping("/template/list")
	public String templateList() throws Exception {
		
		CloseableHttpClient client = HttpClients.createDefault();
		String url = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=" + CommonConstant.ACCESS_TOKEN;
		HttpGet get = new HttpGet(url);
		CloseableHttpResponse response = client.execute(get);
		
		HttpEntity responseEntity = response.getEntity();
		
		String result = EntityUtils.toString(responseEntity, "UTF-8");
		log.info("===== get template List ===== : {}", result);
		
		return result;
		
	}
	
	@RequestMapping("/template/send")
	public String templateSend(@RequestBody TemplateMessage message) throws Exception {
		
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + CommonConstant.ACCESS_TOKEN);
		
		StringEntity entity = new StringEntity(JSON.toJSONString(message), "utf-8");
		log.info("发送模板消息:{}", JSON.toJSONString(message));
		entity.setContentEncoding("UTF-8");
		post.setHeader("Content-type", "application/json");
		post.setEntity(entity);
		CloseableHttpResponse response = client.execute(post);
		
		HttpEntity responseEntity = response.getEntity();
		
		String result = EntityUtils.toString(responseEntity, "UTF-8");
		log.info("===== send template message ===== : {}", result);
		return result;
		
	}

}

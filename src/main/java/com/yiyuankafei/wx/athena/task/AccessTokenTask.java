package com.yiyuankafei.wx.athena.task;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.yiyuankafei.wx.athena.common.CommonConstant;
import com.yiyuankafei.wx.athena.entity.wx.AccessToken;

@Component
@Slf4j
public class AccessTokenTask {
	
	private final String APP_ID = "wx1810583eecf5e40c";
	private final String APP_SECRET = "4ed0148aa7cbd7cee7f647540de2b81a";
	
	@Scheduled(fixedRate = 1000*60*60)  
    public void setAccessToken() throws Exception, IOException { 
		log.info("执行任务获取token");
		HttpGet httpGet = new HttpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APP_ID + "&secret=" + APP_SECRET);
        CloseableHttpClient getClient = HttpClients.createDefault();
        CloseableHttpResponse getResponse = getClient.execute(httpGet);
        HttpEntity entity = getResponse.getEntity();
        String token = EntityUtils.toString(entity, "UTF-8");
        getClient.close();
        log.info("获取accessToken:{}", token);
        AccessToken accessToken = JSON.parseObject(token, AccessToken.class);
        CommonConstant.ACCESS_TOKEN = accessToken.getAccess_token();
    }  
}

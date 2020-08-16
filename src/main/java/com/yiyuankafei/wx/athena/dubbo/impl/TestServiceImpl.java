package com.yiyuankafei.wx.athena.dubbo.impl;

import org.apache.dubbo.config.annotation.Service;

import com.yiyuankafei.wx.athena.dubbo.TestService;

@Service(version = "1.0.0", group = "wx-athena-group", timeout = 60000, retries = -1)
public class TestServiceImpl implements TestService {
	
	@Override
	public String test(String name) {
		return "hi from wx-athena registerd on nacos:" + name;
	}

}

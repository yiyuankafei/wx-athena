package com.yiyuankafei.wx.athena.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yiyuankafei.wx.athena.entity.Emoticon;
import com.yiyuankafei.wx.athena.entity.EmoticonExample;
import com.yiyuankafei.wx.athena.service.EmoticonService;

@RestController
@RequestMapping("/emoticon")
public class EmoticonController {
	
	@Autowired
	EmoticonService emoticonService;
	
	@RequestMapping("/list")
	public List<Emoticon> list() {
		EmoticonExample example = new EmoticonExample();
		example.createCriteria().andIdIsNotNull();
		return emoticonService.selectByExample(example);
	}
 
}

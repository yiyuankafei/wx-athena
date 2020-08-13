package com.yiyuankafei.wx.athena.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yiyuankafei.wx.athena.entity.Emoticon;
import com.yiyuankafei.wx.athena.entity.EmoticonExample;
import com.yiyuankafei.wx.athena.service.EmoticonService;

@RestController
@Slf4j
@RequestMapping("/emoticon")
public class EmoticonController {
	
	@Autowired
	EmoticonService emoticonService;
	
	@RequestMapping("/list")
	public List<Emoticon> list() {
		EmoticonExample example = new EmoticonExample();
		example.createCriteria().andIdIsNotNull();
		List<Emoticon> list = emoticonService.selectByExample(example);
		log.info("/emoticon/list查询结果:{}", list);
		return list;
	}
 
}

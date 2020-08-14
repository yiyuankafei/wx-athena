package com.yiyuankafei.wx.athena.entity.wx;

import java.util.List;

import lombok.Data;

@Data
public class TagUser {
	
	private List<String> openid_list;
	
	private Integer tagid;

}

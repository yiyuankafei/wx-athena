package com.yiyuankafei.wx.athena.entity.wx;

import lombok.Data;

@Data
public class QrCodeCreateTemp {
	
	private Long expire_seconds;
	
	private String action_name;
	
	private QrCodeCreateInfo action_info;

}

package com.yiyuankafei.wx.athena.entity.wx;

import lombok.Data;

@Data
public class TemplateMessage {
	
	private String touser;
	
	private String template_id;
	
	private TemplateMessageData data;
	
	

}

package com.yiyuankafei.wx.athena.entity.wx;

import java.util.List;

import lombok.Data;

@Data
public class Button {
	
	private String type;
	
	private String name;
	
	private String key;
	
	private List<Button> sub_button;

}

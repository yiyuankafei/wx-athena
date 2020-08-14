package com.yiyuankafei.wx.athena.entity.wx;

import lombok.Data;

@Data
public class Tag {
	
	private TagItem tag;

}

@Data
class TagItem {
	private String name;
}

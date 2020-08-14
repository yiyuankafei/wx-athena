package com.yiyuankafei.wx.athena.entity.wx;

import lombok.Data;

@Data
public class DeleteTag {
	
	private DeleteTagItem tag;

}

@Data
class DeleteTagItem {
	private String id;
}

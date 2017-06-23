package com.jaydenxiao.common.v.dialog;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name="dict")
public class GroupEntity {
	@ElementList(entry="key", inline=true)
	public List<String> key;
	@ElementList(entry="dict", inline=true)
	public List<MemberEntity> dict;
	
}

package com.jaydenxiao.common.v.dialog;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root
public class ThreeDictEntity {
	@ElementList(entry="key", inline=true)
	public List<String> key;
	@ElementList(entry="string", inline=true)
	public List<String> string;
	
	
}

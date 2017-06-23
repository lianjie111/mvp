package com.jaydenxiao.common.v.dialog;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name="dict")
public class MemberEntity {
	@ElementList(entry="key", inline=true)
	public List<String> key;
	@Element(name="string")
	public String string;
	@Element(name="dict",type=ThreeDictEntity.class,data=true)
	public ThreeDictEntity threeDictEntity;
	
}

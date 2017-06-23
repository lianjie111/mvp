package com.jaydenxiao.common.v.dialog;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name="plist")
public class PlistEntity {
	@Attribute(required = true) 
	public String version;
	@ElementList(inline=true,required=false)
	public List<GroupEntity> dictentity;
}

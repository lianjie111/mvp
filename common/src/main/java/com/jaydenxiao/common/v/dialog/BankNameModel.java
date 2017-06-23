package com.jaydenxiao.common.v.dialog;

public class BankNameModel {
private String name;
private int index;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getIndex() {
	return index;
}
public void setIndex(int index) {
	this.index = index;
}
@Override
public String toString() {
	return "BankNameModel [name=" + name + ", index=" + index + "]";
}
public BankNameModel(String name, int index) {
	super();
	this.name = name;
	this.index = index;
}
public BankNameModel() {
	super();
	// TODO Auto-generated constructor stub
}

}

package com.bcc.security.admin.dataparse.model;

public class Point {
	String x;
	String[] y;
	public Point(String x,String[] y){
		this.x = x;
		this.y = y;
	}
	
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String[] getY() {
		return y;
	}
	public void setY(String[] y) {
		this.y = y;
	}
	
	
	
}

package com.medical.modules.sys.entity;

/**
 * 待办
 * @author dyf
 *
 */
public class Backlog {
	
	private int num;
	private String text;
	private String url;
	
	public Backlog(){
		
	}

	public Backlog(int num, String text, String url) {
		super();
		this.num = num;
		this.text = text;
		this.url = url;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}

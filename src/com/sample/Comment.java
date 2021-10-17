package com.sample;

import java.util.Date;

public class Comment {

	private int id;
	private Date date;
	private String name;
	private String content;

	public Comment(int id, Date date, String name, String content) {
		super();
		this.id = id;
		this.date = date;
		this.name = name;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}

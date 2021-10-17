package com.sample;

import java.util.Date;

public class Comment {

	private int id;
	private Date date;
	private String name;
	private String content;
	// private List<Reply> reply;

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

	public Date getDate() {
		return date;
	}

	public String getName() {
		return name;
	}

	public String getContent() {
		return content;
	}

}

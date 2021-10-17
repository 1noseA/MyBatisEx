package com.sample;

import java.util.Date;

public class Reply {

	private Date repDate;
	private String repName;
	private String repContent;
	private int comId;

	public Reply(Date repDate, String repName, String repContent, int comId) {
		super();
		this.repDate = repDate;
		this.repName = repName;
		this.repContent = repContent;
		this.comId = comId;
	}

	public Date getRepDate() {
		return repDate;
	}

	public String getRepName() {
		return repName;
	}

	public String getRepContent() {
		return repContent;
	}

	public int getComId() {
		return comId;
	}

}

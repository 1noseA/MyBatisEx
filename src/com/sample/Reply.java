package com.sample;

import java.util.Date;

public class Reply {

	private int comId;
	private int repId;
	private Date repDate;
	private String repName;
	private String repContent;

	public Reply(int comId, int repId, Date repDate, String repName, String repContent) {
		super();
		this.comId = comId;
		this.repId = repId;
		this.repDate = repDate;
		this.repName = repName;
		this.repContent = repContent;
	}

	public int getComId() {
		return comId;
	}

	public void setComId(int comId) {
		this.comId = comId;
	}

	public int getRepId() {
		return repId;
	}

	public void setRepId(int repId) {
		this.repId = repId;
	}

	public Date getRepDate() {
		return repDate;
	}

	public void setRepDate(Date repDate) {
		this.repDate = repDate;
	}

	public String getRepName() {
		return repName;
	}

	public void setRepName(String repName) {
		this.repName = repName;
	}

	public String getRepContent() {
		return repContent;
	}

	public void setRepContent(String repContent) {
		this.repContent = repContent;
	}

}

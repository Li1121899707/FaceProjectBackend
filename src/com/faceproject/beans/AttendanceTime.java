package com.faceproject.beans;

public class AttendanceTime {
	
	private Integer atId;
	private String atStart;
	private String atEnd;
	private String atDate;
	private Integer atEmployeeId;
	private String eiName;
	
	public Integer getAtId() {
		return atId;
	}
	public void setAtId(Integer atId) {
		this.atId = atId;
	}
	
	public String getAtDate() {
		return atDate;
	}
	public void setAtDate(String atDate) {
		this.atDate = atDate;
	}
	public String getAtStart() {
		return atStart;
	}
	public void setAtStart(String atStart) {
		this.atStart = atStart;
	}
	public String getAtEnd() {
		return atEnd;
	}
	public void setAtEnd(String atEnd) {
		this.atEnd = atEnd;
	}
	public Integer getAtEmployeeId() {
		return atEmployeeId;
	}
	public void setAtEmployeeId(Integer atEmployeeId) {
		this.atEmployeeId = atEmployeeId;
	}
	public String getEiName() {
		return eiName;
	}
	public void setEiName(String eiName) {
		this.eiName = eiName;
	}
}

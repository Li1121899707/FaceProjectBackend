package com.faceproject.beans;

public class AttendanceRate {
	private Integer eid;
	private String name;
	private String late;
	public void setLate(String late) {
		this.late = late;
	}
	public void setEarly(String early) {
		this.early = early;
	}
	public void setAbsence(String absence) {
		this.absence = absence;
	}
	private String early;
	private String absence;
	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLate() {
		return late;
	}
	public String getEarly() {
		return early;
	}
	public String getAbsence() {
		return absence;
	}
}

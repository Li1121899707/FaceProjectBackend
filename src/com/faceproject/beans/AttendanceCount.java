package com.faceproject.beans;

public class AttendanceCount {
	private Integer arId;
	private Integer arEmployeeId;
	private String eiName;
	private String atStart;
	private String atEnd;
	private String arDate;
	private String arLate;
	private String arEarly;
	private String arAbsence;
	
	
	public Integer getArId() {
		return arId;
	}
	public void setArId(Integer arId) {
		this.arId = arId;
	}
	public String getArDate() {
		return arDate;
	}
	public void setArDate(String arDate) {
		this.arDate = arDate;
	}
	public String getArLate() {
		return arLate;
	}
	public void setArLate(String arLate) {
		this.arLate = arLate;
	}
	public String getArEarly() {
		return arEarly;
	}
	public void setArEarly(String arEarly) {
		this.arEarly = arEarly;
	}
	public String getArAbsence() {
		return arAbsence;
	}
	public void setArAbsence(String arAbsence) {
		this.arAbsence = arAbsence;
	}
	public Integer getArEmployeeId() {
		return arEmployeeId;
	}
	public void setArEmployeeId(Integer arEmployeeId) {
		this.arEmployeeId = arEmployeeId;
	}
	public String getEiName() {
		return eiName;
	}
	public void setEiName(String eiName) {
		this.eiName = eiName;
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
}

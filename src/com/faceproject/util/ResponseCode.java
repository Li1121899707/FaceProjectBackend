package com.faceproject.util;

/**
 * ������Ӧ��״̬��
 */
public enum ResponseCode {
	SUCCESS(100, "SUCCESS"), ERROR(101, "ERROR"), NEED_LOG(1, "NEED_LOG");
	private int code;
	private String desc;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	ResponseCode(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
}
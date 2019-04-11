package com.faceproject.util;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * ��װ��˷��ص�Json����
 */

//��֤���л�jsonʱ�������null�Ķ���keyҲ����ʧ
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServerResponse<T> {

	// ״̬��
	private int statu;
	// ����
	private T data;
	// ������Ϣ
	private String msg;
	// ��ҳ��Ϣ��������ڣ�
	private PageUtil page;

	public int getStatu() {
		return statu;
	}

	public void setStatu(int statu) {
		this.statu = statu;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public PageUtil getPage() {
		return page;
	}

	public void setPage(PageUtil page) {
		this.page = page;
	}

	// ���캯��
	// �з�ҳ
	public ServerResponse(int statu, T data, String msg, PageUtil page) {
		this.statu = statu;
		this.data = data;
		this.msg = msg;
		this.page = page;
	}

	// �޷�ҳ
	public ServerResponse(int statu, T data, String msg) {
		this.statu = statu;
		this.data = data;
		this.msg = msg;
	}

	// ������ʱ
	public ServerResponse(int statu) {
		this.statu = statu;
	}

	// ����ʧ��ʱ
	public ServerResponse(int statu, String msg) {
		this.statu = statu;
		this.msg = msg;
	}

	// ʹ֮�������л������
	@JsonIgnore
	public boolean checkIsSuccess() {
		return this.statu == ResponseCode.SUCCESS.getCode();
	}

	// �ɹ���������
	// �з�ҳ
	public static <T> ServerResponse<T> createBySuccess(String msg, T data, PageUtil page) {
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), data, msg, page);
	}

	// �޷�ҳ
	public static <T> ServerResponse<T> createBySuccess(String msg, T data) {
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), data, msg);
	}
	
	// ������
	public static <T> ServerResponse<T> createBySuccess(String msg) {
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg);
	}

	/* ����У���¼��Ϣ */

	public static <T> ServerResponse<T> createByCheckSuccess() {
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
	}

	// ���ش�����Ϣ
	public static <T> ServerResponse<T> createByError(String msg) {
		return new ServerResponse<T>(ResponseCode.ERROR.getCode(), msg);
	}

	public static <T> ServerResponse<T> createByNeedLogin() {
		return new ServerResponse<T>(ResponseCode.NEED_LOG.getCode(), "���ȵ�¼��");
	}
}

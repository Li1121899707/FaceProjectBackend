package com.faceproject.util;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 封装后端返回的Json数据
 */

//保证序列化json时，如果是null的对象，key也会消失
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServerResponse<T> {

	// 状态码
	private int statu;
	// 数据
	private T data;
	// 描述信息
	private String msg;
	// 分页信息（如果存在）
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

	// 构造函数
	// 有分页
	public ServerResponse(int statu, T data, String msg, PageUtil page) {
		this.statu = statu;
		this.data = data;
		this.msg = msg;
		this.page = page;
	}

	// 无分页
	public ServerResponse(int statu, T data, String msg) {
		this.statu = statu;
		this.data = data;
		this.msg = msg;
	}

	// 无数据时
	public ServerResponse(int statu) {
		this.statu = statu;
	}

	// 操作失败时
	public ServerResponse(int statu, String msg) {
		this.statu = statu;
		this.msg = msg;
	}

	// 使之不在序列化结果中
	@JsonIgnore
	public boolean checkIsSuccess() {
		return this.statu == ResponseCode.SUCCESS.getCode();
	}

	// 成功返回数据
	// 有分页
	public static <T> ServerResponse<T> createBySuccess(String msg, T data, PageUtil page) {
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), data, msg, page);
	}

	// 无分页
	public static <T> ServerResponse<T> createBySuccess(String msg, T data) {
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), data, msg);
	}
	
	// 无数据
	public static <T> ServerResponse<T> createBySuccess(String msg) {
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg);
	}

	/* 用于校验登录信息 */

	public static <T> ServerResponse<T> createByCheckSuccess() {
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
	}

	// 返回错误信息
	public static <T> ServerResponse<T> createByError(String msg) {
		return new ServerResponse<T>(ResponseCode.ERROR.getCode(), msg);
	}

	public static <T> ServerResponse<T> createByNeedLogin() {
		return new ServerResponse<T>(ResponseCode.NEED_LOG.getCode(), "请先登录！");
	}
}

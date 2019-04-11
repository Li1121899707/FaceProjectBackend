package com.faceproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.faceproject.beans.AttendanceCount;
import com.faceproject.service.AttendanceCountService;
import com.faceproject.util.PageSplit;
import com.faceproject.util.PageUtil;
import com.faceproject.util.ServerResponse;

// 考勤统计信息
@Controller
@RequestMapping("/admin/count")
public class AttendanceCountController {
	
	@Autowired
	private AttendanceCountService service;
	
	// 查询所有考勤统计信息
	@RequestMapping("/queryall")
	@ResponseBody
	public ServerResponse<List<AttendanceCount>> queryAllAttendanceCount(HttpServletRequest request){
		int pageIndex = 1;
		if (request.getParameter("pageIndex") != null) {
			pageIndex = Integer.parseInt((String) request.getParameter("pageIndex"));
		}
		System.out.println(pageIndex);
		int pageNumber = service.pagecount();
		PageSplit pSplit = new PageSplit();
		PageUtil pUtil = new PageUtil();
		pUtil = pSplit.getPageIndex(pageIndex, pageNumber);
		ServerResponse<List<AttendanceCount>> response = service.queryAllAttendanceCount(pUtil);
		return response;
	}
	
	// 生成考勤统计信息表（rate表）
	@RequestMapping("/create")
	@ResponseBody
	public ServerResponse<String> create(){
		return service.isCountComplete();
	}
}

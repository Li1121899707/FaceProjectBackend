package com.faceproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.faceproject.beans.AttendanceRate;
import com.faceproject.service.AttendanceRateService;
import com.faceproject.util.PageSplit;
import com.faceproject.util.PageUtil;
import com.faceproject.util.ServerResponse;

@Controller
@RequestMapping("/admin/rate")
public class AttendanceRateController {
	@Autowired
	private AttendanceRateService service;
	
	@RequestMapping("/queryall")
	@ResponseBody
	public ServerResponse<List<AttendanceRate>> queryAllAttendanceRateToPage(HttpServletRequest request) {
		int pageIndex = 1;
		if (request.getParameter("pageIndex") != null) {
			pageIndex = Integer.parseInt((String) request.getParameter("pageIndex"));
		}
		int pageNumber = service.pagecount();
		PageSplit pSplit = new PageSplit();
		PageUtil pUtil = new PageUtil();
		pUtil = pSplit.getPageIndex(pageIndex, pageNumber);
		return service.queryAllAttendanceRate(pUtil);
	}
}

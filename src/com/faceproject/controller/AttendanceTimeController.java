package com.faceproject.controller;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.faceproject.beans.AttendanceTime;
import com.faceproject.service.AttendanceTimeService;
import com.faceproject.util.PageSplit;
import com.faceproject.util.PageUtil;
import com.faceproject.util.ServerResponse;

// 考勤时间信息
@Controller
@RequestMapping("/admin/time")
public class AttendanceTimeController {

	@Autowired
	private AttendanceTimeService service;
	
	/*
	 * 逻辑：
	 * 上班打卡：在考勤时间表 创建一个条目。
	 * 下班打卡：在考勤时间表寻找上班打卡的条目，进行更新。
	 * 个人不允许进行删除操作。
	 * 删除操作由系统指定时间完成。
	 */

	@RequestMapping("/queryall")
	@ResponseBody
	public ServerResponse<List<AttendanceTime>> queryAllAttendanceTimeToPage(HttpServletRequest request) {
		int pageIndex = 1;
		if (request.getParameter("pageIndex") != null) {
			pageIndex = Integer.parseInt((String) request.getParameter("pageIndex"));
		}
		int pageNumber = service.pagecount();
		PageSplit pSplit = new PageSplit();
		PageUtil pUtil = new PageUtil();
		pUtil = pSplit.getPageIndex(pageIndex, pageNumber);
		ServerResponse<List<AttendanceTime>> response = service.queryAllAttendanceTime(pUtil);
		return response;
	}
	
	// 下班打卡
	@RequestMapping("/update")
	@ResponseBody
	public ServerResponse<String> updateAttendanceTime(@RequestBody Map<String, String> map) {
		AttendanceTime attendanceTime = new AttendanceTime();
		Integer id = Integer.parseInt(map.get("id"));
		attendanceTime.setAtEmployeeId(id);
		
		Date date = new Date();
		SimpleDateFormat sdfDate = new SimpleDateFormat();
		sdfDate.applyPattern("yyyy-MM-dd");
		attendanceTime.setAtDate(sdfDate.format(date));

		SimpleDateFormat sdfTime = new SimpleDateFormat();
		sdfTime.applyPattern("yyyy-MM-dd HH:mm:ss");
		attendanceTime.setAtEnd(sdfTime.format(date));
		
		return service.updateAttendanceTime(attendanceTime);
	}

	// 上班打卡
	@RequestMapping("/create")
	@ResponseBody
	public ServerResponse<String> createAttendanceTime(@RequestBody Map<String, String> map) {
		AttendanceTime attendanceTime = new AttendanceTime();
		Integer id = Integer.parseInt(map.get("id"));
		attendanceTime.setAtEmployeeId(id);
		
		Date date = new Date();
		SimpleDateFormat sdfDate = new SimpleDateFormat();
		sdfDate.applyPattern("yyyy-MM-dd");
		attendanceTime.setAtDate(sdfDate.format(date));
		
		SimpleDateFormat sdfTime = new SimpleDateFormat();
		sdfTime.applyPattern("yyyy-MM-dd HH:mm:ss");
		attendanceTime.setAtStart(sdfTime.format(date));
		
		return service.createAttendanceTime(attendanceTime);
	}

}

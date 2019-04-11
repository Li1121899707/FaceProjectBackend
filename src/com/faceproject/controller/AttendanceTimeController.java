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

// ����ʱ����Ϣ
@Controller
@RequestMapping("/admin/time")
public class AttendanceTimeController {

	@Autowired
	private AttendanceTimeService service;
	
	/*
	 * �߼���
	 * �ϰ�򿨣��ڿ���ʱ��� ����һ����Ŀ��
	 * �°�򿨣��ڿ���ʱ���Ѱ���ϰ�򿨵���Ŀ�����и��¡�
	 * ���˲��������ɾ��������
	 * ɾ��������ϵͳָ��ʱ����ɡ�
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
	
	// �°��
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

	// �ϰ��
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

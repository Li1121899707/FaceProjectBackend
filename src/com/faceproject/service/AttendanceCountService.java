package com.faceproject.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faceproject.beans.AttendanceCount;
import com.faceproject.beans.CountComplete;
import com.faceproject.beans.EmployeeInfo;
import com.faceproject.mapper.AttendanceCountMapper;
import com.faceproject.util.PageUtil;
import com.faceproject.util.ServerResponse;
import com.sun.xml.internal.ws.api.message.saaj.SaajStaxWriter;

@Service
public class AttendanceCountService {
	@Autowired
	private AttendanceCountMapper mapper;
	public static String TODAY = "2018-12-02";

	// 查询考勤统计信息总条目
	public Integer pagecount() {
		return mapper.pagecount();
	}

	// 查询所有考勤统计信息
	public ServerResponse<List<AttendanceCount>> queryAllAttendanceCount(PageUtil pUtil) {
		// DAO层查询数据
		List<AttendanceCount> studentList = mapper.queryAllAttendanceCount(pUtil.getDaoIndex());
		if (studentList.size() > 0) {
			return ServerResponse.createBySuccess("查询成功", studentList, pUtil);
		}
		return ServerResponse.createByError("查询记录为空");
	}
	
	
	public ServerResponse<String> isCountComplete() {
		Date date = new Date();
		SimpleDateFormat sdfDate = new SimpleDateFormat();
		sdfDate.applyPattern("yyyy-MM-dd");
		String daoDate = sdfDate.format(date);
		TODAY = daoDate;
		CountComplete complete = mapper.isCountComplete(TODAY);	
		if(null == complete) {
			System.out.println("今日考勤信息还未生成过");
			generate();
			return ServerResponse.createBySuccess("今日考勤信息生成完毕");
		}
		else if("1".equals(complete.getCcComplete()))
			return ServerResponse.createByError("今日考勤信息已经生成过了！");
		else 
			return ServerResponse.createByError("出现内部错误！");
	}
	
	public void generate() {
		updateTempDate();
		createCountForm();
		queryAllAbsence();
		queryAllLate();
		queryAllEarly();
		createCountComplete();
	}
	
	// 创建今日考勤统计表
	public void createCountForm() {
		List<EmployeeInfo> list = mapper.queryAllEmployeeInfo();
		mapper.createCount(TODAY, list);
	}

	// 给所有缺勤员工做统计
	public void queryAllAbsence() {
		List<AttendanceCount> list = mapper.queryAllAbsence();
		for(int i=0; i<list.size(); i++)
			mapper.updateCountAbsence(TODAY, list.get(i).getArEmployeeId());
	}

	// 给所有迟到员工做统计
	public void queryAllLate() {
		List<AttendanceCount> list = mapper.queryAllLate();
		for(int i=0; i<list.size(); i++)
			mapper.updateCountLate(TODAY, list.get(i).getArEmployeeId());
	}

	// 给所有早退员工做统计
	public void queryAllEarly() {
		List<AttendanceCount> list = mapper.queryAllEarly();
		for(int i=0; i<list.size(); i++)
			mapper.updateCountEarly(TODAY, list.get(i).getArEmployeeId());
	}
	
	// 添加生成成功信息
	public void createCountComplete() {
		mapper.createCountComplete(TODAY);
	}

	public void updateTempDate() {
		mapper.updateTempDate(TODAY);
	}
}

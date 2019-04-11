package com.faceproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faceproject.beans.AttendanceTime;
import com.faceproject.mapper.AttendanceTimeMapper;
import com.faceproject.util.PageUtil;
import com.faceproject.util.ServerResponse;

@Service
public class AttendanceTimeService {
	@Autowired
	private AttendanceTimeMapper mapper;

	// 查询考勤时间信息总条目
	public Integer pagecount() {
		return mapper.pagecount();
	}

	// 查询所有考勤时间信息
	public ServerResponse<List<AttendanceTime>> queryAllAttendanceTime(PageUtil pUtil) {
		// DAO层查询数据
		List<AttendanceTime> studentList = mapper.queryAllAttendanceTime(pUtil.getDaoIndex());
		if (studentList.size() > 0) {
			return ServerResponse.createBySuccess("查询成功", studentList, pUtil);
		}
		return ServerResponse.createByError("查询记录为空");
	}

	// 修改某个考勤时间信息
	public ServerResponse<String> updateAttendanceTime(AttendanceTime attendanceTime) {
		AttendanceTime isSuccess = mapper.queryEmployeeEndTime(attendanceTime);
		if(isSuccess == null)
			return ServerResponse.createByError("下班打卡失败，您可能上班忘记打卡了！请联系工作人员！");
		if(isSuccess.getAtEnd() != null)
			return ServerResponse.createByError("您下班已经打过卡了！请勿重复打卡！");
		
		int result = mapper.updateAttendanceTime(attendanceTime);
		if (result != 0) {
			return ServerResponse.createBySuccess("下班打卡成功。");
		} else {
			return ServerResponse.createByError("下班打卡失败，请联系工作人员！");
		}
	}

	// 删除某个考勤时间信息
	/*public ServerResponse<String> deleteAttendanceTime(int id) {
		int result = mapper.deleteAttendanceTime(id);
		if (result != 0) {
			return ServerResponse.createBySuccess("删除成功");
		} else {
			return ServerResponse.createByError("删除失败");
		}
	}*/

	// 添加某个考勤时间信息
	public ServerResponse<String> createAttendanceTime(AttendanceTime attendanceTime) {
		AttendanceTime isSuccess = mapper.queryEmployeeStartTime(attendanceTime);
		if(isSuccess != null)
			return ServerResponse.createByError("您上班已经打过卡了！请勿重复打卡！");
		
		int result = mapper.createAttendanceTime(attendanceTime);
		if (result != 0) {
			return ServerResponse.createBySuccess("上班打卡成功");
		} else {
			return ServerResponse.createByError("上班打卡失败，疑似系统故障，请联系管理员");
		}
	}

}

package com.faceproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.faceproject.beans.AttendanceTime;

public interface AttendanceTimeMapper {
	// 查询所有考勤信息
	public List<AttendanceTime> queryAllAttendanceTime(@Param(value="index")int index);
	// 查询考勤信息总条目
	public Integer pagecount();
	// 修改某个考勤信息
	public Integer updateAttendanceTime(AttendanceTime attendanceTime);
	// 删除某个考勤信息
	//public Integer deleteAttendanceTime(@Param(value="id")int id);
	// 添加某个考勤信息
	public Integer createAttendanceTime(AttendanceTime attendanceTime);
	
	public AttendanceTime queryEmployeeStartTime(AttendanceTime attendanceTime);
	
	public AttendanceTime queryEmployeeEndTime(AttendanceTime attendanceTime);
}

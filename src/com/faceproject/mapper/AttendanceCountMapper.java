package com.faceproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.faceproject.beans.AttendanceCount;
import com.faceproject.beans.CountComplete;
import com.faceproject.beans.EmployeeInfo;

public interface AttendanceCountMapper {
	// 查询所有统计信息
	public List<AttendanceCount> queryAllAttendanceCount(@Param(value="index")int index);
	// 查询统计信息总条目
	public Integer pagecount();
	// 查询所有迟到员工ID
	public List<AttendanceCount> queryAllLate();
	//查询所有早退员工ID
	public List<AttendanceCount> queryAllEarly();
	//查询所有缺勤员工ID
	public List<AttendanceCount> queryAllAbsence();
	// 查询所有员工ID
	public List<EmployeeInfo> queryAllEmployeeInfo();
	// 添加缺勤记录
	public Integer createCount(@Param(value="date")String date, @Param("list") List<EmployeeInfo> list);
	// 更新
	public Integer updateCountLate(@Param(value="date")String date, @Param(value="eiid")int eiid);
	// 更新
	public Integer updateCountEarly(@Param(value="date")String date, @Param(value="eiid")int eiid);
	// 更新
	public Integer updateCountAbsence(@Param(value="date")String date, @Param(value="eiid")int eiid);
	// 判断是否已经生成今天的考勤统计
	public CountComplete isCountComplete(@Param(value="date")String date);
	// 添加生成成功信息
	public Integer createCountComplete(@Param(value="date")String date);
	// 更新临时表
	public Integer updateTempDate(@Param(value="date")String date);
}

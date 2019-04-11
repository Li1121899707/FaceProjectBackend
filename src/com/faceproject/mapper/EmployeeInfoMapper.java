package com.faceproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.faceproject.beans.EmployeeInfo;
import com.faceproject.beans.TempDate;

public interface EmployeeInfoMapper {
	// 查询所有员工信息
	public List<EmployeeInfo> queryAllEmployeeInfo(@Param(value = "index") int index);

	// 查询一位员工信息
	public EmployeeInfo queryOneEmployeeInfo(EmployeeInfo employeeInfo);
	
	// 查询最大ID
	public EmployeeInfo queryMaxId();

	// 查询员工信息总条目
	public Integer pagecount();

	// 修改某个员工信息
	public Integer updateEmployeeInfo(EmployeeInfo employeeInfo);

	// 删除某个员工信息
	public Integer deleteEmployeeInfo(@Param(value = "id") int id);

	// 添加某个员工信息
	public Integer createEmployeeInfo(EmployeeInfo employeeInfo);

	// 修改临时表
	public Integer createTempEmployeeInfo(TempDate tempDate);
}

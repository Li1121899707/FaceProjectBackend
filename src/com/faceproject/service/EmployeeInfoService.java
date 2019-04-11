package com.faceproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faceproject.beans.EmployeeInfo;
import com.faceproject.beans.TempDate;
import com.faceproject.mapper.EmployeeInfoMapper;
import com.faceproject.util.PageUtil;
import com.faceproject.util.ServerResponse;

@Service
public class EmployeeInfoService {
	@Autowired
	private EmployeeInfoMapper mapper;
	
	// 查询员工信息总条目
	public Integer pagecount() {
		return mapper.pagecount();
	}

	// 查询所有员工信息
	public ServerResponse<List<EmployeeInfo>> queryAllEmployeeInfo(PageUtil pUtil) {
		// DAO层查询数据
		List<EmployeeInfo> studentList = mapper.queryAllEmployeeInfo(pUtil.getDaoIndex());
		if (studentList.size() > 0) {
			return ServerResponse.createBySuccess("查询成功", studentList, pUtil);
		}
		return ServerResponse.createByError("查询记录为空");
	}

	// 查询一位员工信息
	public ServerResponse<EmployeeInfo> queryOneEmployeeInfo(EmployeeInfo employeeInfo) {
		// DAO层查询数据
		EmployeeInfo eInfo = mapper.queryOneEmployeeInfo(employeeInfo);
		if(eInfo == null)
			return ServerResponse.createByError("查询记录为空");
		else
			return ServerResponse.createBySuccess("查询成功", eInfo);
	}

	// 修改某个员工信息
	public ServerResponse<String> updateEmployeeInfo(EmployeeInfo employeeInfo) {
		int result = mapper.updateEmployeeInfo(employeeInfo);
		if (result != 0) {
			return ServerResponse.createBySuccess("更新成功");
		} else {
			return ServerResponse.createByError("更新失败");
		}
	}

	// 删除某个员工信息
	public ServerResponse<String> deleteEmployeeInfo(int id) {
		int result = mapper.deleteEmployeeInfo(id);
		if (result != 0) {
			return ServerResponse.createBySuccess("删除成功");
		} else {
			return ServerResponse.createByError("删除失败");
		}
	}

	// 添加某个员工信息
	public ServerResponse<String> createEmployeeInfo(EmployeeInfo employeeInfo) {
		// 重要！先获取ID，如果先添加数据，若人脸识别失败，该员工数据已经被写入数据库，还需要删除，此时自增主键已经缺失一位。
		String filenameORbase64 = employeeInfo.getEiFaceInfo();
		employeeInfo.setEiFaceInfo("");
		EmployeeInfo maxIdEmployee = mapper.queryMaxId();
		System.out.println("max id = " + maxIdEmployee);
		int userid = maxIdEmployee.getEiId() + 1;
		FaceDealService faceDealService = new FaceDealService();
		Boolean success = false;
		try {
			success = faceDealService.faceAdd(userid, filenameORbase64);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(!success)
			return ServerResponse.createByError("添加人脸失败，请重新拍摄");
		
		employeeInfo.setEiId(userid);
		int result = mapper.createEmployeeInfo(employeeInfo);
		if (result == 0)
			return ServerResponse.createByError("添加个人信息失败，请联系管理员");
		
		TempDate tempDate = new TempDate();
		tempDate.setTdEmployeeId(userid);
		int tempResult = mapper.createTempEmployeeInfo(tempDate);
		if(tempResult == 0)
			return ServerResponse.createByError("添加个人信息失败，请联系管理员");
		else
			return ServerResponse.createBySuccess("注册成功");
	}

}

package com.faceproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.faceproject.beans.AdminInfo;
import com.faceproject.mapper.AdminInfoMapper;
import com.faceproject.util.ServerResponse;

@Service
public class AdminInfoService {
	@Autowired
	private AdminInfoMapper mapper;

	// 查询所有考勤时间信息
	public ServerResponse<AdminInfo> queryAdminInfo(AdminInfo adminInfo) {
		AdminInfo admin = mapper.queryAdminInfo(adminInfo);
		if(admin == null)
			return ServerResponse.createByError("登录失败");
		else 
			return ServerResponse.createBySuccess("登录成功",admin);
	}
}

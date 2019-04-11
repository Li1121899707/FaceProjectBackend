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

	// ��ѯ���п���ʱ����Ϣ
	public ServerResponse<AdminInfo> queryAdminInfo(AdminInfo adminInfo) {
		AdminInfo admin = mapper.queryAdminInfo(adminInfo);
		if(admin == null)
			return ServerResponse.createByError("��¼ʧ��");
		else 
			return ServerResponse.createBySuccess("��¼�ɹ�",admin);
	}
}

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
	
	// ��ѯԱ����Ϣ����Ŀ
	public Integer pagecount() {
		return mapper.pagecount();
	}

	// ��ѯ����Ա����Ϣ
	public ServerResponse<List<EmployeeInfo>> queryAllEmployeeInfo(PageUtil pUtil) {
		// DAO���ѯ����
		List<EmployeeInfo> studentList = mapper.queryAllEmployeeInfo(pUtil.getDaoIndex());
		if (studentList.size() > 0) {
			return ServerResponse.createBySuccess("��ѯ�ɹ�", studentList, pUtil);
		}
		return ServerResponse.createByError("��ѯ��¼Ϊ��");
	}

	// ��ѯһλԱ����Ϣ
	public ServerResponse<EmployeeInfo> queryOneEmployeeInfo(EmployeeInfo employeeInfo) {
		// DAO���ѯ����
		EmployeeInfo eInfo = mapper.queryOneEmployeeInfo(employeeInfo);
		if(eInfo == null)
			return ServerResponse.createByError("��ѯ��¼Ϊ��");
		else
			return ServerResponse.createBySuccess("��ѯ�ɹ�", eInfo);
	}

	// �޸�ĳ��Ա����Ϣ
	public ServerResponse<String> updateEmployeeInfo(EmployeeInfo employeeInfo) {
		int result = mapper.updateEmployeeInfo(employeeInfo);
		if (result != 0) {
			return ServerResponse.createBySuccess("���³ɹ�");
		} else {
			return ServerResponse.createByError("����ʧ��");
		}
	}

	// ɾ��ĳ��Ա����Ϣ
	public ServerResponse<String> deleteEmployeeInfo(int id) {
		int result = mapper.deleteEmployeeInfo(id);
		if (result != 0) {
			return ServerResponse.createBySuccess("ɾ���ɹ�");
		} else {
			return ServerResponse.createByError("ɾ��ʧ��");
		}
	}

	// ���ĳ��Ա����Ϣ
	public ServerResponse<String> createEmployeeInfo(EmployeeInfo employeeInfo) {
		// ��Ҫ���Ȼ�ȡID�������������ݣ�������ʶ��ʧ�ܣ���Ա�������Ѿ���д�����ݿ⣬����Ҫɾ������ʱ���������Ѿ�ȱʧһλ��
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
			return ServerResponse.createByError("�������ʧ�ܣ�����������");
		
		employeeInfo.setEiId(userid);
		int result = mapper.createEmployeeInfo(employeeInfo);
		if (result == 0)
			return ServerResponse.createByError("��Ӹ�����Ϣʧ�ܣ�����ϵ����Ա");
		
		TempDate tempDate = new TempDate();
		tempDate.setTdEmployeeId(userid);
		int tempResult = mapper.createTempEmployeeInfo(tempDate);
		if(tempResult == 0)
			return ServerResponse.createByError("��Ӹ�����Ϣʧ�ܣ�����ϵ����Ա");
		else
			return ServerResponse.createBySuccess("ע��ɹ�");
	}

}

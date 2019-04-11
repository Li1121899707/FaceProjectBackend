package com.faceproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.faceproject.beans.EmployeeInfo;
import com.faceproject.beans.TempDate;

public interface EmployeeInfoMapper {
	// ��ѯ����Ա����Ϣ
	public List<EmployeeInfo> queryAllEmployeeInfo(@Param(value = "index") int index);

	// ��ѯһλԱ����Ϣ
	public EmployeeInfo queryOneEmployeeInfo(EmployeeInfo employeeInfo);
	
	// ��ѯ���ID
	public EmployeeInfo queryMaxId();

	// ��ѯԱ����Ϣ����Ŀ
	public Integer pagecount();

	// �޸�ĳ��Ա����Ϣ
	public Integer updateEmployeeInfo(EmployeeInfo employeeInfo);

	// ɾ��ĳ��Ա����Ϣ
	public Integer deleteEmployeeInfo(@Param(value = "id") int id);

	// ���ĳ��Ա����Ϣ
	public Integer createEmployeeInfo(EmployeeInfo employeeInfo);

	// �޸���ʱ��
	public Integer createTempEmployeeInfo(TempDate tempDate);
}

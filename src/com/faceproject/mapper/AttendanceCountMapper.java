package com.faceproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.faceproject.beans.AttendanceCount;
import com.faceproject.beans.CountComplete;
import com.faceproject.beans.EmployeeInfo;

public interface AttendanceCountMapper {
	// ��ѯ����ͳ����Ϣ
	public List<AttendanceCount> queryAllAttendanceCount(@Param(value="index")int index);
	// ��ѯͳ����Ϣ����Ŀ
	public Integer pagecount();
	// ��ѯ���гٵ�Ա��ID
	public List<AttendanceCount> queryAllLate();
	//��ѯ��������Ա��ID
	public List<AttendanceCount> queryAllEarly();
	//��ѯ����ȱ��Ա��ID
	public List<AttendanceCount> queryAllAbsence();
	// ��ѯ����Ա��ID
	public List<EmployeeInfo> queryAllEmployeeInfo();
	// ���ȱ�ڼ�¼
	public Integer createCount(@Param(value="date")String date, @Param("list") List<EmployeeInfo> list);
	// ����
	public Integer updateCountLate(@Param(value="date")String date, @Param(value="eiid")int eiid);
	// ����
	public Integer updateCountEarly(@Param(value="date")String date, @Param(value="eiid")int eiid);
	// ����
	public Integer updateCountAbsence(@Param(value="date")String date, @Param(value="eiid")int eiid);
	// �ж��Ƿ��Ѿ����ɽ���Ŀ���ͳ��
	public CountComplete isCountComplete(@Param(value="date")String date);
	// ������ɳɹ���Ϣ
	public Integer createCountComplete(@Param(value="date")String date);
	// ������ʱ��
	public Integer updateTempDate(@Param(value="date")String date);
}

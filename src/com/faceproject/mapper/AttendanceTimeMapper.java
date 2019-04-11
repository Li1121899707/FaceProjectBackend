package com.faceproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.faceproject.beans.AttendanceTime;

public interface AttendanceTimeMapper {
	// ��ѯ���п�����Ϣ
	public List<AttendanceTime> queryAllAttendanceTime(@Param(value="index")int index);
	// ��ѯ������Ϣ����Ŀ
	public Integer pagecount();
	// �޸�ĳ��������Ϣ
	public Integer updateAttendanceTime(AttendanceTime attendanceTime);
	// ɾ��ĳ��������Ϣ
	//public Integer deleteAttendanceTime(@Param(value="id")int id);
	// ���ĳ��������Ϣ
	public Integer createAttendanceTime(AttendanceTime attendanceTime);
	
	public AttendanceTime queryEmployeeStartTime(AttendanceTime attendanceTime);
	
	public AttendanceTime queryEmployeeEndTime(AttendanceTime attendanceTime);
}

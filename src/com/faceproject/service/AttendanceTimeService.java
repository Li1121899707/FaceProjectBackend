package com.faceproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faceproject.beans.AttendanceTime;
import com.faceproject.mapper.AttendanceTimeMapper;
import com.faceproject.util.PageUtil;
import com.faceproject.util.ServerResponse;

@Service
public class AttendanceTimeService {
	@Autowired
	private AttendanceTimeMapper mapper;

	// ��ѯ����ʱ����Ϣ����Ŀ
	public Integer pagecount() {
		return mapper.pagecount();
	}

	// ��ѯ���п���ʱ����Ϣ
	public ServerResponse<List<AttendanceTime>> queryAllAttendanceTime(PageUtil pUtil) {
		// DAO���ѯ����
		List<AttendanceTime> studentList = mapper.queryAllAttendanceTime(pUtil.getDaoIndex());
		if (studentList.size() > 0) {
			return ServerResponse.createBySuccess("��ѯ�ɹ�", studentList, pUtil);
		}
		return ServerResponse.createByError("��ѯ��¼Ϊ��");
	}

	// �޸�ĳ������ʱ����Ϣ
	public ServerResponse<String> updateAttendanceTime(AttendanceTime attendanceTime) {
		AttendanceTime isSuccess = mapper.queryEmployeeEndTime(attendanceTime);
		if(isSuccess == null)
			return ServerResponse.createByError("�°��ʧ�ܣ��������ϰ����Ǵ��ˣ�����ϵ������Ա��");
		if(isSuccess.getAtEnd() != null)
			return ServerResponse.createByError("���°��Ѿ�������ˣ������ظ��򿨣�");
		
		int result = mapper.updateAttendanceTime(attendanceTime);
		if (result != 0) {
			return ServerResponse.createBySuccess("�°�򿨳ɹ���");
		} else {
			return ServerResponse.createByError("�°��ʧ�ܣ�����ϵ������Ա��");
		}
	}

	// ɾ��ĳ������ʱ����Ϣ
	/*public ServerResponse<String> deleteAttendanceTime(int id) {
		int result = mapper.deleteAttendanceTime(id);
		if (result != 0) {
			return ServerResponse.createBySuccess("ɾ���ɹ�");
		} else {
			return ServerResponse.createByError("ɾ��ʧ��");
		}
	}*/

	// ���ĳ������ʱ����Ϣ
	public ServerResponse<String> createAttendanceTime(AttendanceTime attendanceTime) {
		AttendanceTime isSuccess = mapper.queryEmployeeStartTime(attendanceTime);
		if(isSuccess != null)
			return ServerResponse.createByError("���ϰ��Ѿ�������ˣ������ظ��򿨣�");
		
		int result = mapper.createAttendanceTime(attendanceTime);
		if (result != 0) {
			return ServerResponse.createBySuccess("�ϰ�򿨳ɹ�");
		} else {
			return ServerResponse.createByError("�ϰ��ʧ�ܣ�����ϵͳ���ϣ�����ϵ����Ա");
		}
	}

}

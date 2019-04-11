package com.faceproject.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faceproject.beans.AttendanceCount;
import com.faceproject.beans.CountComplete;
import com.faceproject.beans.EmployeeInfo;
import com.faceproject.mapper.AttendanceCountMapper;
import com.faceproject.util.PageUtil;
import com.faceproject.util.ServerResponse;
import com.sun.xml.internal.ws.api.message.saaj.SaajStaxWriter;

@Service
public class AttendanceCountService {
	@Autowired
	private AttendanceCountMapper mapper;
	public static String TODAY = "2018-12-02";

	// ��ѯ����ͳ����Ϣ����Ŀ
	public Integer pagecount() {
		return mapper.pagecount();
	}

	// ��ѯ���п���ͳ����Ϣ
	public ServerResponse<List<AttendanceCount>> queryAllAttendanceCount(PageUtil pUtil) {
		// DAO���ѯ����
		List<AttendanceCount> studentList = mapper.queryAllAttendanceCount(pUtil.getDaoIndex());
		if (studentList.size() > 0) {
			return ServerResponse.createBySuccess("��ѯ�ɹ�", studentList, pUtil);
		}
		return ServerResponse.createByError("��ѯ��¼Ϊ��");
	}
	
	
	public ServerResponse<String> isCountComplete() {
		Date date = new Date();
		SimpleDateFormat sdfDate = new SimpleDateFormat();
		sdfDate.applyPattern("yyyy-MM-dd");
		String daoDate = sdfDate.format(date);
		TODAY = daoDate;
		CountComplete complete = mapper.isCountComplete(TODAY);	
		if(null == complete) {
			System.out.println("���տ�����Ϣ��δ���ɹ�");
			generate();
			return ServerResponse.createBySuccess("���տ�����Ϣ�������");
		}
		else if("1".equals(complete.getCcComplete()))
			return ServerResponse.createByError("���տ�����Ϣ�Ѿ����ɹ��ˣ�");
		else 
			return ServerResponse.createByError("�����ڲ�����");
	}
	
	public void generate() {
		updateTempDate();
		createCountForm();
		queryAllAbsence();
		queryAllLate();
		queryAllEarly();
		createCountComplete();
	}
	
	// �������տ���ͳ�Ʊ�
	public void createCountForm() {
		List<EmployeeInfo> list = mapper.queryAllEmployeeInfo();
		mapper.createCount(TODAY, list);
	}

	// ������ȱ��Ա����ͳ��
	public void queryAllAbsence() {
		List<AttendanceCount> list = mapper.queryAllAbsence();
		for(int i=0; i<list.size(); i++)
			mapper.updateCountAbsence(TODAY, list.get(i).getArEmployeeId());
	}

	// �����гٵ�Ա����ͳ��
	public void queryAllLate() {
		List<AttendanceCount> list = mapper.queryAllLate();
		for(int i=0; i<list.size(); i++)
			mapper.updateCountLate(TODAY, list.get(i).getArEmployeeId());
	}

	// ����������Ա����ͳ��
	public void queryAllEarly() {
		List<AttendanceCount> list = mapper.queryAllEarly();
		for(int i=0; i<list.size(); i++)
			mapper.updateCountEarly(TODAY, list.get(i).getArEmployeeId());
	}
	
	// ������ɳɹ���Ϣ
	public void createCountComplete() {
		mapper.createCountComplete(TODAY);
	}

	public void updateTempDate() {
		mapper.updateTempDate(TODAY);
	}
}

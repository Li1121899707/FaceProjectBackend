package com.faceproject.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.faceproject.beans.AttendanceRate;

public interface AttendanceRateMapper {
	// ��ѯ���п�����Ϣ
	public List<AttendanceRate> queryAllAttendanceRate(@Param(value="ldate")String ldate, @Param(value="rdate")String rdate,@Param(value="index")int index);
	// ��ѯ������Ϣ����Ŀ
	public Integer pagecount();
}

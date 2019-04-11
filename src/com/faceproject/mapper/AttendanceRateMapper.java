package com.faceproject.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.faceproject.beans.AttendanceRate;

public interface AttendanceRateMapper {
	// 查询所有考勤信息
	public List<AttendanceRate> queryAllAttendanceRate(@Param(value="ldate")String ldate, @Param(value="rdate")String rdate,@Param(value="index")int index);
	// 查询考勤信息总条目
	public Integer pagecount();
}

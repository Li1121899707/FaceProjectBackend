package com.faceproject.service;

import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.faceproject.beans.AttendanceRate;
import com.faceproject.mapper.AttendanceRateMapper;
import com.faceproject.util.PageUtil;
import com.faceproject.util.ServerResponse;

@Service
public class AttendanceRateService {
	@Autowired
	private AttendanceRateMapper mapper;

	// 查询考勤时间信息总条目
	public Integer pagecount() {
		return mapper.pagecount();
	}
	private Calendar getDate() {
		Calendar calendar  = Calendar.getInstance();
		return calendar;
	}
	private String getldate() {
		Calendar calendar = getDate();
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		Integer month = calendar.get(Calendar.MONTH) + 1;
		String smonth = month > 10 ? String.valueOf(month) : ("0" + month);
		return year + "-" + smonth + "-01";
	}
	private String getrdate() {
		Calendar calendar = getDate();
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		Integer month = calendar.get(Calendar.MONTH) + 1;
		String smonth = month > 10 ? String.valueOf(month) : ("0" + month);
		int dayNum = calendar.get(Calendar.DAY_OF_MONTH);
		String sday = dayNum > 10 ? String.valueOf(dayNum) : ("0" + dayNum);
		return year + "-" + smonth + "-" +  sday;
	}
	// 查询所有考勤时间信息
	public ServerResponse<List<AttendanceRate>> queryAllAttendanceRate(PageUtil pUtil) {
		// DAO层查询数据
		List<AttendanceRate> rateList = mapper.queryAllAttendanceRate(getldate(),getrdate(),pUtil.getDaoIndex());
		if (rateList.size() > 0) {
			return ServerResponse.createBySuccess("查询成功", rateList, pUtil);
		}
		return ServerResponse.createByError("查询记录为空");
	}
}

package com.faceproject.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.faceproject.beans.EmployeeInfo;
import com.faceproject.service.EmployeeInfoService;
import com.faceproject.service.FaceDealService;
import com.faceproject.util.PageSplit;
import com.faceproject.util.PageUtil;
import com.faceproject.util.ServerResponse;

// 员工信息
@Controller
@RequestMapping("/admin/employee")
public class EmployeeInfoController {

	@Autowired
	private EmployeeInfoService service;

	/**
	 * 查询员工个人信息的所有数据，包含了分页 逻辑：Controller为路由（包括分页），查询出当前分页得出的要查询数据库的下标，传给Service。
	 * Service调用Mapper，Mapper访问数据库，得到数据返回给Service，Service对数据加以封装，加入状态码、数据、分页标记，
	 * 返回给Controoler，Controller返回给前端。 注意：1. 加@ResponseBody注解，才可发送json数据
	 * 2.Controller、 Service均有@Controller/@Service注解。
	 * 3.Controller使用Service对象时，需要添加注解@Autowired自动注入，否则出现空异常。
	 * 4.Service对数据进行封装时，按理需要按照接口定义的数据返回，而不是根据javaBean类的数据返回。为了简便，可以直接更改一下JAVA Bean类
	 * 返回给前端数据，前端使用JAVA Bean类定义的名称进行取值。 5. 后端取前端传来的值需要使用接口文档中的定义。 5.前台拦截多次提交的情况
	 */
	@RequestMapping("/queryall")
	@ResponseBody
	public ServerResponse<List<EmployeeInfo>> queryAllEmployeeInfoToPage(HttpServletRequest request) {
		int pageIndex = 1;
		if (request.getParameter("pageIndex") != null) {
			pageIndex = Integer.parseInt((String) request.getParameter("pageIndex"));
		}
		int pageNumber = service.pagecount();
		PageSplit pSplit = new PageSplit();
		PageUtil pUtil = new PageUtil();
		pUtil = pSplit.getPageIndex(pageIndex, pageNumber);
		ServerResponse<List<EmployeeInfo>> response = service.queryAllEmployeeInfo(pUtil);
		return response;
	}

	@RequestMapping("/update")
	@ResponseBody
	public ServerResponse<String> updateEmployeeInfo(@RequestBody Map<String, String> map) {
		Integer id = 0;
		String name = "";
		Integer job = 0;
		try {
			id = Integer.parseInt(map.get("id"));
			name = map.get("name");
			job = Integer.parseInt(map.get("job"));
		} catch (Exception e) {
			return ServerResponse.createByError("您的输入有误！");
		}

		EmployeeInfo employeeInfo = new EmployeeInfo();
		employeeInfo.setEiId(id);
		employeeInfo.setEiName(name);
		employeeInfo.setEiJob(job);
		return service.updateEmployeeInfo(employeeInfo);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public ServerResponse<String> deleteEmployeeInfo(@RequestBody Map<String, String> map) {
		Integer id = 0;
		try {
			id = Integer.parseInt(map.get("id"));
		} catch (Exception e) {
			return ServerResponse.createByError("您的输入有误！");
		}

		return service.deleteEmployeeInfo(id);
	}

	@RequestMapping("/create")
	@ResponseBody
	public ServerResponse<String> createEmployeeInfo(@RequestBody Map<String, String> map) {
		String name = "";
		String faceInfo = "";
		try {
			name = map.get("name");
			faceInfo = map.get("faceInfo");
		} catch (Exception e) {
			return ServerResponse.createByError("您的输入有误！");
		}

		// Integer job = Integer.parseInt(map.get("job"));
		EmployeeInfo employeeInfo = new EmployeeInfo();
		employeeInfo.setEiName(name);
		employeeInfo.setEiFaceInfo(faceInfo);
		employeeInfo.setEiJob(1);
		return service.createEmployeeInfo(employeeInfo);
	}


	@RequestMapping("/loginface")
	@ResponseBody
	public ServerResponse<EmployeeInfo> loginFace(@RequestBody Map<String, String> map) {
		String faceInfo = map.get("faceInfo");
		FaceDealService faceDealService = new FaceDealService();
		String stringId;
		try {
			stringId = faceDealService.matchFace(faceInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return ServerResponse.createByError("打卡失败，人脸无法识别！");
		}
		Integer employeeId = Integer.parseInt(stringId);
		System.out.println(employeeId);
		EmployeeInfo employeeInfo = new EmployeeInfo();
		employeeInfo.setEiId(employeeId);
		return service.queryOneEmployeeInfo(employeeInfo);
	}
}

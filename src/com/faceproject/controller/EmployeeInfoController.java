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

// Ա����Ϣ
@Controller
@RequestMapping("/admin/employee")
public class EmployeeInfoController {

	@Autowired
	private EmployeeInfoService service;

	/**
	 * ��ѯԱ��������Ϣ���������ݣ������˷�ҳ �߼���ControllerΪ·�ɣ�������ҳ������ѯ����ǰ��ҳ�ó���Ҫ��ѯ���ݿ���±꣬����Service��
	 * Service����Mapper��Mapper�������ݿ⣬�õ����ݷ��ظ�Service��Service�����ݼ��Է�װ������״̬�롢���ݡ���ҳ��ǣ�
	 * ���ظ�Controoler��Controller���ظ�ǰ�ˡ� ע�⣺1. ��@ResponseBodyע�⣬�ſɷ���json����
	 * 2.Controller�� Service����@Controller/@Serviceע�⡣
	 * 3.Controllerʹ��Service����ʱ����Ҫ���ע��@Autowired�Զ�ע�룬������ֿ��쳣��
	 * 4.Service�����ݽ��з�װʱ��������Ҫ���սӿڶ�������ݷ��أ������Ǹ���javaBean������ݷ��ء�Ϊ�˼�㣬����ֱ�Ӹ���һ��JAVA Bean��
	 * ���ظ�ǰ�����ݣ�ǰ��ʹ��JAVA Bean�ඨ������ƽ���ȡֵ�� 5. ���ȡǰ�˴�����ֵ��Ҫʹ�ýӿ��ĵ��еĶ��塣 5.ǰ̨���ض���ύ�����
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
			return ServerResponse.createByError("������������");
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
			return ServerResponse.createByError("������������");
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
			return ServerResponse.createByError("������������");
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
			return ServerResponse.createByError("��ʧ�ܣ������޷�ʶ��");
		}
		Integer employeeId = Integer.parseInt(stringId);
		System.out.println(employeeId);
		EmployeeInfo employeeInfo = new EmployeeInfo();
		employeeInfo.setEiId(employeeId);
		return service.queryOneEmployeeInfo(employeeInfo);
	}
}

package com.faceproject.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.faceproject.beans.AdminInfo;
import com.faceproject.service.AdminInfoService;
import com.faceproject.util.ServerResponse;

@Controller
@RequestMapping("/admin/login")
public class AdminInfoController {
	@Autowired
	private AdminInfoService service;
	
	@RequestMapping("/query")
	@ResponseBody
	public ServerResponse<AdminInfo> queryAdminInfoToPage(@RequestBody Map<String, String> map) {
		int id = 0;
		String pwd = "";
		try {
			id = Integer.parseInt(map.get("id"));
			pwd = map.get("pwd");
		} catch (Exception e) {
			return ServerResponse.createByError("ƒ˙µƒ ‰»Î”–ŒÛ£°");
		}
		
		AdminInfo admin = new AdminInfo();
		admin.setAiId(id);
		admin.setAiPwd(pwd);
		
		return service.queryAdminInfo(admin);
	}
}

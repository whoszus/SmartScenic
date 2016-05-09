package com.scenic.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.scenic.baseUitl.WriteJson;
import com.scenic.model.MUserinfo;
import com.scenic.service.IUserInfoService;

public class UserAction extends ActionSupport implements ModelDriven<MUserinfo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MUserinfo muser = new MUserinfo();

	@Autowired
	private IUserInfoService userService;

	/**
	 * http://luorong:8080/SmartScenic/user/user_register.action?userName=小一&
	 * userPassword=123
	 */
	public void register() {// 添加
		String json = null;
		if (userService.IsExisted(muser.getUserName()) != null) {
			json = "{\"message\":\"existed\"}";
		} else {
			MUserinfo mUserinfo = userService.register(muser);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			json = gson.toJson(mUserinfo);
		}
		WriteJson.writeToClient(json);
	}

	/**
	 * http://luorong:8080/SmartScenic/user/user_login.action?userName=小一&
	 * userPassword=123 登录
	 */
	public void login() {
		MUserinfo user = userService.login(muser.getUserName(), muser
				.getUserPassword());
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String json = gson.toJson(user);
		WriteJson.writeToClient(json);

	}

	/**
	 * http://luorong:8080/SmartScenic/user/user_update.action?userId=1&
	 * userPassword=123 更改用户信息
	 */
	public void update() {
		MUserinfo user = userService.update(muser.getUserId(), muser);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String json = gson.toJson(user);
		WriteJson.writeToClient(json);
	}

	/**
	 * http://luorong:8080/SmartScenic/user/user_delete.action?userId=1 删除用户
	 */
	public void delete() {
		String str = null;
		try {
			userService.delete(muser.getUserId());
			str = "{\"message\":\"success\"}";
		} catch (Exception e) {
			str = "{\"message\":\"failed\"}";
		} finally {
			WriteJson.writeToClient(str);
		}

	}

	@Override
	public MUserinfo getModel() {
		return muser;
	}

}

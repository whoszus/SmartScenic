package com.scenic.service;

import java.util.Date;

import com.scenic.model.MUserinfo;

import com.scenic.repo.pojo.Userinfo;

import net.sf.json.JSON;

//业务层接口
public interface IUserInfoService {

	public MUserinfo login(String userName, String userPassword);

	/**
	 * 
	 * @param user
	 * @return
	 */
	public MUserinfo register(MUserinfo user);

	/**
	 * 判断用户名是否存在
	 * 
	 * @param userName
	 * @return
	 */
	public Boolean IsExisted(String userName);

	/**
	 * 更改用户信息
	 * 
	 * @param id
	 * @param userinfo
	 * @return
	 */
	public MUserinfo update(Integer id, MUserinfo userinfo);

	/**
	 * 删除用户信息
	 * 
	 * @param id
	 * @return
	 */
	public void delete(Integer id);

	/**
	 * public String submit(MUserinfo model);
	 * 
	 * public JSON list();
	 * 
	 * 
	 * public Userinfo findById(String id);
	 */

}

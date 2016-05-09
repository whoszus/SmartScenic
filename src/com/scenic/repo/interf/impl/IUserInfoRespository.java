package com.scenic.repo.interf.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scenic.repo.pojo.Userinfo;

//持久层接口
public interface IUserInfoRespository extends JpaRepository<Userinfo, Integer> {

	@Query("select user from Userinfo user where user.userName = ?1")
	public Userinfo findByUserName(String name);

	@Query("select user from Userinfo user where user.userName = ?1 and user.userPassword = ?2")
	public Userinfo login(String userName, String userPassword);

	@Query("select user from Userinfo user where user.userId = ?1")
	public Userinfo findById(Integer userId);

}

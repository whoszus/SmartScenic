package com.scenic.model;

import java.util.Date;

import com.scenic.repo.pojo.Userinfo;

public class MUserinfo {// 用戶信息

	private Integer userId;
	private String userName;
	private String userPassword;
	private String userNickname;
	private String userSex;
	private String userHeadpicture;
	private Integer userRight;
	private String userEmail;
	private String userTel;
	private Date userBirthday;

	public MUserinfo() {

	}

	public MUserinfo(Userinfo user) {
		this.userId = user.getUserId();
		this.userPassword = user.getUserPassword();
		this.userName = user.getUserName();
		this.userNickname = user.getUserNickname();
		this.userSex = user.getUserSex();
		this.userHeadpicture = user.getUserHeadpicture();
		this.userRight = user.getUserRight();
		this.userEmail = user.getUserEmail();
		this.userTel = user.getUserTel();
		this.userBirthday = user.getUserBirthday();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserHeadpicture() {
		return userHeadpicture;
	}

	public void setUserHeadpicture(String userHeadpicture) {
		this.userHeadpicture = userHeadpicture;
	}

	public Integer getUserRight() {
		return userRight;
	}

	public void setUserRight(Integer userRight) {
		this.userRight = userRight;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public Date getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}

}

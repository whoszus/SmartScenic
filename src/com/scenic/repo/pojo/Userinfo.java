package com.scenic.repo.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.scenic.model.MUserinfo;

/**
 * Userinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "userinfo", catalog = "smartscenic1")
public class Userinfo implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String userPassword;
	private String userName;
	private String userNickname;
	private String userSex;
	private String userHeadpicture;
	private Integer userRight;
	private String userEmail;
	private String userTel;
	private Date userBirthday;

	public Userinfo(MUserinfo user) {
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

	// Constructors

	/** default constructor */
	public Userinfo() {
	}

	/** minimal constructor */
	public Userinfo(String userPassword) {
		this.userPassword = userPassword;
	}

	/** full constructor */
	public Userinfo(String userPassword, String userName, String userNickname,
			String userSex, String userHeadpicture, Integer userRight,
			String userEmail, String userTel, Date userBirthday) {
		this.userPassword = userPassword;
		this.userName = userName;
		this.userNickname = userNickname;
		this.userSex = userSex;
		this.userHeadpicture = userHeadpicture;
		this.userRight = userRight;
		this.userEmail = userEmail;
		this.userTel = userTel;
		this.userBirthday = userBirthday;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "user_password", nullable = false, length = 30)
	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Column(name = "user_name", length = 30)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "user_nickname", length = 30)
	public String getUserNickname() {
		return this.userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	@Column(name = "user_sex", length = 2)
	public String getUserSex() {
		return this.userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	@Column(name = "user_headpicture", length = 64)
	public String getUserHeadpicture() {
		return this.userHeadpicture;
	}

	public void setUserHeadpicture(String userHeadpicture) {
		this.userHeadpicture = userHeadpicture;
	}

	@Column(name = "user_right")
	public Integer getUserRight() {
		return this.userRight;
	}

	public void setUserRight(Integer userRight) {
		this.userRight = userRight;
	}

	@Column(name = "user_email", length = 64)
	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Column(name = "user_tel", length = 11)
	public String getUserTel() {
		return this.userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "user_birthday", length = 10)
	public Date getUserBirthday() {
		return this.userBirthday;
	}

	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}

}
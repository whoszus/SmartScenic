/*package com.tvcloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tvcloud.repo.interf.UserRepository;
import com.tvcloud.repo.pojo.User;
import com.tvcloud.service.IUserService;

 *//**
 * �û�ҵ�����ʵ����
 * 
 * @author
 */
/*
 @Service("userService")
 public class UserServiceImpl implements IUserService{
 @Autowired
 private IUserRepository userRepository;// ע��UserRepository

 @Transactional
 public void saveUser(User user) {
 userRepository.save(user);

 }

 @Transactional(readOnly = true)
 public User findUserById(Integer id) {
 return userRepository.findOne(id);
 }

 @Transactional
 public void updateUser(User user) {
 userRepository.save(user);
 }

 @Transactional
 public void deleteUserById(Integer id) {
 userRepository.delete(id);
 }
 }
 */

package com.scenic.service.impl;

//import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scenic.model.MUserinfo;
import com.scenic.repo.interf.impl.IUserInfoRespository;
import com.scenic.repo.pojo.Userinfo;
import com.scenic.service.IUserInfoService;

@Service("userInfoService")
public class UserInfoServiceImpl implements IUserInfoService {
	// @Autowired
	// private IUserinfoRepository userinfoRepository;
	@Autowired
	private IUserInfoRespository userRepository;

	/**
	 * 注册，读取currCount时需要同步
	 **/
	@Transactional
	public synchronized String submit(MUserinfo model) {
		return null;
	}

	@Override
	public MUserinfo login(String userName, String userPassword) {
		MUserinfo muser = new MUserinfo(userRepository.login(userName,
				userPassword));

		if (muser == null)
			return null;

		return muser;
	}

	@Override
	public MUserinfo register(MUserinfo user) {
		Userinfo userinfo = new Userinfo(user);
		userRepository.save(userinfo);
		MUserinfo mUser = login(user.getUserName(), user.getUserPassword());
		System.out.println(mUser.getUserId());
		return mUser;
	}

	@Override
	public Boolean IsExisted(String userName) {
		if (userRepository.findByUserName(userName) != null) {
			return true;
		} else {
			return null;
		}
	}

	@Override
	public MUserinfo update(Integer id, MUserinfo userinfo) {

		Userinfo user = userRepository.findById(id);
		if (user == null)
			return null;

		if (userinfo.getUserPassword() != null) {
			user.setUserPassword(userinfo.getUserPassword());
		}

		if (userinfo.getUserNickname() != null) {
			user.setUserNickname(userinfo.getUserNickname());
		}

		if (userinfo.getUserSex() != null) {
			user.setUserSex(userinfo.getUserSex());
		}

		if (userinfo.getUserHeadpicture() != null) {
			user.setUserHeadpicture(userinfo.getUserHeadpicture());
		}
		if (userinfo.getUserEmail() != null) {
			user.setUserEmail(userinfo.getUserEmail());
		}
		if (userinfo.getUserTel() != null) {
			user.setUserTel(userinfo.getUserTel());
		}
		if (userinfo.getUserBirthday() != null) {
			user.setUserBirthday(userinfo.getUserBirthday());
		}
		userRepository.saveAndFlush(user);
		return new MUserinfo(user);
	}

	@Override
	public void delete(Integer id) {
		userRepository.delete(id);

	}

	/*
	 * @Override public JSON list() {//分页查询 Pageable pageable=new PageRequest(0,
	 * 10, new Sort(Direction.ASC, "userNickname")); Page<Userinfo>
	 * page=userRepository.findAll(pageable); MyPage m=new MyPage();
	 * m.setTotal(page.getTotalElements()); List<MUserinfo> list=new
	 * ArrayList<MUserinfo>() ; m.setDatas(list); for(Userinfo user:page){
	 * list.add(new MUserinfo(user)); } return JSONObject.fromObject(m); }
	 * 
	 * 
	 * 
	 * @Override public Userinfo findById(String id) { return
	 * userRepository.findOne(id); }
	 */

}

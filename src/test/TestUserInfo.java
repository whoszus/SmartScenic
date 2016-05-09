package test;

//import java.sql.Timestamp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.scenic.model.MUserinfo;
import com.scenic.repo.interf.impl.IUserInfoRespository;
import com.scenic.service.IUserInfoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( { "classpath:/applicationContext.xml" })
public class TestUserInfo {

	@Autowired
	private IUserInfoService userService;

	@Autowired
	private IUserInfoRespository userRepository;

	@Test
	public void testLogin() {
		MUserinfo mUserinfo = new MUserinfo(userRepository.login("小一", "123"));
		String json = new Gson().toJson(mUserinfo);
		System.out.println(json);
	}

	@Test
	public void testfindByUserName() {
		MUserinfo mUserinfo = new MUserinfo(userRepository.findByUserName("小一"));
		String json = new Gson().toJson(mUserinfo);
		System.out.println(json);
	}

	/**
	 * @Test public void testEdit(){ try { Userinfo user=new Userinfo();
	 *       user.setUserId("123"); user.setUserPassword("123");
	 *       user.setUserNickname("姣"); user.setUserSex("女");
	 *       user.setUserRight(Integer.valueOf("1"));
	 *       user.setUserClass(Integer.valueOf("1")); userRepository.save(user);
	 *       ;
	 * 
	 *       userRepository.findOne("123"); } catch (Exception e) {
	 *       e.printStackTrace(); }
	 * 
	 *       }
	 * @Test public void testFind(){ JSON json=userService.list();
	 *       System.out.println(json.toString()); }
	 * @Test public void Testupdate(){ MUserinfo muserinfo = new
	 *       MUserinfo(userRepository.findById(1)); String jsonString =new
	 *       Gson().toJson(muserinfo); System.out.println(jsonString);
	 * 
	 * 
	 *       }
	 */
}

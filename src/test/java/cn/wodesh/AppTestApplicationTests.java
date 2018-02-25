package cn.wodesh;
import cn.wodesh.bean.UserBean;
import cn.wodesh.dao.UserDao;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTestApplicationTests {

	@Autowired
	private UserDao templateDao;

	@Test
	public void contextLoads() throws Exception {
		UserBean userBean = new UserBean();
//		userBean.setUuid("6bcd52f51e9b3dce32bec4a3997715ac");
		userBean.setHeadurl("https://csdnimg.cn/release/edu/resource/images/special/180208/ui.jpg");
		userBean.setUsername("谭帅");
		userBean.setLogintime("2018-02-15 15:51:23");
		userBean.setToken("6bcd52f51e9b3dce32bec4a3997715ac_test");
//		templateDao.save(userBean);
//		templateDao.delete(userBean);
//		templateDao.deleteById(1 , UserBean.class);
//		UserBean bean = templateDao.findById("6bcd52f51e9b3dce32bec4a3997715ac" , UserBean.class);
//		UserBean bean = templateDao.findBySQLToBean("uuid = 2" , UserBean.class);
//		List list = templateDao.findBySQLToList("username = '谭帅'" , UserBean.class );
		Object obj = templateDao.findBySQLToList("select * from t_user where username = '谭帅'");
		System.out.println(JSONArray.toJSON(obj));
	}

}

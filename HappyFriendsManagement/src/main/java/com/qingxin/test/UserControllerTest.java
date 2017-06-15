package com.qingxin.test;

import com.qingxin.service.UserService;
import com.qingxin.user.bean.User;

import junit.framework.TestCase;

public class UserControllerTest extends TestCase {

	public void testCreate(){
		User anakinSkywalker = new User();
		anakinSkywalker.setMailAddress("AnakinSkywalker@google.com");
		User obiwanKonobi = new User();
		obiwanKonobi.setMailAddress("ObiwanKonobi@google.com");
		
		UserService service = UserService.getInstance();
		service.create(anakinSkywalker, obiwanKonobi);
	}

}

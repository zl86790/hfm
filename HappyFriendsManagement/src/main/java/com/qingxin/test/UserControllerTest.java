package com.qingxin.test;

import com.qingxin.exceptions.AlreadyInFriendsListException;
import com.qingxin.service.UserService;
import com.qingxin.user.bean.User;
import com.qingxin.user.controller.UserController;

import junit.framework.TestCase;

public class UserControllerTest extends TestCase {

	public void testCreate() throws AlreadyInFriendsListException {
		User anakinSkywalker = new User();
		anakinSkywalker.setMailAddress("AnakinSkywalker@google.com");
		User obiwanKonobi = new User();
		obiwanKonobi.setMailAddress("ObiwanKonobi@google.com");
		
		UserService service = UserService.getInstance();
		service.create(anakinSkywalker, obiwanKonobi);
		service.create(anakinSkywalker, obiwanKonobi);
	}

}

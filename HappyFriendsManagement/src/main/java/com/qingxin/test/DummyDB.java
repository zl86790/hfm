package com.qingxin.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qingxin.user.bean.User;

public class DummyDB {
	public static Map<String,User> users = new HashMap<String,User>();
	static{
		User andy = new User("andy@example.com");
		User john = new User("john@example.com");
		User tony = new User("tony@example.com");
		
		users.put("andy@example.com", andy);
		users.put("john@example.com", john);
		
		andy.getFriends().add(john);
		andy.getFriends().add(tony);
		john.getFriends().add(tony);
		
	}
}

package com.qingxin.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.qingxin.test.DummyDB;
import com.qingxin.user.bean.User;

public class UserService {
	
	static UserService userService = new UserService();
	Map<String,User> users = DummyDB.users;
	
	private UserService(){
		
	}
	
	public static synchronized UserService getInstance(){
		return userService;
	}
	
	public boolean create(User user1, User user2){
		
		User u1 = users.get(user1.getMailAddress());
		User u2 = users.get(user2.getMailAddress());
		
		List<String> mailAddressList1 = u1.getFriends().stream().map(User::getMailAddress).collect(Collectors.toList());
		if(!mailAddressList1.contains(u2.getMailAddress())){
			u1.getFriends().add(u2);
		}
		List<String> mailAddressList2 = u2.getFriends().stream().map(User::getMailAddress).collect(Collectors.toList());
		if(!mailAddressList2.contains(u1.getMailAddress())){
			u2.getFriends().add(u1);
		}
		return true;
	}
	
	public List<String> getFriends(User user){
		List<String> friends = users.get(user.getMailAddress()).getFriends().stream().map(User::getMailAddress).collect(Collectors.toList());
		return friends;
	}
}

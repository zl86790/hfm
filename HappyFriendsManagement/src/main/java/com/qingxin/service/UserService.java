package com.qingxin.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.qingxin.db.DummyDB;
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
		
		List<String> blockerList1 = u1.getBlockers().stream().map(User::getMailAddress).collect(Collectors.toList());
		List<String> blockerList2 = u2.getBlockers().stream().map(User::getMailAddress).collect(Collectors.toList());
		
		if(blockerList1.contains(u2.getMailAddress()) || blockerList2.contains(u1.getMailAddress())){
			return false;
		}
		
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
	
	public List<String> getRecipients(User user){
		List<String> recipients = users.get(user.getMailAddress()).getObservers().stream().map(User::getMailAddress).collect(Collectors.toList());
		return recipients;
	}

	public List<String> getCommonFriends(List<String> friends1, List<String> friends2) {
		friends1.retainAll(friends2);
		return friends1;
	}
	
	public boolean subscribe(User requestor, User target){
		
		User u1 = users.get(requestor.getMailAddress());
		User u2 = users.get(target.getMailAddress());
		
		List<String> mailAddressList = u2.getObservers().stream().map(User::getMailAddress).collect(Collectors.toList());
		if(!mailAddressList.contains(u1.getMailAddress())){
			u2.getObservers().add(u1);
		}
		return true;
	}

	public boolean block(User requestor, User target) {
		User u1 = users.get(requestor.getMailAddress());
		User u2 = users.get(target.getMailAddress());
		
		List<String> blokerList = u1.getBlockers().stream().map(User::getMailAddress).collect(Collectors.toList());
		if(!blokerList.contains(u2.getMailAddress())){
			u1.getBlockers().add(u2);
		}
		
		List<String> friendList = u1.getFriends().stream().map(User::getMailAddress).collect(Collectors.toList());
		if(!friendList.contains(u2.getMailAddress())){
			for(int i=0;i<u2.getObservers().size();i++){
				User u = u2.getObservers().get(i);
				if(u.getMailAddress().equals(u1.getMailAddress())){
					u2.getObservers().remove(i);
					break;
				}
			}
		}
		
		return true;
	}
}

package com.qingxin.user.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.qingxin.service.UserService;
import com.qingxin.user.bean.User; 

@Path("/user")
public class UserController {
	@POST  
	@Path("/getFriends")
	@Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON)  
    public String getFriends(JSONObject obj) {  
		JSONObject result = new JSONObject();
		UserService userService = UserService.getInstance();
		try {
			String mailAdress = obj.get("email").toString();
			List<String> friends = userService.getFriends(new User(mailAdress));
			
			result.put("success", true);
			result.put("friends", friends);
			result.put("count", friends.size());
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return result.toString();
    }
	
	@POST  
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON)  
    public String create(JSONObject obj) {
		UserService userService = UserService.getInstance();
		JSONObject result = new JSONObject();
		
		try {
			JSONArray array = (JSONArray) obj.get("friends");
			
			userService.create(new User(array.get(0).toString()),new User(array.get(1).toString()));
			boolean success = userService.create(new User(array.get(0).toString()), new User(array.get(1).toString()));
			
			result.put("success", success);
		} catch (JSONException e) {
			try {
				result.put("success", false);
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			
		} 
		
		return result.toString();  
        
    }
	
	@POST  
	@Path("/getCommonFriends")
	@Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON)  
    public String getCommonFriends(JSONObject obj) {  
		JSONObject result = new JSONObject();
		UserService userService = UserService.getInstance();
		try {
			JSONArray array = (JSONArray) obj.get("friends");
			
			List<String> friends1 = userService.getFriends(new User(array.get(0).toString()));
			List<String> friends2 = userService.getFriends(new User(array.get(1).toString()));
			
			friends1.retainAll(friends2);
			
			result.put("success", true);
			result.put("friends", friends1);
			result.put("count", friends1.size());
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return result.toString();
    }
}

package com.qingxin.client;

import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class RestClient {
	public static void main(String args[]) {
		System.out.println(RestClient.getFriendsRequest());
		System.out.println(RestClient.sendCreateRequest());
		System.out.println(RestClient.getCommonFriendsRequest());
		System.out.println(RestClient.sendSubscribeRequest());
		System.out.println(RestClient.sendBlockRequest());
		System.out.println(RestClient.getRecipients());
		
	}

	private static String sendSubscribeRequest() {
		String jsonstr = "{'requestor': 'lisa@example.com','target': 'john@example.com'}";
		JSONObject obj;
		try {
			obj = new JSONObject(jsonstr);
			return sendPostRequest(obj,"subscribe");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static String sendBlockRequest() {
		String jsonstr = "{'requestor': 'andy@example.com','target': 'john@example.com'}";
		JSONObject obj;
		try {
			obj = new JSONObject(jsonstr);
			return sendPostRequest(obj,"block");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String getCommonFriendsRequest() {
		String jsonstr = "{friends:['andy@example.com','john@example.com']}";
		JSONObject obj;
		try {
			obj = new JSONObject(jsonstr);
			return sendPostRequest(obj,"getCommonFriends");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String getRecipients() {
		String jsonstr = "{sender:'andy@example.com','text': 'Hello World! kate@example.com'}";
		JSONObject obj;
		try {
			obj = new JSONObject(jsonstr);
			return sendPostRequest(obj,"getRecipients");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static String getFriendsRequest() {
		String jsonstr = "{email:'andy@example.com'}";
		JSONObject obj;
		try {
			obj = new JSONObject(jsonstr);
			return sendPostRequest(obj,"getFriends");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String sendCreateRequest(){
		String jsonstr = "{friends:['andy@example.com','john@example.com']}";
		JSONObject obj;
		try {
			obj = new JSONObject(jsonstr);
			return sendPostRequest(obj,"create");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String sendPostRequest(JSONObject obj, String functionPath) {
		ClientConfig cc = new DefaultClientConfig();
		Client client = Client.create(cc);
		WebResource resource = client.resource("http://127.0.0.1:8888/api/user/v1/" + functionPath);

		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, obj);

		String returnedString = response.getEntity(String.class);
		return returnedString;
	}

	private static String sendGetRequest(String functionPath) {
		ClientConfig cc = new DefaultClientConfig();
		Client client = Client.create(cc);
		WebResource resource = client.resource("http://127.0.0.1:8888/api/user/v1/" + functionPath);

		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
				.get(ClientResponse.class);

		String returnedString = response.getEntity(String.class);
		return returnedString;
	}

}

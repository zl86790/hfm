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
		RestClient.getFriendsRequest();
		RestClient.sendCreateRequest();
	}

	private static void getFriendsRequest() {
		String jsonstr = "{email:'andy@example.com'}";
		JSONObject obj;
		try {
			obj = new JSONObject(jsonstr);
			sendPostRequest(obj,"getFriends");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private static void sendCreateRequest(){
		String jsonstr = "{friends:['andy@example.com','john@example.com']}";
		JSONObject obj;
		try {
			obj = new JSONObject(jsonstr);
			sendPostRequest(obj,"create");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}

	private static void sendPostRequest(JSONObject obj, String functionPath) {
		ClientConfig cc = new DefaultClientConfig();
		Client client = Client.create(cc);
		WebResource resource = client.resource("http://127.0.0.1:8080/user/" + functionPath);

		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, obj);

		String returnedString = response.getEntity(String.class);
		System.out.println(returnedString);
	}

	private static void sendGetRequest(String functionPath) {
		ClientConfig cc = new DefaultClientConfig();
		Client client = Client.create(cc);
		WebResource resource = client.resource("http://127.0.0.1:8080/user/" + functionPath);

		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
				.get(ClientResponse.class);

		String returnedString = response.getEntity(String.class);
		System.out.println(returnedString);
	}

}

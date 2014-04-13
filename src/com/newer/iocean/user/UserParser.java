package com.newer.iocean.user;

import org.json.JSONException;
import org.json.JSONObject;

public class UserParser {
	
	private User user = null;
	
	public UserParser() {
		
		user = new User();
	}
	
	public User parserUserInfo(String userData)  {
		
		try {
			JSONObject jsonObject = new JSONObject(userData);
			
			user.setUid(jsonObject.getString("id"));
			user.setScreen_name(jsonObject.getString("screen_name"));
			user.setProfile_image_url(jsonObject.getString("profile_image_url"));
			user.setName(jsonObject.getString("name"));
			user.setLocation(jsonObject.getString("location"));
			user.setGender(jsonObject.getString("gender"));
			user.setAvatar_large(jsonObject.getString("avatar_large"));
			user.setFollowers_count(jsonObject.getInt("followers_count"));
			user.setFriends_count(jsonObject.getInt("friends_count"));
			user.setFavourites_count(jsonObject.getInt("favourites_count"));
			user.setStatuses_count(jsonObject.getInt("statuses_count"));
			
			
			
		} catch (JSONException e) {
			user = null;
			e.printStackTrace();
		}
		
		return user;
		
	}

}

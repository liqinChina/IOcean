package com.newer.iocean.user;

public class User {

	private String uid; // 用户ID
	private String screen_name; // 用户昵称
	private String name; // 友好显示名称
	private String location; // 用户地址
	private String profile_image_url; // 用户图像URL
	private String gender; // 用户性别
	private String avatar_large; // 用户大图像地址
	private int followers_count; // 粉丝数
	private int friends_count; // 关注数
	private int statuses_count; // 微博数
	private int favourites_count; // 收藏数

	public static final String UID = "uid";
	public static final String SCREEN_NAME = "screen_name";
	public static final String NAME = "name";
	public static final String LOCATION = "location";
	public static final String PROFILE_IMAGE_URL = "profile_image_url";
	public static final String GENDER = "gender";
	public static final String AVATAR_LARGE = "avatar_large";
	public static final String FOLLOWERS_COUNT = "followers_count";
	public static final String FRIENDS_COUNT = "friends_count";
	public static final String STATUSES_COUNT = "statuses_count";
	public static final String FAVOURITES_COUNT = "favourites_count";

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String uid, String screen_name, String name, String location,
			String profile_image_url, String gender, String avatar_large,
			int followers_count, int friends_count, int statuses_count,
			int favourites_count) {
		super();
		this.uid = uid;
		this.screen_name = screen_name;
		this.name = name;
		this.location = location;
		this.profile_image_url = profile_image_url;
		this.gender = gender;
		this.avatar_large = avatar_large;
		this.followers_count = followers_count;
		this.friends_count = friends_count;
		this.statuses_count = statuses_count;
		this.favourites_count = favourites_count;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getScreen_name() {
		return screen_name;
	}

	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getProfile_image_url() {
		return profile_image_url;
	}

	public void setProfile_image_url(String profile_image_url) {
		this.profile_image_url = profile_image_url;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAvatar_large() {
		return avatar_large;
	}

	public void setAvatar_large(String avatar_large) {
		this.avatar_large = avatar_large;
	}

	public int getFollowers_count() {
		return followers_count;
	}

	public void setFollowers_count(int followers_count) {
		this.followers_count = followers_count;
	}

	public int getFriends_count() {
		return friends_count;
	}

	public void setFriends_count(int friends_count) {
		this.friends_count = friends_count;
	}

	public int getStatuses_count() {
		return statuses_count;
	}

	public void setStatuses_count(int statuses_count) {
		this.statuses_count = statuses_count;
	}

	public int getFavourites_count() {
		return favourites_count;
	}

	public void setFavourites_count(int favourites_count) {
		this.favourites_count = favourites_count;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", screen_name=" + screen_name + ", name="
				+ name + ", location=" + location + ", profile_image_url="
				+ profile_image_url + ", gender=" + gender + ", avatar_large="
				+ avatar_large + ", followers_count=" + followers_count
				+ ", friends_count=" + friends_count + ", statuses_count="
				+ statuses_count + ", favourites_count=" + favourites_count
				+ "]";
	}
	
	

}

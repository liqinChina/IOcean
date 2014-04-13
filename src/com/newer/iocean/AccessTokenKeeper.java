package com.newer.iocean;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.Preference;

/**
 * 该类定义了微博授权时所需要的参数
 * 
 * @author Administrator
 * 
 */
public class AccessTokenKeeper {

	public static final String FREFERENCES_NAME = "com_weibo_sdk_android";

	private static final String KEY_UID = "uid";
	private static final String KEY_ACCESS_TOKEN = "access_token";
	private static final String KEY_EXPIRES_IN = "expires_in";

	private static final String KEY_STATUS_RESPONSE = "key_status_response";

	private static final String KEY_TOME_COMMENTS_RESPONSE = "key_tome_comments_response";

	private static final String KEY_BYME_COMMENTS_RESPONSE = "key_byme_comments_response";

	/**
	 * 保存access_token 到SharedPreferences中
	 * 
	 * 
	 * @param context
	 * @param token
	 */
	public static void writeAccessToken(Context context, Oauth2AccessToken token) {

		if (context == null || token == null) {
			return;
		}

		SharedPreferences preferences = context.getSharedPreferences(
				FREFERENCES_NAME, Context.MODE_APPEND);
		Editor editor = preferences.edit();
		editor.putString(KEY_UID, token.getUid());
		editor.putString(KEY_ACCESS_TOKEN, token.getToken());
		editor.putLong(KEY_EXPIRES_IN, token.getExpiresTime());
		editor.commit();

	}

	public static void writeStatusResponse(Context context, String response) {

		SharedPreferences preferences = context.getSharedPreferences(
				FREFERENCES_NAME, Context.MODE_APPEND);
		Editor editor = preferences.edit();
		editor.putString(KEY_STATUS_RESPONSE, response);

		editor.commit();

	}

	public static void writeTomeCommentsResponse(Context context,
			String response) {

		SharedPreferences preferences = context.getSharedPreferences(
				FREFERENCES_NAME, Context.MODE_APPEND);
		Editor editor = preferences.edit();
		editor.putString(KEY_TOME_COMMENTS_RESPONSE, response);

		editor.commit();

	}
	
	public static void writeBymeCommentsResponse(Context context,
			String response) {

		SharedPreferences preferences = context.getSharedPreferences(
				FREFERENCES_NAME, Context.MODE_APPEND);
		Editor editor = preferences.edit();
		editor.putString(KEY_BYME_COMMENTS_RESPONSE, response);

		editor.commit();

	}

	/**
	 * 从SharedPreferences中获取access_token
	 * 
	 * 
	 * @param context
	 * @return Token对象
	 */
	public static Oauth2AccessToken readAccessToken(Context context) {

		if (context == null) {
			return null;
		}
		Oauth2AccessToken token = new Oauth2AccessToken();

		SharedPreferences preferences = context.getSharedPreferences(
				FREFERENCES_NAME, Context.MODE_APPEND);
		token.setUid(preferences.getString(KEY_UID, ""));
		token.setToken(preferences.getString(KEY_ACCESS_TOKEN, ""));
		token.setExpiresTime(preferences.getLong(KEY_EXPIRES_IN, 0));

		return token;

	}

	public static String readStatusResponse(Context context) {

		SharedPreferences preferences = context.getSharedPreferences(
				FREFERENCES_NAME, Context.MODE_APPEND);

		return preferences.getString(KEY_STATUS_RESPONSE, "");

	}

	public static String readTomeCommentsResponse(Context context) {

		SharedPreferences preferences = context.getSharedPreferences(
				FREFERENCES_NAME, Context.MODE_APPEND);

		return preferences.getString(KEY_TOME_COMMENTS_RESPONSE, "");

	}
	
	public static String readBymeCommentsResponse(Context context) {

		SharedPreferences preferences = context.getSharedPreferences(
				FREFERENCES_NAME, Context.MODE_APPEND);

		return preferences.getString(KEY_BYME_COMMENTS_RESPONSE, "");

	}

	/**
	 * 清空SharedPreferences中的Token中的信息
	 * 
	 * 
	 * @param context
	 */
	public static void clear(Context context) {
		if (context == null) {
			return;
		}

		SharedPreferences preferences = context.getSharedPreferences(
				FREFERENCES_NAME, Context.MODE_APPEND);
		Editor editor = preferences.edit();
		editor.clear();

		editor.commit();
	}

}

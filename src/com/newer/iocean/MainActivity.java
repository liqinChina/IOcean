package com.newer.iocean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.newer.iocean.openApi.StatusAPIActivity;
import com.newer.iocean.user.User;
import com.newer.iocean.user.UserParser;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.LogoutAPI;
import com.sina.weibo.sdk.openapi.models.Status;
import com.sina.weibo.sdk.openapi.models.StatusList;
import com.squareup.picasso.Picasso;

public class MainActivity extends ActionBarActivity  {

	private DrawerLayout drawerLayout;
	private ImageView imageViewOfDrawer;
	private TextView textViewOfDrawer;
	private ListView listViewOfDrawer;
	private ActionBarDrawerToggle drawerToggle;

	private ActionBar actionBar;

	private ArrayList<HashMap<String, Object>> dataOfList;
	private SimpleAdapter adapterOfList;

	private CharSequence drawerTitle;
	private CharSequence mTitle;

	private String[] from = { "imageName", "textName" };
	private int[] to = { R.id.imageView_tome, R.id.textView_following_num };

	private Oauth2AccessToken accessToken;
	
	 MainFragment mainFragment;
	User muser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTitle = drawerTitle = getTitle();
		
		

		actionBar = getActionBar();

		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		imageViewOfDrawer = (ImageView) findViewById(R.id.imageView_drawer);
		textViewOfDrawer = (TextView) findViewById(R.id.textView_drawer);
		listViewOfDrawer = (ListView) findViewById(R.id.listView_drawer);

		dataOfList = new ArrayList<HashMap<String, Object>>();
		loadDrawerListViewData();
		adapterOfList = new SimpleAdapter(this, dataOfList,
				R.layout.list_item_drawer_left, from, to);
		listViewOfDrawer.setAdapter(adapterOfList);

		// 设置阴影
		drawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);

		drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			@Override
			public void onDrawerOpened(View drawerView) {

				super.onDrawerOpened(drawerView);
				actionBar.setTitle(R.string.drawer_open);
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerClosed(drawerView);

				actionBar.setTitle(R.string.app_name);
			}

		};

		drawerLayout.setDrawerListener(drawerToggle);

		listViewOfDrawer.setOnItemClickListener(new DrawerItemClickListener());
		
		muser = new User();

		 getUserData();
		 
		 
		 
		 
		 textViewOfDrawer.setText(muser.getScreen_name());
		 
		 
		 Oauth2AccessToken accessToken = AccessTokenKeeper.readAccessToken(this);
			
			StatusAPIActivity statusAPIActivity = new StatusAPIActivity(this, accessToken);						
			statusAPIActivity.setFriendsTimeLine();

	}

	private void loadDrawerListViewData() {
		
		
		
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("imageName", R.drawable.ic_action_favorite);
		map.put("textName", "首页");

		HashMap<String, Object> map2 = new HashMap<String, Object>();
		map2.put("imageName", R.drawable.ic_action_favorite);
		map2.put("textName", "提及");

		HashMap<String, Object> map3 = new HashMap<String, Object>();
		map3.put("imageName", R.drawable.ic_action_favorite);
		map3.put("textName", "评论");
		
		HashMap<String, Object> map4 = new HashMap<String, Object>();
		map4.put("imageName", R.drawable.ic_action_favorite);
		map4.put("textName", "收藏");
		
		HashMap<String, Object> map5 = new HashMap<String, Object>();
		map5.put("imageName", R.drawable.ic_action_favorite);
		map5.put("textName", "个人资料");
		
		HashMap<String, Object> map6= new HashMap<String, Object>();
		map6.put("imageName", R.drawable.ic_action_favorite);
		map6.put("textName", "注销");
		
		HashMap<String, Object> map7= new HashMap<String, Object>();
		map7.put("imageName", R.drawable.ic_action_favorite);
		map7.put("textName", "设置");

		dataOfList.add(map);
		dataOfList.add(map2);
		dataOfList.add(map3);
		dataOfList.add(map4);
		dataOfList.add(map5);
		dataOfList.add(map6);
		dataOfList.add(map7);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(title);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
		drawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawerToggle.onConfigurationChanged(newConfig);
	}

	class DrawerItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {

			selectItem(position);

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.main, menu);
		
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		// 关闭或打开抽屉
		if (drawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		
		switch (item.getItemId()) {
		case R.id.action_post:
			Intent intent = new Intent(getApplicationContext(), PostStatusActivity.class);
			startActivity(intent);
			
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	private void getUserData() {
		accessToken = AccessTokenKeeper.readAccessToken(this);
		String uid = accessToken.getUid();
		String access_token = accessToken.getToken();

		getUserInfoTask task = new getUserInfoTask();
		String url = "https://api.weibo.com/2/users/show.json?uid=" + uid
				+ "&access_token=" + access_token;
		task.execute(url);
	}

	public void selectItem(int position) {

		if (position == 0) {

			 mainFragment = new MainFragment();
			Bundle bundle = new Bundle();
			bundle.putInt(MainFragment.LIST_NUMBER, position);
			mainFragment.setArguments(bundle);

			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, mainFragment).commit();

			listViewOfDrawer.setItemChecked(position, true);
			drawerLayout.closeDrawer(GravityCompat.START);
			
			
		}
		
		if (position == 2) {

			Intent intent = new Intent(getApplicationContext(), CommentActivity.class);
			startActivity(intent);
			
			
			listViewOfDrawer.setItemChecked(position, true);
			drawerLayout.closeDrawer(GravityCompat.START);
			
			
		}
		
		if (position == 4) {

			 SelfInfoFragment selfInfoFragment = new SelfInfoFragment();
			Bundle bundle = new Bundle();
			bundle.putInt(SelfInfoFragment.LIST_NUMBER, position);
			selfInfoFragment.setArguments(bundle);

			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, selfInfoFragment).commit();

			listViewOfDrawer.setItemChecked(position, true);
			drawerLayout.closeDrawer(GravityCompat.START);
			
			
		}
		
		if (position == 5) {

			 LogoutFragment logoutFragment = new LogoutFragment();
			Bundle bundle = new Bundle();
			bundle.putInt(LogoutFragment.LIST_NUMBER, position);
			logoutFragment.setArguments(bundle);

			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, logoutFragment).commit();

			listViewOfDrawer.setItemChecked(position, true);
			drawerLayout.closeDrawer(GravityCompat.START);
			
			
		}

	}

	
	
	public static class MainFragment extends Fragment {

		public static final String LIST_NUMBER = "list_number";
		private static final String TAG = "MainFragment";
		static User user;
		
		private ListView listViewStatuses;
		private StatusAdapter statusAdapter;
		public static ArrayList<Status> dataStstuses;
		
		
		public MainFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			View view = inflater.inflate(R.layout.activity_main_fragment,
					container, false);
			
			listViewStatuses = (ListView) view.findViewById(R.id.listView_statuses);
			
			/*Oauth2AccessToken accessToken = AccessTokenKeeper.readAccessToken(getActivity());
			Log.d(TAG, accessToken.toString());
			StatusAPIActivity statusAPIActivity = new StatusAPIActivity(getActivity(), accessToken);
			
			statusAPIActivity.setFriendsTimeLine();
			
			
			
			StatusesDao dao = new StatusesDao(getActivity());
			cursor = dao.findAll();
			cursorAdapter = new SimpleCursorAdapter(getActivity(), R.layout.list_item_statuses_listview, cursor, statfrom, statto);
			
			listViewStatuses.setAdapter(cursorAdapter);*/
			
			/*int i=0;
			while(cursor.moveToNext()) {
				
					String url = cursor.getString(0);
					Bitmap bitmap = dao.getHttpBitmap(url);
					
					View oneview  = listViewStatuses.getChildAt(i);
					ImageView imageView = (ImageView) oneview.findViewById(R.id.imageView_status);
					
					imageView.setImageBitmap(bitmap);
			
				
			}
			
			cursorAdapter.changeCursor(cursor);*/
			
			
			
			
			
			
			
			
			dataStstuses = new ArrayList<Status>();
			
			String response = AccessTokenKeeper.readStatusResponse(getActivity());
			if (response.startsWith("{\"statuses\"")) {
				// 调用 StatusList#parse 解析字符串成微博列表对象
				StatusList statuses = StatusList.parse(response);
				if (statuses != null && statuses.total_number > 0) {
					Toast.makeText(getActivity(),
							"获取微博信息流成功, 条数: " + statuses.statusList.size(),
							Toast.LENGTH_LONG).show();

					dataStstuses = statuses.statusList;
				
					Toast.makeText(getActivity(),
							"friends: " + dataStstuses.toString(),
							Toast.LENGTH_LONG).show();
					
				}
			}
			
		
			
			statusAdapter = new StatusAdapter(getActivity(), dataStstuses);
			listViewStatuses.setAdapter(statusAdapter);
			
			listViewStatuses.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					
					Status status = dataStstuses.get(position);
					
					Intent intent = new Intent(getActivity(), BigStatusActivity.class);
					Bundle bundle = new Bundle();
					
					bundle.putString("image", status.user.profile_image_url);
					bundle.putString("name", status.user.name);
					bundle.putString("time", status.created_at);
					bundle.putString("text", status.text);
					bundle.putString("imageBig", status.user.profile_image_url);			
					bundle.putInt("repost", status.reposts_count);
					bundle.putInt("comment", status.comments_count);
					
					bundle.putString("imageBigBig", status.original_pic);
					
					intent.putExtras(bundle);
					
					startActivity(intent);
					
					
				}
				
				
			});
			
			
			
			SharedPreferences preferences = getActivity().getSharedPreferences(AccessTokenKeeper.FREFERENCES_NAME, Context.MODE_APPEND);	
			 user = new User();
			
			user.setUid(preferences.getString(User.UID, ""));
			user.setScreen_name(preferences.getString(User.SCREEN_NAME, ""));
			user.setProfile_image_url(preferences.getString(User.PROFILE_IMAGE_URL, ""));
			user.setName(preferences.getString(User.NAME, ""));
			user.setLocation(preferences.getString(User.LOCATION, ""));
			user.setGender(preferences.getString(User.GENDER, ""));
			user.setAvatar_large(preferences.getString(User.AVATAR_LARGE, ""));
			
			Toast.makeText(getActivity(), user.toString(), Toast.LENGTH_LONG).show();
			
			
			return view;
		}

	}

	public static class SelfInfoFragment extends Fragment {
		public static final String LIST_NUMBER = "list_number";
		
		public SelfInfoFragment() {
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.activity_selfinfo, container, false);
			
			ImageView imageView = (ImageView) view.findViewById(R.id.imageView_self_info);
			TextView textViewScreenName = (TextView) view.findViewById(R.id.textView_screen_name);
			TextView textViewLocation = (TextView) view.findViewById(R.id.textView_self_location);
			TextView textViewStatus = (TextView) view.findViewById(R.id.textView_self_status_count);
			TextView textViewFriend = (TextView) view.findViewById(R.id.textView_self_friends_count);
			TextView textViewFollowing = (TextView) view.findViewById(R.id.textView_self_following_count);
			TextView textViewTopic = (TextView) view.findViewById(R.id.textView_self_topic_count);
			
			SharedPreferences preferences = getActivity().getSharedPreferences(AccessTokenKeeper.FREFERENCES_NAME, Context.MODE_APPEND);
			
			
			String url = preferences.getString(User.PROFILE_IMAGE_URL, "");
			Picasso.with(getActivity()).load(url).into(imageView);
			
			
			textViewScreenName.setText(preferences.getString(User.SCREEN_NAME, ""));
			textViewLocation.setText(preferences.getString(User.LOCATION, ""));
			
			textViewStatus.setText(String.valueOf(preferences.getInt(User.STATUSES_COUNT, 0)));
			textViewFriend.setText(String.valueOf(preferences.getInt(User.FRIENDS_COUNT, 0)));
			textViewFollowing.setText(String.valueOf(preferences.getInt(User.FOLLOWERS_COUNT, 0)));
			textViewTopic.setText("topic");
			return view;
		}
		
		
		
	}
	
	public static class LogoutFragment extends Fragment {
		
		public static final String LIST_NUMBER = "list_number";
		private static final String TAG = "LogoutFragment";
		TextView textViewCurrentToken;
		Button button;
		Oauth2AccessToken accessToken;
		LogOutRequestListener logOutRequestListener = new LogOutRequestListener();
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_logout, container, false);
		
		textViewCurrentToken = (TextView) view.findViewById(R.id.current_token_info);
		button = (Button) view.findViewById(R.id.button_logout);
		accessToken = AccessTokenKeeper.readAccessToken(getActivity());
		if (accessToken != null && accessToken.isSessionValid()) {
			
			String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date(accessToken.getExpiresTime()));
            String format = getString(R.string.weibosdk_demo_token_to_string_format_1);
            textViewCurrentToken.setText(String.format(format, accessToken.getToken(), date));
		}
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {

				if (accessToken != null && accessToken.isSessionValid()) {
                    new LogoutAPI(accessToken).logout(logOutRequestListener);
                } else {
                	textViewCurrentToken.setText(R.string.weibosdk_demo_logout_failed_1);
                }
			}
		});
		
		
		return view;
		
		
		}
		
		 class LogOutRequestListener implements RequestListener {
	        @Override
	        public void onComplete(String response) {
	            if (!TextUtils.isEmpty(response)) {
	                try {
	                    JSONObject obj = new JSONObject(response);
	                    String value = obj.getString("result");
	                    
	                    if ("true".equalsIgnoreCase(value)) {
	                        AccessTokenKeeper.clear(getActivity());

	                        textViewCurrentToken.setText("已注销");
	                        accessToken = null;
	                    }
	                } catch (JSONException e) {
	                    e.printStackTrace();
	                }
	            }
	        } 

	        @Override
	        public void onWeiboException(WeiboException e) {
	        	textViewCurrentToken.setText("注销失败");
	        }
	    }
	}
	
	
	public class getUserInfoTask extends AsyncTask<String, Void, User> {

		private static final String TAG = "getUserInfoTask";
		private String result = "";

		public getUserInfoTask() {
			super();

		}

		@Override
		protected User doInBackground(String... params) {
			boolean isGetInfo = false;

			try {
				URL url = new URL(params[0]);
				HttpURLConnection connection = (HttpURLConnection) url
						.openConnection();
				connection.setConnectTimeout(3000);
				connection.setReadTimeout(3000);
				connection.setRequestMethod("GET");
				connection.connect();

				int code = connection.getResponseCode();

				if (code == HttpURLConnection.HTTP_OK) {

					isGetInfo = true;

					InputStream inputStream = connection.getInputStream();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(inputStream, "utf-8"));

					char[] buffer = new char[1024];
					StringBuilder builder = new StringBuilder();

					int size;

					while (-1 != (size = reader.read(buffer))) {
						builder.append(buffer, 0, size);

					}

					result = builder.toString();

				}

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (isGetInfo) {

				UserParser userParser = new UserParser();
				User user = userParser.parserUserInfo(result);
				Log.d(TAG, user.toString());
				return user;

			} else {
				return null;
			}

		}

		@Override
		protected void onPostExecute(User user) {
			// TODO Auto-generated method stub
			super.onPostExecute(user);
			
			muser = user;
			Log.d(TAG, "muser" + muser.toString());
			
			SharedPreferences preferences = getSharedPreferences(AccessTokenKeeper.FREFERENCES_NAME, Context.MODE_APPEND);
			Editor editor = preferences.edit();
			
			editor.putString(User.UID, user.getUid());
			editor.putString(User.SCREEN_NAME, user.getScreen_name());
			editor.putString(User.PROFILE_IMAGE_URL, user.getProfile_image_url());
			editor.putString(User.NAME, user.getName());
			editor.putString(User.LOCATION, user.getLocation());
			editor.putString(User.GENDER, user.getGender());
			editor.putString(User.AVATAR_LARGE, user.getAvatar_large());
			editor.putInt(User.FOLLOWERS_COUNT, user.getFollowers_count());
			editor.putInt(User.FRIENDS_COUNT, user.getFriends_count());
			editor.putInt(User.STATUSES_COUNT, user.getStatuses_count());
			editor.putInt(User.FAVOURITES_COUNT, user.getFavourites_count());
			
			editor.commit();

			Log.d(TAG, user.toString());
			
			
			String url = user.getProfile_image_url();
			Picasso.with(getApplicationContext()).load(url).into(imageViewOfDrawer);
		}

	}

}

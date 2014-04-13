package com.newer.iocean;

import java.util.ArrayList;

import com.newer.iocean.openApi.CommentApi;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.openapi.models.Comment;
import com.sina.weibo.sdk.openapi.models.CommentList;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class CommentActivity extends ActionBarActivity implements TabListener {

	private ActionBar actionBar;
	private LeftFragment leftFragment;
	private RightFragment rightFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_comment);

		actionBar = getActionBar();
		leftFragment = new LeftFragment();
		rightFragment = new RightFragment();

		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		Tab tabLeft = actionBar.newTab().setText("收到的评论").setTabListener(this);
		actionBar.addTab(tabLeft);

		Tab tabRight = actionBar.newTab().setText("发出的评论").setTabListener(this);
		actionBar.addTab(tabRight);

		Oauth2AccessToken accessToken = AccessTokenKeeper.readAccessToken(this);
		CommentApi commentApi = new CommentApi(accessToken, this);
		commentApi.Tome();
		commentApi.Byme();
	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// 再次选中

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		switch (tab.getPosition()) {
		case 0:

			ft.add(android.R.id.content, leftFragment);
			break;

		case 1:
			ft.add(android.R.id.content, rightFragment);
			break;
		default:
			break;
		}

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		switch (tab.getPosition()) {
		case 0:

			ft.remove(leftFragment);
			break;

		case 1:
			ft.remove(rightFragment);
			break;
		default:
			break;
		}

	}

	@SuppressLint("ValidFragment")
	static class LeftFragment extends Fragment {

		private ListView listViewTome;
		private ArrayList<Comment> dataSet;
		private TomeCommentAdapter commentAdapter;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			View view = inflater.inflate(R.layout.activity_comment_left,
					container, false);

			listViewTome = (ListView) view.findViewById(R.id.listView_tome);
			dataSet = new ArrayList<Comment>();

			String response = AccessTokenKeeper
					.readTomeCommentsResponse(getActivity());

			CommentList comments = CommentList.parse(response);
			if (comments != null && comments.total_number > 0) {

				dataSet = comments.commentList;
				Toast.makeText(getActivity(),
						"获取评论成功, 条数: " + comments.commentList.size(),
						Toast.LENGTH_LONG).show();

			}

			commentAdapter = new TomeCommentAdapter(getActivity(), dataSet);

			listViewTome.setAdapter(commentAdapter);

			return view;
		}

	}

	@SuppressLint("ValidFragment")
	static class RightFragment extends Fragment {

		private ListView listViewByme;
		private ArrayList<Comment> dataSetByme;
		private BymeCommentAdapter commentAdapterByme;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			View view = inflater.inflate(R.layout.activity_comment_right,
					container, false);
			listViewByme = (ListView) view.findViewById(R.id.listView_byme);
			dataSetByme = new ArrayList<Comment>();
			
			
			String responseByme = AccessTokenKeeper
					.readBymeCommentsResponse(getActivity());

			CommentList comments = CommentList.parse(responseByme);
			if (comments != null && comments.total_number > 0) {

				dataSetByme = comments.commentList;
				Toast.makeText(getActivity(),
						"获取评论成功, 条数: " + comments.commentList.size(),
						Toast.LENGTH_LONG).show();

			}
			
			commentAdapterByme = new BymeCommentAdapter(getActivity(), dataSetByme);
			
			
			listViewByme.setAdapter(commentAdapterByme);

			return view;
		}

	}

}

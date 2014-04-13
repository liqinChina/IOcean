package com.newer.iocean.openApi;

import java.util.ArrayList;

import com.newer.iocean.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.StatusesAPI;
import com.sina.weibo.sdk.openapi.models.ErrorInfo;
import com.sina.weibo.sdk.openapi.models.Status;
import com.sina.weibo.sdk.openapi.models.StatusList;
import com.sina.weibo.sdk.utils.LogUtil;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

public class StatusAPIActivity {

	protected static final String TAG = "StatusAPIActivity";

	private Context context;

	private Oauth2AccessToken accessToken;

	private StatusesAPI statusesAPI;

	public ArrayList<Status> dataStatusList;

	public StatusAPIActivity() {
	}

	public StatusAPIActivity(Context context, Oauth2AccessToken accessToken) {

		this.context = context;
		this.accessToken = accessToken;
		dataStatusList = new ArrayList<Status>();
	}

	

	public void setFriendsTimeLine() {

		statusesAPI = new StatusesAPI(accessToken);

		statusesAPI.friendsTimeline(0L, 0L, 10, 1, false, 0, false, mListener);

		

	}
	
	public void postStatus(String content) {

		statusesAPI = new StatusesAPI(accessToken);

		statusesAPI.update(content, null, null, mListener);

		

	}

	private RequestListener mListener = new RequestListener() {
		@Override
		public void onComplete(String response) {
			if (!TextUtils.isEmpty(response)) {
				LogUtil.i(TAG, response);
				AccessTokenKeeper.writeStatusResponse(context, response);
				if (response.startsWith("{\"statuses\"")) {
					// 调用 StatusList#parse 解析字符串成微博列表对象
					StatusList statuses = StatusList.parse(response);
					if (statuses != null && statuses.total_number > 0) {
						Toast.makeText(context,
								"获取微博信息流成功, 条数: " + statuses.statusList.size(),
								Toast.LENGTH_LONG).show();

						dataStatusList = statuses.statusList;
						Toast.makeText(context,
								"friends: " + dataStatusList.toString(),
								Toast.LENGTH_LONG).show();
						LogUtil.i(TAG, dataStatusList.toString());
						
						

					}
				} else if (response.startsWith("{\"created_at\"")) {
					// 调用 Status#parse 解析字符串成微博对象
					Status status = Status.parse(response);
					Toast.makeText(context, "发送一送微博成功, id = " + status.id,
							Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(context, response, Toast.LENGTH_LONG).show();
				}
			}
		}

		@Override
		public void onWeiboException(WeiboException e) {
			// LogUtil.e(TAG, e.getMessage());
			ErrorInfo info = ErrorInfo.parse(e.getMessage());
			Toast.makeText(context, info.toString(), Toast.LENGTH_LONG).show();
		}
	};

	

}

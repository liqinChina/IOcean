package com.newer.iocean;

import com.newer.iocean.openApi.StatusAPIActivity;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PostStatusActivity extends Activity {

	private EditText editText;
	private Oauth2AccessToken accessToken;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_status);
		
		editText = (EditText) findViewById(R.id.editText_post_status);
		accessToken = AccessTokenKeeper.readAccessToken(this);
		
	}
	
	public void doPost(View view) {
		
		StatusAPIActivity statusAPIActivity = new StatusAPIActivity(this, accessToken);
		String content  = editText.getText().toString();
		
		statusAPIActivity.postStatus(content);
		
	}

}

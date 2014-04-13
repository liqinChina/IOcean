package com.newer.iocean;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HasAuthActivity extends Activity {

	private Oauth2AccessToken accessToken;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_hasauth);
		
		accessToken = AccessTokenKeeper.readAccessToken(getApplicationContext());
		

	}

	public void doClick(View v) {

		switch (v.getId()) {
		case R.id.button1:
			if (accessToken.isSessionValid()) {
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(intent);
			}
				if (accessToken == null) {
					Intent intent = new Intent(getApplicationContext(), AuthActivity.class);
				startActivity(intent);
				}
				
			
			
			
			

			break;

		case R.id.button2:
			
			Intent intent2 = new Intent(getApplicationContext(), AuthActivity.class);
			startActivity(intent2);

			break;

		default:
			break;
		}
	}

}

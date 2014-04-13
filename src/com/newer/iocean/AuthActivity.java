package com.newer.iocean;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuth;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.exception.WeiboException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class AuthActivity extends Activity{
	
	private static final String TAG = "AuthActivity";
	
	WeiboAuth mWeiboAuth;
	
	
	private Oauth2AccessToken mAccessToken;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//取消标题
				this.requestWindowFeature(Window.FEATURE_NO_TITLE);
				
				this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
				
		
		setContentView(R.layout.activity_auth);
		
		
		 mWeiboAuth = new WeiboAuth(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
		
		
		
		
		
	}
	
	public void doAuth(View v) {
		
		mWeiboAuth.anthorize(new AuthListener());
		
		mAccessToken = AccessTokenKeeper.readAccessToken(AuthActivity.this);
		
		/*if (mAccessToken.isSessionValid()) {
			 Log.d(TAG,  "isSessionValid  " + mAccessToken.toString());
			
			Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
		}*/
		
		
		
	}
	
	
	
	class AuthListener implements WeiboAuthListener {
	    
	     

		

		@Override
	    public void onComplete(Bundle values) {
	        // 从 Bundle 中解析 Token
	         mAccessToken = Oauth2AccessToken.parseAccessToken(values);
	        if (mAccessToken.isSessionValid()) {
	            // 保存 Token 到 SharedPreferences
	            AccessTokenKeeper.writeAccessToken(AuthActivity.this, mAccessToken);
	          
	            Log.d(TAG, mAccessToken.toString());
	            
	            Toast.makeText(getApplicationContext(), "授权成功", Toast.LENGTH_LONG).show();
	       
	        
	        
	        
	        } else {
	        // 当您注册的应用程序签名不正确时，就会收到 Code，请确保签名正确
	        	
	            String code = values.getString("code", "");
	            String message = "授权失败";
	            
	            if (!TextUtils.isEmpty(code)) {
					message = message + "\nObtained the code: " + code ;
				}
	            
	            Log.d(TAG, message);
	            Toast.makeText(AuthActivity.this, message, Toast.LENGTH_LONG).show();
	           
	        }
	    }

	    @Override
	    public void onCancel() {
	    	
	    	Toast.makeText(AuthActivity.this, "取消授权", Toast.LENGTH_LONG).show();
	    	
	    	  Log.d(TAG, "取消授权");
	    	
	    }

	    @Override
	    public void onWeiboException(WeiboException e) {
	    	
	    	Toast.makeText(AuthActivity.this, "Auth exception :" + e.getMessage(), Toast.LENGTH_LONG).show();
	    	 Log.d(TAG, "Auth exception :" + e.getMessage());
	    
	    }
	}
	
	
	
	

}

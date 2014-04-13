package com.newer.iocean.openApi;

import android.content.Context;
import android.text.TextUtils;

import com.newer.iocean.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.CommentsAPI;
import com.sina.weibo.sdk.utils.LogUtil;

public class CommentApi {
	
	protected static final String TAG = "CommentApi";
	private Oauth2AccessToken accessToken;
	private CommentsAPI commentsAPI;
	private Context context;
	
	public CommentApi() {
	}

	public CommentApi(Oauth2AccessToken accessToken, Context context) {
		super();
		this.accessToken = accessToken;
		this.context = context;
	}
	
	public void Tome() {
		commentsAPI = new CommentsAPI(accessToken);
		
		commentsAPI.toME(0L, 0L, 10, 1, CommentsAPI.AUTHOR_FILTER_ALL, CommentsAPI.SRC_FILTER_ALL, mListener);
        
		
		
	}
	
	public void Byme() {
		commentsAPI = new CommentsAPI(accessToken);
		
        
		commentsAPI.byME(0L, 0L, 10, 1, CommentsAPI.SRC_FILTER_ALL,bymeListener);
		
	}
	
	
	
	
	private RequestListener mListener = new RequestListener() {
        @Override
        public void onComplete(String response) {
            LogUtil.i(TAG, response);
            if (!TextUtils.isEmpty(response)) {
            	
            	AccessTokenKeeper.writeTomeCommentsResponse(context, response);
                /*CommentList comments = CommentList.parse(response);
                if(comments != null && comments.total_number > 0){
                    Toast.makeText(context,
                            "获取评论成功, 条数: " + comments.commentList.size(), 
                            Toast.LENGTH_LONG).show();
                }*/
            }
        }

        @Override
        public void onWeiboException(WeiboException e) {
            LogUtil.e(TAG, e.getMessage());
        }
    }; 
    
    private RequestListener bymeListener = new RequestListener() {
        @Override
        public void onComplete(String response) {
            LogUtil.i(TAG, response);
            if (!TextUtils.isEmpty(response)) {
            	
            	AccessTokenKeeper.writeBymeCommentsResponse(context, response);
                /*CommentList comments = CommentList.parse(response);
                if(comments != null && comments.total_number > 0){
                    Toast.makeText(context,
                            "获取评论成功, 条数: " + comments.commentList.size(), 
                            Toast.LENGTH_LONG).show();
                }*/
            }
        }

        @Override
        public void onWeiboException(WeiboException e) {
            LogUtil.e(TAG, e.getMessage());
        }
    }; 
	
	

}

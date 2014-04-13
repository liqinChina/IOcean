package com.newer.iocean;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.sina.weibo.sdk.openapi.models.Status;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class StatusesDao {
	
	private SQLiteDatabase db;
	
	public StatusesDao(Context context) {
		
		db = new DbHelper(context).getWritableDatabase();
		
	}
	
	public StatusesDao() {
		// TODO Auto-generated constructor stub
	}
	
	public long save(Status status) {
	
		
		ContentValues values = new ContentValues();
		
		values.put(DbHelper.COL_IMAGE, status.user.profile_image_url);
		values.put(DbHelper.COL_NAME, status.user.name);
		values.put(DbHelper.COL_TIME, status.created_at);
		values.put(DbHelper.COL_TEXT, status.text);
		
		long id = db.insert(DbHelper.TABLE_NAME, null, values);
		
		db.close();
		
		return id;
	}
	
	public Cursor findAll() {
		
	
		return db.query(DbHelper.TABLE_NAME, DbHelper.ALL_COLS, null, null, null, null, null);
		
	}
	
	public void delete() {
		
		db.delete(DbHelper.TABLE_NAME, null, null);
		
	}
	
	
	  public  Bitmap getHttpBitmap(String url){
	        URL myFileURL;
	        Bitmap bitmap=null;
	        try{
	            myFileURL = new URL(url);
	            //获得连接
	            HttpURLConnection conn=(HttpURLConnection)myFileURL.openConnection();
	            //设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制
	            conn.setConnectTimeout(6000);
	            //连接设置获得数据流
	            conn.setDoInput(true);
	            //不使用缓存
	            conn.setUseCaches(false);
	            //这句可有可无，没有影响
	            //conn.connect();
	            //得到数据流
	            InputStream is = conn.getInputStream();
	            //解析得到图片
	            bitmap = BitmapFactory.decodeStream(is);
	            //关闭数据流
	            is.close();
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	         
	        return bitmap;
	         
	    }
	
	

}

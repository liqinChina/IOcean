package com.newer.iocean;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

	private static final String DB_NAME = "mydb.db";
	private static final int DB_VWESION = 1;
	
	public static final String TABLE_NAME = "statuses";
	
	public static final String COL_ID = "_id";
	public static final String COL_IMAGE = "image";
	public static final String COL_NAME = "name";
	public static final String COL_TIME = "time";
	public static final String COL_TEXT = "text";
	public static final String[] ALL_COLS = {COL_ID,COL_IMAGE,COL_NAME,COL_TIME,COL_TEXT};
	
	
	private static final String CREATE_TABLE =  "create table " + TABLE_NAME
			 + " (" +  COL_ID +
			" integer primary key autoincrement, " +
			COL_IMAGE + " text, " +
			COL_NAME + " text," +
			COL_TIME + " text," +
			COL_TEXT + " text )";
			
	
	
	private static final String DROP_TABLE = "drop table if exists " ;

	public DbHelper(Context context) {
		super(context, DB_NAME, null, DB_VWESION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		db.execSQL(DROP_TABLE);
		onCreate(db);
	}

}

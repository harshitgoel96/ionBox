package com.harshit.ionBox.DB;

import org.apache.cordova.LOG;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CacheDBHelper extends SQLiteOpenHelper{
	public static final String TABLE_CAHCE="cache_master";
	public static final String Key="key";
	public static final String Value="value";
	
	private static final String DB_Name="Cache.db";
	private static final int DB_Version=1;
	
	private static final String DB_Create="create table "
	+TABLE_CAHCE+"( id integer primary key autoincrement,"
	+Key+" text not null unique,"
	+Value+" text not null);";
	public CacheDBHelper (Context context){
		super(context,DB_Name,null,DB_Version);
	}
	@Override public void onCreate(SQLiteDatabase database){
		LOG.e("Create the db",DB_Create);
		database.execSQL(DB_Create);
		LOG.e("Create the db","Success");
	}
	@Override public void onUpgrade(SQLiteDatabase db, int oldV,int newV){
		db.execSQL("Drop table if exists "+TABLE_CAHCE);
	}
}

 
 
 
package com.harshit.ionBox.DB;

import org.apache.cordova.LOG;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;

public class CacheSystem {
	private SQLiteDatabase db;
	private SQLiteOpenHelper dbHelper;
	private String[] allColumns = { CacheDBHelper.Key, CacheDBHelper.Value };
	private static CacheSystem obj;

	private CacheSystem(Context context) {
		this.dbHelper = new CacheDBHelper(context);
		open();
	}

	public static CacheSystem getObject(Context context) {
		if (obj == null) {
			obj = new CacheSystem(context);
		}
		return obj;
	}

	public void open() throws SQLException {
		
		this.db = this.dbHelper.getWritableDatabase();
		
	}

	public void close() {
		this.dbHelper.close();
	}

	public void clearCache() {
		
		this.db.delete(CacheDBHelper.TABLE_CAHCE, null, null);
		
	}

	public boolean addToCache(String _key, String _value) {
		
		deleteFromCache(_key);

		ContentValues values = new ContentValues();
		values.put(CacheDBHelper.Key, _key);
		values.put(CacheDBHelper.Value, _value);
		long insertID = this.db.insert(CacheDBHelper.TABLE_CAHCE, null, values);
		
		if (insertID != -1) {
			return true;
		}
		return false;

	}

	public void deleteFromCache(String _key) {
		
		this.db.delete(CacheDBHelper.TABLE_CAHCE, CacheDBHelper.Key + " = '" + _key+"'",
				null);
		
	}

	public String getCacheValue(String _key) {
		
		try{
			Cursor cursor = this.db.query(CacheDBHelper.TABLE_CAHCE, this.allColumns,
				CacheDBHelper.Key + " = '" + _key+"'", null, null, null, null, null);
		if (cursor.moveToFirst()) {
			LOG.d("GET CAHCE VALUE","got key");
			if (!cursor.isAfterLast()) {
				return cursor.getString(1);
			}
			close();
			
		}
		LOG.d("GET CAHCE VALUE","got no value");
		return null;}
		catch(Exception E)
		{
			LOG.e("Exception in getCacheValue", E.toString());
			return "";
		}
	}

	public int getCacheCount() {
		
		Cursor cursor = this.db.query(CacheDBHelper.TABLE_CAHCE, this.allColumns, null,
				null, null, null, null);
		
		return cursor.getCount();
	}
}

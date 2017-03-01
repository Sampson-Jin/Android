package com.example.dao;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {

	private  DBHelper dbhelper=null;
	private static final int SUCCESS=1;
	private static UriMatcher uriMatcher =new UriMatcher(UriMatcher.NO_MATCH);
	static{
		//content://com.jin.yong/yang  成功 1 否则-1
		uriMatcher.addURI("com.jin.yong", "yang", SUCCESS);
	}
	@Override
	public boolean onCreate() {
		dbhelper=new DBHelper(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		int result=uriMatcher.match(uri);
		if(result==SUCCESS){
			SQLiteDatabase database=dbhelper.getWritableDatabase();
			return database.query("persioninfo", projection, selection, selectionArgs, null, null, sortOrder);
		}else {
			throw new IllegalArgumentException("uri 未能匹配成功，请检查");
		}
	}

	@Override
	public String getType(Uri uri) {
		
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		
		return 0;
	}

}

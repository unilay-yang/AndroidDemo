package com.coslay.asynchronoutest.lib;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PersistenceUtil {
	private Context context;

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public PersistenceUtil(Context context) {
		this.context = context;
	}

	public void savePersistence(String name, String key, Integer value) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				name, Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	public void savePersistence(String name, String key, String value) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				name, Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public void savePersistence(String name, String key, Boolean value) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				name, Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	public String getPersistenceString(String name, String key) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				name, Context.MODE_PRIVATE);
		String value = sharedPreferences.getString(key, null);
		return value;
	}

	public Boolean getPersistenceBoolean(String name, String key) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				name, Context.MODE_PRIVATE);
		Boolean value = sharedPreferences.getBoolean(key, false);
		return value;
	}

	public Integer getPersistenceInteger(String name, String key) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				name, Context.MODE_PRIVATE);
		Integer value = sharedPreferences.getInt(key, -1);
		return value;
	}
}

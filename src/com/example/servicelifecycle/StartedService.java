package com.example.servicelifecycle;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class StartedService extends Service{

	private static final String tag = StartedService.class.getSimpleName();
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		Log.d(tag, "onCreate");
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(tag, "onStartCommand startId=" + startId);
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		Log.d(tag, "onDestroy");
		super.onDestroy();
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		Log.d(tag, "onUnbind");
		return super.onUnbind(intent);
	}
	
	
}

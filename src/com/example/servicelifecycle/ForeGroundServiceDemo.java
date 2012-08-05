package com.example.servicelifecycle;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ForeGroundServiceDemo extends Service{

	private static final String tag = ForeGroundServiceDemo.class.getSimpleName();
	
	@Override
	public IBinder onBind(Intent intent) {
		Log.d(tag, "onBind");
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		
		Log.d(tag, "onCreate");
		
		Notification notification = new Notification(R.drawable.ic_launcher, getText(R.string.app_name),
		        System.currentTimeMillis());
		Intent notificationIntent = new Intent(this, MainActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
		notification.setLatestEventInfo(this, "ForeGround Service", "Great! Service is running", pendingIntent);
		startForeground(1, notification);
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Log.d(tag, "onStart startId=" + startId);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(tag, "onStartCommand startId=" + startId);
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		Log.d(tag, "onUnbind");
		return super.onUnbind(intent);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(tag, "onDestroy");
	}
	
	@Override
	public void onRebind(Intent intent) {
		super.onRebind(intent);
	}
}

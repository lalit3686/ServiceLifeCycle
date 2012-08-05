package com.example.servicelifecycle;

import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class BoundService extends Service{

	private final static String tag = BoundService.class.getSimpleName();
	
	@Override
	public IBinder onBind(Intent intent) {
		Log.d(tag, "onBind");
		return new LocalBinder();
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
//		return true;
	}
	
	@Override
	public void onRebind(Intent intent) {
		Log.d(tag, "onRebind");
		super.onRebind(intent);
	}
	
	class LocalBinder extends Binder {
		
		public int getRandomNumber() {
			Random mRandom = new Random();
			return mRandom.nextInt(100);
		}
	}
}

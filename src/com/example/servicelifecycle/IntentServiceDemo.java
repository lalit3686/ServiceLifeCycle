package com.example.servicelifecycle;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class IntentServiceDemo extends IntentService{

	private static final String tag = IntentServiceDemo.class.getSimpleName();

	public IntentServiceDemo() {
		super("IntentServiceDemo");
		Log.d(tag, "IntentServiceDemo");

	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.d(tag, "onHandleIntent");

		long endTime = System.currentTimeMillis() + 10*1000;
		while (System.currentTimeMillis() < endTime) {
			synchronized (this) {
				try {
					wait(endTime - System.currentTimeMillis());
				} catch (Exception e) {
				}
			}
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(tag, "onCreate");
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.d(tag, "onBind");
		return super.onBind(intent);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(tag, "onStartCommand startId=" + startId);

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onRebind(Intent intent) {
		super.onRebind(intent);
		Log.d(tag, "onRebind");
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.d(tag, "onUnBind");
		return super.onUnbind(intent);
	}

	@Override
	public void onDestroy() {
		Log.d(tag, "onDestroy");
		super.onDestroy();
	}
}

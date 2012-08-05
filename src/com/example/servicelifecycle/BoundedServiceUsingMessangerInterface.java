package com.example.servicelifecycle;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

public class BoundedServiceUsingMessangerInterface extends Service{

	private final static String tag = BoundedServiceUsingMessangerInterface.class.getSimpleName();
	public static final int MSG_HELLO = 1;
	private Messenger messenger = new Messenger(new ServiceHandler());
	
	class ServiceHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_HELLO:
				Toast.makeText(BoundedServiceUsingMessangerInterface.this, "Hello", Toast.LENGTH_SHORT).show();
				break;

			default:
				break;
			}
			super.handleMessage(msg);
		}
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		Log.d(tag, "onCreate");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(tag, "onStartCommand startId=" + startId);
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		Log.d(tag, "onBind");
		return messenger.getBinder();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.d(tag, "onUnbinnd");
		
		return super.onUnbind(intent);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(tag, "onDestroy");
	}
}

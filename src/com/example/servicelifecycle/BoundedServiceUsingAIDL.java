package com.example.servicelifecycle;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class BoundedServiceUsingAIDL extends Service{

	private static final String tag = BoundedServiceUsingAIDL.class.getSimpleName();
	private int index = 0;
	
	@Override
	public IBinder onBind(Intent intent) {
		Log.d(tag, "onBind");
		return stub;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(tag, "onCreate");
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
	
	private final AIDLServiceDemoInterface.Stub stub = new AIDLServiceDemoInterface.Stub() {
		
		public String getText() throws RemoteException {
			return "Hello From Service.";
		}
		
		public int getNumber() throws RemoteException {
			return ++index;
		}
	};
}

package com.example.servicelifecycle;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.servicelifecycle.BoundService.LocalBinder;

public class BoundServiceActivity extends Activity implements OnClickListener{
	private static final String tag = BoundServiceActivity.class.getSimpleName();

	private Button btnBind, btnUnbind, btnGetNumber;
	private LocalBinder mServiceBinder;
	private boolean mBound;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bound_service);

		bindComponents();
		addListeners();
	}

	private void bindComponents() {
		btnBind = (Button) findViewById(R.id.btnBind);
		btnUnbind = (Button) findViewById(R.id.btnUnbind);
		btnGetNumber = (Button) findViewById(R.id.btnGetNumber);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
		if(mBound) {
			unbindService(mServiceConnection);
			mBound = false;
			mServiceBinder = null;
		}
	}

	private void addListeners() {
		btnBind.setOnClickListener(this);
		btnUnbind.setOnClickListener(this);
		btnGetNumber.setOnClickListener(this);
	}

	public void onClick(View view) {
		Intent intent = new Intent();
		intent.setClass(this, BoundService.class);

		switch (view.getId()) {
		case R.id.btnBind:
			if(!mBound)
				bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
			break;
		case R.id.btnUnbind:
			if(mBound) {
				unbindService(mServiceConnection);
				mBound = false;
				mServiceBinder = null;
			}

			break;
		case R.id.btnGetNumber:
			if(mBound) {
				int number = mServiceBinder.getRandomNumber();
				Toast.makeText(this, "Number = " + number, Toast.LENGTH_SHORT).show();
			}
			else{
				Toast.makeText(this, "Service is not connected.", Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}
	}

	ServiceConnection mServiceConnection = new ServiceConnection() {

		public void onServiceDisconnected(ComponentName name) {
			mBound = false;
			mServiceBinder = null;
			Log.d(tag, "onServiceDisconnected");
		}

		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.d(tag, "onServiceConnected");
			mServiceBinder = (LocalBinder)service;
			mBound = true;
		}
	};
}

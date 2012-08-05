package com.example.servicelifecycle;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MessangerServiceActivity extends Activity implements OnClickListener{

	private static final String tag = MessangerServiceActivity.class.getSimpleName();
	
	private Button btnBind, btnUnbind, btnShow;
	boolean mBound;
	private Messenger messenger;
	
	ServiceConnection mConnection = new ServiceConnection() {
		
		public void onServiceDisconnected(ComponentName name) {
			Log.d(tag, "onServiceDisconnected");
			mBound = false;
			messenger = null;
		}
		
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.d(tag, "onServiceConnected");
			mBound = true;
			messenger = new Messenger(service);
		}
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.messanger_service);
		
		bindComponents();
		addListeners();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
		if(mBound) {
			unbindService(mConnection);
			mBound = false;
		}
	}
	
	private void bindComponents() {
		btnBind = (Button) findViewById(R.id.btnBind);
		btnUnbind = (Button)findViewById(R.id.btnUnbind);
		btnShow = (Button)findViewById(R.id.btnShowMessage);
	}
	
	private void addListeners() {
		btnBind.setOnClickListener(this);
		btnUnbind.setOnClickListener(this);
		btnShow.setOnClickListener(this);
	}
	
	public void onClick(View view) {
		Intent mIntent = new Intent();
		mIntent.setClass(this, BoundedServiceUsingMessangerInterface.class);
		switch (view.getId()) {
		
		case R.id.btnBind:
			if(!mBound) {
				bindService(mIntent, mConnection, BIND_AUTO_CREATE);
			}
			break;
		case R.id.btnUnbind:
			if(mBound) {
				unbindService(mConnection);
				mBound = false;
			}
			break;
		case R.id.btnShowMessage:
			if(mBound) {
				Message message = Message.obtain();
				message.what = BoundedServiceUsingMessangerInterface.MSG_HELLO;
				try {
					messenger.send(message);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
			break;
		default:
			break;
		}
	}
}

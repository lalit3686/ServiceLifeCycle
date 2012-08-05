package com.example.servicelifecycle;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity implements OnItemClickListener{

	final static String[] ACTIVITIES = {
		"Started Service Lifecycle", 
		"Bounded Service Lifecycle", 
		"Bound Service Using Messenger Interface",
		"Intent Service",
		"ForeGround Service",
		"Bound Service Using AIDL"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}
	
	private void init() {
		ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ACTIVITIES);
		getListView().setAdapter(mAdapter);
		getListView().setOnItemClickListener(this);
	}

	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent mIntent = new Intent();
		switch (position) {
		case 0:
			mIntent.setClass(this, StartedServiceActivity.class);
			startActivity(mIntent);
			break;
		case 1:
			mIntent.setClass(this, BoundServiceActivity.class);
			startActivity(mIntent);
			break;
		case 2:
			mIntent.setClass(this, MessangerServiceActivity.class);
			startActivity(mIntent);
			break;
		case 3:
			mIntent.setClass(this, IntentServiceActivity.class);
			startActivity(mIntent);
			break;
		case 4:
			mIntent.setClass(this, ForeGroundServiceActivity.class);
			startActivity(mIntent);
			break;
		case 5:
			mIntent.setClass(this, AIDLActivity.class);
			startActivity(mIntent);
			break;
		default:
			break;
		}
	}
}

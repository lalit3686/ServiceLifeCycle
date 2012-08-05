package com.example.servicelifecycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartedServiceActivity extends Activity implements OnClickListener {

	private Button btnStartService, btnStopService;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        bindComponents();
        addListener();
        init();
    }

    private void bindComponents() {
    	btnStartService = (Button)findViewById(R.id.btnStartService);
    	btnStopService = (Button)findViewById(R.id.btnStopService);
	}
    
    private void addListener() {
    	btnStartService.setOnClickListener(this);
    	btnStopService.setOnClickListener(this);
	}
    
    private void init() {
	
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btnStartService:
			Intent mIntent = new Intent();
			mIntent.setClass(this, StartedService.class);
			startService(mIntent);
			break;
		case R.id.btnStopService:
			Intent mIntent2 = new Intent();
			mIntent2.setClass(this, StartedService.class);
			stopService(mIntent2);
			break;
		default:
			break;
		}
	}

    
}

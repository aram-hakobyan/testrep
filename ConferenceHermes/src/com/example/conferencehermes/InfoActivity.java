package com.example.conferencehermes;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;

public class InfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_info);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.info, menu);
		return false;
	}

}

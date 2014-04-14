package com.example.conferencehermes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		findViewById(R.id.startButton1).setOnClickListener(this);
		findViewById(R.id.startButton2).setOnClickListener(this);
		findViewById(R.id.startButton3).setOnClickListener(this);
		findViewById(R.id.startButton4).setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return false;
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.startButton1:
			intent = new Intent(MainActivity.this, InfoActivity.class);
			break;
		case R.id.startButton2:
			intent = new Intent(MainActivity.this, NewsActivity.class);
			break;
		case R.id.startButton3:
			intent = new Intent(MainActivity.this, MapActivity.class);
			break;
		case R.id.startButton4:
			intent = new Intent(MainActivity.this, ContactActivity.class);
			break;
		}
		if (intent != null)
			startActivity(intent);
	}
}

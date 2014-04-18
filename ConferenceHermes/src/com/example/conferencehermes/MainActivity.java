package com.example.conferencehermes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

import com.example.conferencehermes.util.Constants;
import com.example.conferencehermes.util.Utilities;

public class MainActivity extends FragmentActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		findViewById(R.id.startButton1).setOnClickListener(this);
		findViewById(R.id.startButton2).setOnClickListener(this);
		findViewById(R.id.startButton3).setOnClickListener(this);
		findViewById(R.id.startButton4).setOnClickListener(this);
		findViewById(R.id.mainInfoBtn).setOnClickListener(this);
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
			intent = new Intent(MainActivity.this, MyFragmentActivity.class);
			intent.putExtra("PAGE_ID", Constants.INFO_FRAGMENT);
			break;
		case R.id.startButton2:
			intent = new Intent(MainActivity.this, MyFragmentActivity.class);
			intent.putExtra("PAGE_ID", Constants.NEWS_FRAGMENT);
			break;
		case R.id.startButton3:
			intent = new Intent(MainActivity.this, MyFragmentActivity.class);
			intent.putExtra("PAGE_ID", Constants.MAP_FRAGMENT);
			break;
		case R.id.startButton4:
			// intent = new Intent(MainActivity.this, ContactActivity.class);
			Utilities.phoneCall(MainActivity.this);
			break;
		case R.id.mainInfoBtn:
			Utilities.showInfoDialog(MainActivity.this);
			break;
		}
		if (intent != null)
			startActivity(intent);
	}

}

package com.example.conferencehermes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

import com.example.conferencehermes.util.Utilities;

public class InfoActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_info);

		findViewById(R.id.infoBtnCall).setOnClickListener(this);
		findViewById(R.id.infoBtnGrads).setOnClickListener(this);
		findViewById(R.id.infoBtnInfo).setOnClickListener(this);
		findViewById(R.id.infoBtnPin).setOnClickListener(this);
		findViewById(R.id.infoBtnSpeech).setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.info, menu);
		return false;
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.infoBtnCall:
			Utilities.phoneCall(InfoActivity.this);
			break;
		case R.id.infoBtnGrads:

			break;
		case R.id.infoBtnInfo:
			Utilities.showInfoDialog(InfoActivity.this);
			break;
		case R.id.infoBtnPin:
			intent = new Intent(InfoActivity.this, MapActivity.class);
			break;
		case R.id.infoBtnSpeech:
			intent = new Intent(InfoActivity.this, NewsActivity.class);
			break;

		default:
			break;
		}
		if (intent != null) {
			startActivity(intent);
			finish();
		}

	}

}

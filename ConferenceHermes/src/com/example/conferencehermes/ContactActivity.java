package com.example.conferencehermes;

import com.example.conferencehermes.util.Utilities;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class ContactActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_contact);

		findViewById(R.id.contactBtnCall).setOnClickListener(this);
		findViewById(R.id.contactBtnGrads).setOnClickListener(this);
		findViewById(R.id.contactBtnInfo).setOnClickListener(this);
		findViewById(R.id.contactBtnPin).setOnClickListener(this);
		findViewById(R.id.contactBtnSpeech).setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.contact, menu);
		return false;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.contactBtnCall:
			Utilities.phoneCall(ContactActivity.this);
			break;
		case R.id.contactBtnGrads:

			break;
		case R.id.contactBtnInfo:
			startActivity(new Intent(ContactActivity.this, InfoActivity.class));
			break;
		case R.id.contactBtnPin:

			break;
		case R.id.contactBtnSpeech:

			break;

		default:
			break;
		}

	}

}

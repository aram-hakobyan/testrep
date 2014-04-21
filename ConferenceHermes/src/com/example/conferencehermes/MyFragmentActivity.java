package com.example.conferencehermes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import com.example.conferencehermes.util.Constants;
import com.example.conferencehermes.util.DataHolder;
import com.example.conferencehermes.util.Utilities;

public class MyFragmentActivity extends FragmentActivity implements
		OnClickListener {

	private TextView title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_my_fragment);

		findViewById(R.id.viewBtnCall).setOnClickListener(this);
		findViewById(R.id.viewBtnGrads).setOnClickListener(this);
		findViewById(R.id.viewBtnInfo).setOnClickListener(this);
		findViewById(R.id.viewBtnPin).setOnClickListener(this);
		findViewById(R.id.viewBtnSpeech).setOnClickListener(this);
		findViewById(R.id.footer_menu_text).setOnClickListener(this);
		title = (TextView) findViewById(R.id.titleTextView);

		int PAGE_ID = getIntent().getIntExtra("PAGE_ID", 0);
		Fragment fr = null;
		switch (PAGE_ID) {
		case 0:
			title.setText(getResources().getString(R.string.main_menu_text1));
			fr = new InfoFragment();
			break;
		case 1:
			title.setText(getResources().getString(R.string.lescentres));
			fr = new MapFragment();
			break;
		case 2:
			title.setText(getResources().getString(R.string.main_menu_text2));
			fr = new NewsFragment();
			break;

		default:
			break;
		}

		if (fr != null) {
			FragmentManager fm = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fm.beginTransaction();
			fragmentTransaction.replace(R.id.fragmentContainer, fr);
			fragmentTransaction.commit();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.my, menu);
		return false;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.viewBtnCall:
			Utilities.phoneCall(MyFragmentActivity.this);
			break;
		case R.id.viewBtnGrads:
			title.setText(getResources().getString(R.string.main_menu_text1));
			Utilities.selectFrag(this, Constants.INFO_FRAGMENT);
			break;
		case R.id.viewBtnInfo:
			Utilities.showInfoDialog(MyFragmentActivity.this);
			break;
		case R.id.viewBtnPin:
			if (DataHolder.getInstance().getCURRENT_PAGE() != Constants.MAP_FRAGMENT) {
				title.setText(getResources().getString(R.string.lescentres));
				Utilities.selectFrag(this, Constants.MAP_FRAGMENT);
			}
			break;
		case R.id.viewBtnSpeech:
			title.setText(getResources().getString(R.string.main_menu_text2));
			Utilities.selectFrag(this, Constants.NEWS_FRAGMENT);
			break;
		case R.id.footer_menu_text:
			Utilities.openWebsite(MyFragmentActivity.this);
			break;

		default:
			break;
		}

	}

}

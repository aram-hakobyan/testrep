package com.example.conferencehermes;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import com.example.conferencehermes.util.DataHolder;
import com.example.conferencehermes.util.News;
import com.example.conferencehermes.util.Utilities;

public class ReadNewsActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_read_news);

		findViewById(R.id.readnewsBtnInfo).setOnClickListener(this);

		int newsId = getIntent().getIntExtra("newsId", -1);
		if (newsId > -1) {
			News news = DataHolder.getInstance().getNewsArray().get(newsId);
			TextView title = (TextView) findViewById(R.id.read_news_title);
			TextView text = (TextView) findViewById(R.id.read_news_text);
			title.setText(news.getTitle());
			text.setText(news.getDesc());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.read_news, menu);
		return false;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.readnewsBtnInfo:
			Utilities.showInfoDialog(ReadNewsActivity.this);
			break;

		default:
			break;
		}

	}

}

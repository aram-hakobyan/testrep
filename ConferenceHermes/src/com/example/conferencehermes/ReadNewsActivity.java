package com.example.conferencehermes;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.example.conferencehermes.util.DataHolder;
import com.example.conferencehermes.util.News;

public class ReadNewsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_read_news);

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

}

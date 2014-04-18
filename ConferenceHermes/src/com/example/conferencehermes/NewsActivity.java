package com.example.conferencehermes;

import java.util.ArrayList;

import com.example.conferencehermes.util.DataHolder;
import com.example.conferencehermes.util.News;
import com.example.conferencehermes.util.NewsAdapter;
import com.example.conferencehermes.util.Utilities;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class NewsActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_news);

		findViewById(R.id.newsBtnCall).setOnClickListener(this);
		findViewById(R.id.newsBtnGrads).setOnClickListener(this);
		findViewById(R.id.newsBtnInfo).setOnClickListener(this);
		findViewById(R.id.newsBtnPin).setOnClickListener(this);
		findViewById(R.id.newsBtnSpeech).setOnClickListener(this);

		ArrayList<News> newsList = new ArrayList<News>();
		for (int i = 0; i < 50; i++) {
			String title = "Title " + String.valueOf(i);
			String desc = getResources().getString(R.string.app_name);
			News mNews = new News(title, (desc + desc + desc + desc + desc));
			newsList.add(mNews);
		}

		DataHolder.getInstance().setNewsArray(newsList);

		ListView listViewNews = (ListView) findViewById(R.id.listViewNews);
		listViewNews.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(NewsActivity.this,
						ReadNewsActivity.class);
				intent.putExtra("newsId", position);
				startActivity(intent);
			}

		});

		NewsAdapter newsAdapter = new NewsAdapter(NewsActivity.this, newsList);
		listViewNews.setAdapter(newsAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.news, menu);
		return false;
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.newsBtnCall:
			Utilities.phoneCall(NewsActivity.this);
			break;
		case R.id.newsBtnGrads:
			intent = new Intent(NewsActivity.this, InfoActivity.class);
			break;
		case R.id.newsBtnInfo:
			Utilities.showInfoDialog(NewsActivity.this);
			break;
		case R.id.newsBtnPin:
			intent = new Intent(NewsActivity.this, MapActivity.class);
			break;
		case R.id.newsBtnSpeech:

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

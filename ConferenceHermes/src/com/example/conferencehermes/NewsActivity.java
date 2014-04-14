package com.example.conferencehermes;

import java.util.ArrayList;

import com.example.conferencehermes.util.DataHolder;
import com.example.conferencehermes.util.News;
import com.example.conferencehermes.util.NewsAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class NewsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_news);

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

}

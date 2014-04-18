package com.example.conferencehermes;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.conferencehermes.util.Constants;
import com.example.conferencehermes.util.DataHolder;
import com.example.conferencehermes.util.News;
import com.example.conferencehermes.util.NewsAdapter;

public class NewsFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View fragment = inflater.inflate(R.layout.activity_news, container,
				false);
		DataHolder.getInstance().setCURRENT_PAGE(Constants.NEWS_FRAGMENT);

		ArrayList<News> newsList = new ArrayList<News>();
		for (int i = 0; i < 50; i++) {
			String title = "Title " + String.valueOf(i);
			String desc = getResources().getString(R.string.app_name);
			News mNews = new News(title, (desc + desc + desc + desc + desc));
			newsList.add(mNews);
		}

		DataHolder.getInstance().setNewsArray(newsList);

		ListView listViewNews = (ListView) fragment
				.findViewById(R.id.listViewNews);
		listViewNews.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getActivity(),
						ReadNewsActivity.class);
				intent.putExtra("newsId", position);
				startActivity(intent);
			}

		});

		NewsAdapter newsAdapter = new NewsAdapter(getActivity(), newsList);
		listViewNews.setAdapter(newsAdapter);

		return fragment;
	}

}

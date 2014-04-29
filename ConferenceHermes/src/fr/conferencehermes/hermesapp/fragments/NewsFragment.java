package fr.conferencehermes.hermesapp.fragments;

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
import fr.conferencehermes.hermesapp.R;
import fr.conferencehermes.hermesapp.activities.ReadNewsActivity;
import fr.conferencehermes.hermesapp.adapters.NewsAdapter;
import fr.conferencehermes.hermesapp.util.Constants;
import fr.conferencehermes.hermesapp.util.DataHolder;
import fr.conferencehermes.hermesapp.util.News;

public class NewsFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View fragment = inflater.inflate(R.layout.activity_news, container,
				false);
		DataHolder.getInstance().setCURRENT_PAGE(Constants.NEWS_FRAGMENT);

		ArrayList<News> newsList = DataHolder.getInstance().getNewsArray();
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

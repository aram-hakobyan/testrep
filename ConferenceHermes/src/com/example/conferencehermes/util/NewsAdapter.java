package com.example.conferencehermes.util;

import java.util.ArrayList;

import com.example.conferencehermes.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsAdapter extends BaseAdapter {
	Context ctx;
	LayoutInflater inflater;
	ArrayList<News> news;

	static class ViewHolder {
		public TextView title;
		public TextView text;
		public ImageView image;
		public Button open;

	}

	public NewsAdapter(Context context, ArrayList<News> mNews) {
		this.ctx = context;
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.news = mNews;
	}

	@Override
	public int getCount() {
		return news.size();
	}

	@Override
	public Object getItem(int position) {
		return news.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.news_item, null);

			viewHolder = new ViewHolder();
			viewHolder.title = (TextView) convertView
					.findViewById(R.id.newsTitle);
			viewHolder.text = (TextView) convertView
					.findViewById(R.id.newsText);
			viewHolder.image = (ImageView) convertView
					.findViewById(R.id.newsImage);
			viewHolder.open = (Button) convertView.findViewById(R.id.newsArrow);

			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.title.setText(news.get(position).getTitle());
		viewHolder.text.setText(news.get(position).getDesc());

		return convertView;
	}

}

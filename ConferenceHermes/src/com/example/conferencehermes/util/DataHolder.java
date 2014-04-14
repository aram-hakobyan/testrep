package com.example.conferencehermes.util;

import java.util.ArrayList;

public class DataHolder {
	private static DataHolder instance;
	private ArrayList<News> newsArray;

	private DataHolder() {
		newsArray = new ArrayList<News>();
	}

	public static DataHolder getInstance() {
		if (instance == null) {
			instance = new DataHolder();
		}
		return instance;

	}

	public ArrayList<News> getNewsArray() {
		return newsArray;
	}

	public void setNewsArray(ArrayList<News> nArray) {
		this.newsArray = nArray;
	}
}

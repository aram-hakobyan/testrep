package com.example.conferencehermes.util;

import java.util.ArrayList;

import android.location.Location;

public class DataHolder {
	private static DataHolder instance;
	private ArrayList<News> newsArray;
	private ArrayList<Location> locations;
	private ArrayList<Place> places;

	private DataHolder() {
		setNewsArray(new ArrayList<News>());
		setLocations(new ArrayList<Location>());
		setPlaces(new ArrayList<Place>());
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

	public ArrayList<Location> getLocations() {
		return locations;
	}

	public void setLocations(ArrayList<Location> locations) {
		this.locations = locations;
	}

	public ArrayList<Place> getPlaces() {
		return places;
	}

	public void setPlaces(ArrayList<Place> places) {
		this.places = places;
	}
}

package fr.conferencehermes.hermesapp.util;

import java.util.ArrayList;

import android.location.Location;

public class DataHolder {
	private static DataHolder instance;
	private ArrayList<News> newsArray;
	private ArrayList<Location> locations;
	private ArrayList<Place> places;
	private int CURRENT_PAGE;
	private String aboutURL;
	private String infoURL;
	private String phone;
	private String joinURL;

	private DataHolder() {
		setNewsArray(new ArrayList<News>());
		setLocations(new ArrayList<Location>());
		setPlaces(new ArrayList<Place>());
		setCURRENT_PAGE(0);
		setAboutURL("");
		setInfoURL("");
		setPhone("");
		setJoinURL("");
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

	public int getCURRENT_PAGE() {
		return CURRENT_PAGE;
	}

	public void setCURRENT_PAGE(int cURRENT_PAGE) {
		CURRENT_PAGE = cURRENT_PAGE;
	}

	public String getAboutURL() {
		return aboutURL;
	}

	public void setAboutURL(String aboutURL) {
		this.aboutURL = aboutURL;
	}

	public String getInfoURL() {
		return infoURL;
	}

	public void setInfoURL(String infoURL) {
		this.infoURL = infoURL;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getJoinURL() {
		return joinURL;
	}

	public void setJoinURL(String joinURL) {
		this.joinURL = joinURL;
	}
}

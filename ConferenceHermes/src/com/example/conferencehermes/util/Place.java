package com.example.conferencehermes.util;

import java.util.ArrayList;

public class Place {
	private String name;
	private String adress;
	private String phone;
	private String website;
	private double lat;
	private double lng;
	private double rating;
	private int distance;
	private String reference;
	private String iconUrl;
	private ArrayList<String> photoLinks;
	private ArrayList<String> types;
	private boolean OPENNOW;
	private String openingHour;
	private String closingHour;

	/*** Constructors ***/

	public Place() {
		photoLinks = new ArrayList<String>();
		types = new ArrayList<String>();
		setName("");
		setAdress("");
		setLat(0);
		setLng(0);
		setRating(0);
		setPhone("");
		setWebsite("");
		setDistance(0);
		setReference("");
		setIconUrl("");
		setOPENNOW(true);
		setOpeningHour("");
		setClosingHour("");
	}

	public Place(String nAME, String aDRESS, double lAT, double lONG,
			double rATING, String pHONE, String wEBSITE, int dIST,
			String Reference, String iURL) {
		setName(nAME);
		setAdress(aDRESS);
		setLat(lAT);
		setLng(lONG);
		setRating(rATING);
		setPhone(pHONE);
		setWebsite(wEBSITE);
		setDistance(dIST);
		setReference(Reference);
		setIconUrl(iURL);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public ArrayList<String> getPhotoLinks() {
		return photoLinks;
	}

	public void setPhotoLinks(ArrayList<String> photoLinks) {
		this.photoLinks = photoLinks;
	}

	public ArrayList<String> getTypes() {
		return types;
	}

	public void setTypes(ArrayList<String> types) {
		this.types = types;
	}

	public boolean isOPENNOW() {
		return OPENNOW;
	}

	public void setOPENNOW(boolean oPENNOW) {
		OPENNOW = oPENNOW;
	}

	public String getOpeningHour() {
		return openingHour;
	}

	public void setOpeningHour(String openingHour) {
		this.openingHour = openingHour;
	}

	public String getClosingHour() {
		return closingHour;
	}

	public void setClosingHour(String closingHour) {
		this.closingHour = closingHour;
	}

}

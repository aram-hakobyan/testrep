package com.example.conferencehermes.util;

public class News {
	String title;
	String description;

	public News(String mTitle, String mDesc) {
		this.title = mTitle;
		this.description = mDesc;
	};

	public void setTitle(String t) {
		this.title = t;
	};

	public String getTitle() {
		return this.title;
	};

	public void setDesc(String d) {
		this.description = d;
	};

	public String getDesc() {
		return this.description;
	};

}

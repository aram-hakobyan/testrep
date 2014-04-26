package fr.conferencehermes.hermesapp.util;

import android.text.Spanned;

public class News {
	String title;
	Spanned description;
	String image;
	String html;

	public News(String mTitle, Spanned description2, String mImage, String mHtml) {
		this.title = mTitle;
		this.description = description2;
		this.image = mImage;
		this.html = mHtml;
	};

	public void setTitle(String t) {
		this.title = t;
	};

	public String getTitle() {
		return this.title;
	};

	public void setDesc(Spanned d) {
		this.description = d;
	};

	public Spanned getDesc() {
		return this.description;
	};

	public void setImage(String i) {
		this.image = i;
	};

	public String getImage() {
		return this.image;
	};

	public void setHtml(String i) {
		this.html = i;
	};

	public String getHtml() {
		return this.html;
	};

}

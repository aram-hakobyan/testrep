package com.conferencehermes.hermesapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import fr.conferencehermes.hermesapp.R;
import fr.conferencehermes.hermesapp.util.Constants;
import fr.conferencehermes.hermesapp.util.DataHolder;

public class InfoFragment extends Fragment {

	private WebView web;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View fragment = inflater.inflate(R.layout.activity_info, container,
				false);

		DataHolder.getInstance().setCURRENT_PAGE(Constants.INFO_FRAGMENT);

		web = (WebView) fragment.findViewById(R.id.webviewInfo);
		web.loadUrl(DataHolder.getInstance().getAboutURL() + "&"
				+ Constants.AUTH_TOKEN);
		web.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);

				return true;
			}
		});

		return fragment;
	}

}

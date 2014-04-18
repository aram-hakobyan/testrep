package com.example.conferencehermes;

import com.example.conferencehermes.util.Constants;
import com.example.conferencehermes.util.DataHolder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InfoFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View fragment = inflater.inflate(R.layout.activity_info, container,
				false);
		
		DataHolder.getInstance().setCURRENT_PAGE(Constants.INFO_FRAGMENT);

		return fragment;
	}

}

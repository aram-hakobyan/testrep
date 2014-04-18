package com.example.conferencehermes;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.conferencehermes.util.DataHolder;
import com.example.conferencehermes.util.Place;
import com.example.conferencehermes.util.Utilities;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnClickListener {
	private GoogleMap mMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_map);

		findViewById(R.id.mapviewBtnCall).setOnClickListener(this);
		findViewById(R.id.mapviewBtnGrads).setOnClickListener(this);
		findViewById(R.id.mapviewBtnInfo).setOnClickListener(this);
		findViewById(R.id.mapviewBtnPin).setOnClickListener(this);
		findViewById(R.id.mapviewBtnSpeech).setOnClickListener(this);

		if (mMap == null) {
			mMap = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.mapview)).getMap();
			if (mMap != null) {
				mMap.getUiSettings().setCompassEnabled(true);
				mMap.getUiSettings().setRotateGesturesEnabled(true);
				mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
				mMap.setMyLocationEnabled(true);
				// Location loc =
				// DataHolder.getInstance().getLocations().get(0);
				Place place = new Place("Place 1", "Adress", 48.866973,
						2.309135, 5, "", "", 1000, "", "");
				Place place2 = new Place("Place 2", "Adress", 48.860875,
						2.197212, 5, "", "", 1000, "", "");
				Place place3 = new Place("Place 3", "Adress", 48.7847, 2.2724,
						5, "", "", 1000, "", "");
				DataHolder.getInstance().getPlaces().add(place);
				DataHolder.getInstance().getPlaces().add(place2);
				DataHolder.getInstance().getPlaces().add(place3);
				if (place != null) {
					LatLng coordinate = new LatLng(place.getLat(),
							place.getLng());
					CameraUpdate yourLocation = CameraUpdateFactory
							.newLatLngZoom(coordinate, 12);
					mMap.animateCamera(yourLocation);

					drawMarkers(DataHolder.getInstance().getPlaces());
				}
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.map, menu);
		return false;
	}

	private void drawMarkers(ArrayList<Place> places) {
		if (mMap != null) {
			mMap.clear();

			if (!places.isEmpty() && places != null) {
				for (int j = 0; j < places.size(); j++) {
					Place place = places.get(j);
					mMap.addMarker(new MarkerOptions().position(
							new LatLng(place.getLat(), place.getLng())).title(
							place.getName()));
				}
			}

			mMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
				@Override
				public void onInfoWindowClick(Marker marker) {
					Dialog dialog = new Dialog(MapActivity.this,
							R.style.DialogSlideAnim);
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.getWindow().clearFlags(
							WindowManager.LayoutParams.FLAG_DIM_BEHIND);
					dialog.getWindow().setGravity(Gravity.BOTTOM);
					dialog.getWindow().setBackgroundDrawableResource(
							android.R.color.transparent);
					dialog.setContentView(R.layout.dialog_place);
					TextView title = (TextView) dialog
							.findViewById(R.id.placeTitle);
					TextView desc = (TextView) dialog
							.findViewById(R.id.placeDetails);
					title.setText(marker.getTitle());
					desc.setText("place details");

					if (!dialog.isShowing())
						dialog.show();
					else
						dialog.dismiss();

					WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
					Window window = dialog.getWindow();
					lp.copyFrom(window.getAttributes());
					lp.width = WindowManager.LayoutParams.MATCH_PARENT;
					lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
					lp.y = (int) TypedValue.applyDimension(
							TypedValue.COMPLEX_UNIT_DIP, 60, getResources()
									.getDisplayMetrics());
					window.setAttributes(lp);
				}
			});
		}

	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.mapviewBtnCall:
			Utilities.phoneCall(MapActivity.this);
			break;
		case R.id.mapviewBtnGrads:
			intent = new Intent(MapActivity.this, InfoActivity.class);
			break;
		case R.id.mapviewBtnInfo:
			Utilities.showInfoDialog(MapActivity.this);
			break;
		case R.id.mapviewBtnPin:

			break;
		case R.id.mapviewBtnSpeech:
			intent = new Intent(MapActivity.this, NewsActivity.class);
			break;

		default:
			break;
		}
		if (intent != null) {
			startActivity(intent);
			finish();
		}

	}
}

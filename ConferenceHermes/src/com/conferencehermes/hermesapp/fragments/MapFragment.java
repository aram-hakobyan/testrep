package com.conferencehermes.hermesapp.fragments;

import java.util.ArrayList;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import fr.conferencehermes.hermesapp.R;
import fr.conferencehermes.hermesapp.util.Constants;
import fr.conferencehermes.hermesapp.util.DataHolder;
import fr.conferencehermes.hermesapp.util.Place;

public class MapFragment extends Fragment {
	private GoogleMap mMap;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View fragment = inflater.inflate(R.layout.activity_map, container,
				false);
		DataHolder.getInstance().setCURRENT_PAGE(Constants.MAP_FRAGMENT);

		if (mMap == null) {
			mMap = ((SupportMapFragment) getActivity()
					.getSupportFragmentManager().findFragmentById(R.id.mapview))
					.getMap();
			if (mMap != null) {
				mMap.getUiSettings().setCompassEnabled(true);
				mMap.getUiSettings().setRotateGesturesEnabled(true);
				mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
				mMap.setMyLocationEnabled(true);

				ArrayList<Place> places = DataHolder.getInstance().getPlaces();
				Place place = places.get(0);
				if (place != null) {
					LatLng coordinate = new LatLng(place.getLat(),
							place.getLng());
					CameraUpdate yourLocation = CameraUpdateFactory
							.newLatLngZoom(coordinate, 12);
					mMap.animateCamera(yourLocation);

					if (!places.isEmpty())
						drawMarkers(places);
				}
			}
		}

		return fragment;

	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Fragment fragment = (getActivity().getSupportFragmentManager()
				.findFragmentById(R.id.mapview));
		if (fragment.isResumed()) {
			FragmentTransaction ft = getActivity().getSupportFragmentManager()
					.beginTransaction();
			ft.remove(fragment);
			ft.commit();
		}
	}

	private void drawMarkers(final ArrayList<Place> places) {
		if (mMap != null) {
			mMap.clear();

			for (int j = 0; j < places.size(); j++) {
				Place place = places.get(j);
				mMap.addMarker(new MarkerOptions().position(
						new LatLng(place.getLat(), place.getLng())).title(
						place.getName()));

			}

			mMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
				@Override
				public void onInfoWindowClick(Marker marker) {
					Dialog dialog = new Dialog(getActivity(),
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

					int pos = 0;
					for (int m = 0; m < places.size(); m++) {
						if (places.get(m).getName()
								.equalsIgnoreCase(marker.getTitle())) {
							pos = m;
							break;
						}
					}

					Place place = places.get(pos);
					desc.setText(place.getAdress() + "\n" + place.getPhone());

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

}

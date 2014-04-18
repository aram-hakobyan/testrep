package com.example.conferencehermes.util;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.conferencehermes.InfoFragment;
import com.example.conferencehermes.MapFragment;
import com.example.conferencehermes.NewsFragment;
import com.example.conferencehermes.R;

public class Utilities {
	private static ProgressDialog mDialog;

	public static void phoneCall(Context context) {
		Intent callIntent = new Intent(Intent.ACTION_CALL);
		callIntent.setData(Uri.parse("tel:" + "+0102030405"));
		context.startActivity(callIntent);
	};

	public static void selectFrag(FragmentActivity a, int PAGE_ID) {
		Fragment fr = null;

		switch (PAGE_ID) {
		case 0:
			fr = new InfoFragment();
			break;
		case 1:
			fr = new MapFragment();
			break;
		case 2:
			fr = new NewsFragment();
			break;

		default:
			break;
		}

		if (fr != null) {
			FragmentManager fm = a.getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fm.beginTransaction();
			fragmentTransaction.replace(R.id.fragmentContainer, fr);
			fragmentTransaction.commit();
		}

	}

	public static void showOrHideActivityIndicator(Context ctx, final int hide,
			final String message) {
		if (mDialog != null && hide == 1) {
			if (mDialog.isShowing())
				mDialog.dismiss();
		} else {
			mDialog = new ProgressDialog(ctx);
			mDialog.setMessage(message);
			mDialog.setCancelable(false);
			mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			mDialog.show();
		}
	}

	public static void showInfoDialog(Context context) {
		Dialog dialog = new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().clearFlags(
				WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		dialog.getWindow().setGravity(Gravity.BOTTOM);
		dialog.getWindow().setBackgroundDrawableResource(
				android.R.color.transparent);
		dialog.setContentView(R.layout.info_dialog);

		if (!dialog.isShowing())
			dialog.show();
		else
			dialog.dismiss();

		WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
		Window window = dialog.getWindow();
		lp.copyFrom(window.getAttributes());
		lp.width = WindowManager.LayoutParams.MATCH_PARENT;
		lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
		window.setAttributes(lp);
	}

	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connectivity == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

}

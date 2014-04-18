package com.example.conferencehermes.util;

import com.example.conferencehermes.MapActivity;
import com.example.conferencehermes.R;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class Utilities {
	private static ProgressDialog mDialog;

	public static void phoneCall(Context context) {
		Intent callIntent = new Intent(Intent.ACTION_CALL);
		callIntent.setData(Uri.parse("tel:" + "+0102030405"));
		context.startActivity(callIntent);
	};

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

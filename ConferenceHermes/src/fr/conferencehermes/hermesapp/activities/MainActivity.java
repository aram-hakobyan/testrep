package fr.conferencehermes.hermesapp.activities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import fr.conferencehermes.hermesapp.R;
import fr.conferencehermes.hermesapp.util.Constants;
import fr.conferencehermes.hermesapp.util.DataHolder;
import fr.conferencehermes.hermesapp.util.News;
import fr.conferencehermes.hermesapp.util.Place;
import fr.conferencehermes.hermesapp.util.Utilities;

public class MainActivity extends FragmentActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		String URL = Constants.SERVER_URL + "?" + Constants.AUTH_TOKEN;
		downloadAsyncTask task = new downloadAsyncTask();
		task.execute(URL);

		findViewById(R.id.startButton1).setOnClickListener(this);
		findViewById(R.id.startButton2).setOnClickListener(this);
		findViewById(R.id.startButton3).setOnClickListener(this);
		findViewById(R.id.startButton4).setOnClickListener(this);
		findViewById(R.id.mainInfoBtn).setOnClickListener(this);
		findViewById(R.id.mainFooterText).setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return false;
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.startButton1:
			intent = new Intent(MainActivity.this, MyFragmentActivity.class);
			intent.putExtra("PAGE_ID", Constants.INFO_FRAGMENT);
			break;
		case R.id.startButton2:
			intent = new Intent(MainActivity.this, MyFragmentActivity.class);
			intent.putExtra("PAGE_ID", Constants.NEWS_FRAGMENT);
			break;
		case R.id.startButton3:
			intent = new Intent(MainActivity.this, MyFragmentActivity.class);
			intent.putExtra("PAGE_ID", Constants.MAP_FRAGMENT);
			break;
		case R.id.startButton4:
			// intent = new Intent(MainActivity.this, ContactActivity.class);
			Utilities.phoneCall(MainActivity.this);
			break;
		case R.id.mainInfoBtn:
			Utilities.showInfoDialog(MainActivity.this);
			break;
		case R.id.mainFooterText:
			Utilities.openWebsite(MainActivity.this);
			break;
		}
		if (intent != null)
			startActivity(intent);
	}

	class downloadAsyncTask extends AsyncTask<String, String, String> {

		private ProgressDialog progressDialog = new ProgressDialog(
				MainActivity.this);
		InputStream inputStream = null;
		String url;
		String result = "";

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog.setMessage("Downloading data...");
			progressDialog.show();
			progressDialog.setOnCancelListener(new OnCancelListener() {
				public void onCancel(DialogInterface arg0) {
					downloadAsyncTask.this.cancel(true);
				}
			});
		}

		@Override
		protected String doInBackground(String... params) {
			String url_select = params[0];
			System.out.println(url_select);

			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(url_select);
				HttpResponse httpResponse = httpClient.execute(httpGet);
				HttpEntity httpEntity = httpResponse.getEntity();
				inputStream = httpEntity.getContent();
			} catch (UnsupportedEncodingException e1) {
				Log.e("UnsupportedEncodingException", e1.toString());
				e1.printStackTrace();
			} catch (ClientProtocolException e2) {
				Log.e("ClientProtocolException", e2.toString());
				e2.printStackTrace();
			} catch (IllegalStateException e3) {
				Log.e("IllegalStateException", e3.toString());
				e3.printStackTrace();
			} catch (IOException e4) {
				Log.e("IOException", e4.toString());
				e4.printStackTrace();
			}

			try {
				BufferedReader bReader = new BufferedReader(
						new InputStreamReader(inputStream, "iso-8859-1"), 8);
				StringBuilder sBuilder = new StringBuilder();

				String line = null;
				while ((line = bReader.readLine()) != null) {
					sBuilder.append(line + "\n");
				}

				inputStream.close();
				result = sBuilder.toString();

			} catch (Exception e) {
				Log.e("StringBuilding & BufferedReader",
						"Error converting result " + e.toString());
			}

			System.out.println(result);
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			try {
				JSONObject jObject = new JSONObject(result);
				ArrayList<Place> places = new ArrayList<Place>();
				ArrayList<News> articles = new ArrayList<News>();

				JSONArray mapArray = jObject.getJSONArray("maps");
				for (int i = 0; i < mapArray.length(); i++) {
					JSONObject jObj = (JSONObject) mapArray.get(i);
					String title = jObj.getString("title");
					String phone = jObj.getString("phone");
					String address = jObj.getString("address");
					String latitude = jObj.getString("lat");
					String longitude = jObj.getString("long");

					Place p = new Place();
					p.setName(title);
					p.setPhone(phone);
					p.setAdress(address);
					p.setLat(Double.valueOf(latitude));
					p.setLng(Double.valueOf(longitude));
					places.add(p);
				}

				JSONArray newsArray = jObject.getJSONArray("articles");
				for (int i = 0; i < newsArray.length(); i++) {
					JSONObject jObj = (JSONObject) newsArray.get(i);
					String title = jObj.getString("title");
					String image = jObj.getString("image");
					Spanned description = Html.fromHtml(jObj
							.getString("description"));
					String html = jObj.getString("html");

					News n = new News(title, description, image, html);
					articles.add(n);
				}

				DataHolder.getInstance().setPlaces(places);
				DataHolder.getInstance().setNewsArray(articles);
				DataHolder.getInstance()
						.setAboutURL(jObject.getString("about"));
				DataHolder.getInstance().setInfoURL(jObject.getString("info"));
				DataHolder.getInstance().setPhone(jObject.getString("phone"));

			} catch (JSONException e) {
				Log.e("JSONException", "Error: " + e.toString());
			} finally {
				this.progressDialog.dismiss();
			}

		}
	}

}

package fr.conferencehermes.hermesapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import fr.conferencehermes.hermesapp.R;
import fr.conferencehermes.hermesapp.imageloader.ImageLoader;
import fr.conferencehermes.hermesapp.util.Constants;
import fr.conferencehermes.hermesapp.util.DataHolder;
import fr.conferencehermes.hermesapp.util.News;
import fr.conferencehermes.hermesapp.util.Utilities;

public class ReadNewsActivity extends Activity implements OnClickListener {
	private WebView webview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_read_news);

		findViewById(R.id.readnewsBtnCall).setOnClickListener(this);
		findViewById(R.id.readnewsBtnGrads).setOnClickListener(this);
		findViewById(R.id.readnewsBtnPin).setOnClickListener(this);
		findViewById(R.id.readnewsBtnSpeech).setOnClickListener(this);
		findViewById(R.id.readnewsBtnInfo).setOnClickListener(this);
		findViewById(R.id.retourLayout).setOnClickListener(this);
		findViewById(R.id.readnewsFooterText).setOnClickListener(this);

		int newsId = getIntent().getIntExtra("newsId", -1);
		if (newsId > -1) {
			News news = DataHolder.getInstance().getNewsArray().get(newsId);
			TextView title = (TextView) findViewById(R.id.read_news_title);
			TextView text = (TextView) findViewById(R.id.read_news_text);
			ImageView img = (ImageView) findViewById(R.id.read_news_title_image);

			title.setText(news.getTitle());
			text.setText(news.getDesc());
			int loader = R.drawable.news_image_left;
			String image_url = news.getImage();
			ImageLoader imgLoader = new ImageLoader(ReadNewsActivity.this);
			imgLoader.DisplayImage(image_url, loader, img);

			webview = (WebView) findViewById(R.id.webviewReadNews);
			webview.loadUrl(news.getHtml() + "&" + Constants.AUTH_TOKEN);
			webview.setWebViewClient(new WebViewClient() {
				@Override
				public boolean shouldOverrideUrlLoading(WebView view, String url) {
					view.loadUrl(url);
					return true;
				}
			});
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.read_news, menu);
		return false;
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.readnewsBtnCall:
			Utilities.phoneCall(ReadNewsActivity.this);
			break;
		case R.id.readnewsBtnGrads:
			intent = new Intent(ReadNewsActivity.this, MyFragmentActivity.class);
			intent.putExtra("PAGE_ID", Constants.INFO_FRAGMENT);
			break;
		case R.id.readnewsBtnPin:
			intent = new Intent(ReadNewsActivity.this, MyFragmentActivity.class);
			intent.putExtra("PAGE_ID", Constants.MAP_FRAGMENT);
			break;
		case R.id.readnewsBtnSpeech:
			intent = new Intent(ReadNewsActivity.this, MyFragmentActivity.class);
			intent.putExtra("PAGE_ID", Constants.NEWS_FRAGMENT);
			break;
		case R.id.readnewsBtnInfo:
			Utilities.showInfoDialog(ReadNewsActivity.this);
			break;
		case R.id.retourLayout:
			finish();
			break;
		case R.id.readnewsFooterText:
			Utilities.openWebsite(ReadNewsActivity.this);
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

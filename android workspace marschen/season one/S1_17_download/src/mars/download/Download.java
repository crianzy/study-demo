package mars.download;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import mars.utils.HttpDownloader;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.s1_17_download.R;

public class Download extends Activity {
	private static final String DEBUG_TAG = "Debug";
	/** Called when the activity is first created. */
	private Button downloadTxtButton;
	private Button downloadMp3Button;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		downloadTxtButton = (Button) findViewById(R.id.downloadTxt);
		downloadTxtButton.setOnClickListener(new DownloadTxtListener());
		downloadMp3Button = (Button) findViewById(R.id.downloadMp3);
		downloadMp3Button.setOnClickListener(new DownloadMp3Listener());
	}

	class DownloadTxtListener implements OnClickListener {

		@Override
		public void onClick(View v) {

			// String stringUrl =
			// "http://192.168.137.1:8080/android_server_demo/music.krc";
			String stringUrl = "http://192.168.137.1:8080/android_server_demo/test.txt";
			new DownloadTextTask().execute(stringUrl);
		}

	}

	class DownloadMp3Listener implements OnClickListener {

		@Override
		public void onClick(View v) {

			String stringUrl = "http://192.168.137.1:8080/android_server_demo/music.mp3";
			new DownloadFileTask().execute(stringUrl);
		}

	}

	private class DownloadTextTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... urls) {

			// params comes from the execute() call: params[0] is the url.
			try {
				HttpDownloader httpDownloader = new HttpDownloader();
				return httpDownloader.download(urls[0]);
			} catch (Exception e) {
				return "Unable to retrieve web page. URL may be invalid.";
			}
		}

		// onPostExecute displays the results of the AsyncTask.
		@Override
		protected void onPostExecute(String result) {
			Log.i(DEBUG_TAG, "result : " + result);
		}
	}

	private class DownloadFileTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... urls) {

			// params comes from the execute() call: params[0] is the url.
			try {
				HttpDownloader httpDownloader = new HttpDownloader();
				int result = httpDownloader
						.downFile(
								"http://192.168.137.1:8080/android_server_demo/music.mp3",
								"voa/", "a1.mp3");
				return result + "";
			} catch (Exception e) {
				return "Unable to retrieve web page. URL may be invalid.";
			}
		}

		// onPostExecute displays the results of the AsyncTask.
		@Override
		protected void onPostExecute(String result) {
			Log.i(DEBUG_TAG, "result : " + result);
		}
	}
	
}
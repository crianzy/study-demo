package mars.xml;

import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import mars.utils.HttpDownloader;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.s1_18_xml.R;

public class XMLActitity extends Activity {
	/** Called when the activity is first created. */
	private static final String DEBUG_TAG = "Debug";
	private Button parseButton;
	private String resultStr;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		parseButton = (Button) findViewById(R.id.parseButton);
		parseButton.setOnClickListener(new ParseButtonListener());
	}

	class ParseButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			String stringUrl = "http://192.168.137.1:8080/android_server_demo/test.xml";
			new DownloadXMLTask().execute(stringUrl);
		}

	}

	private class DownloadXMLTask extends AsyncTask<String, Void, String> {
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
			resultStr = result;
			// 创建一个SAXParserFactory
			try {
				SAXParserFactory factory = SAXParserFactory.newInstance();
				XMLReader reader;
				reader = factory.newSAXParser().getXMLReader();
				// 为XMLReader设置内容处理器
				reader.setContentHandler(new MyContentHandler());
				// 开始解析文件
				reader.parse(new InputSource(new StringReader(resultStr)));
			} catch (Exception e) {
				e.printStackTrace();
			}
			Log.i(DEBUG_TAG, "result : " + result);
		}
	}
}
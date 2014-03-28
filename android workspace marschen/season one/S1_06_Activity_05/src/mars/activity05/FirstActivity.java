package mars.activity05;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.s1_06_activity_05.R;

public class FirstActivity extends Activity {
	/** Called when the activity is first created. */
	private Button myButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		System.out.println("FirstActivity ---> onCreate	 ");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		myButton = (Button) findViewById(R.id.myButton);
		myButton.setOnClickListener(new ButtonListener());
	}

	@Override
	protected void onDestroy() {

		System.out.println("FirstAcvity --->onDestory");
		super.onDestroy();
	}

	@Override
	protected void onPause() {

		System.out.println("FirstAcvity --->onPause");
		super.onPause();
	}

	@Override
	protected void onRestart() {

		System.out.println("FirstAcvity --->onRestart");
		super.onRestart();
	}

	@Override
	protected void onResume() {

		System.out.println("FirstAcvity --->onResume");
		super.onResume();
	}

	@Override
	protected void onStart() {

		System.out.println("FirstAcvity --->onStart");
		super.onStart();
	}

	@Override
	protected void onStop() {

		System.out.println("FirstAcvity --->onStop");
		super.onStop();
	}

	class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {

			Intent intent = new Intent();
			intent.setClass(FirstActivity.this, SecondActivity.class);
			FirstActivity.this.startActivity(intent);
		}

	}

}
package mars.activity05;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.s1_06_activity_05.R;

public class SecondActivity extends Activity {

	private Button secondButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		System.out.println("SecondActivity--->onCreate");

		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		secondButton = (Button) findViewById(R.id.secondButton);
		secondButton.setOnClickListener(new ButtonListener());
	}

	@Override
	protected void onDestroy() {

		System.out.println("SecondActivity--->onDestory");
		super.onDestroy();
	}

	@Override
	protected void onPause() {

		System.out.println("SecondActivity--->onPause");
		super.onPause();
	}

	@Override
	protected void onRestart() {

		System.out.println("SecondActivity--->onRestart");
		super.onRestart();
	}

	@Override
	protected void onResume() {

		System.out.println("SecondActivity--->onResume");
		super.onResume();
	}

	@Override
	protected void onStart() {

		System.out.println("SecondActivity--->onStart");
		super.onStart();
	}

	@Override
	protected void onStop() {

		System.out.println("SecondActivity--->onStop");
		super.onStop();
	}

	class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {

			Intent intent = new Intent();
			intent.setClass(SecondActivity.this, FirstActivity.class);
			SecondActivity.this.startActivity(intent);
		}

	}
}

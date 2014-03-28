package mars.activity02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.s1_03_activity_02.R;

public class OtherActivity extends Activity{
	private TextView myTextView = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.other);
		//取得从上一个Activity当中传递过来的Intent对象
		Intent intent = getIntent();
		//从Intent当中根据key取得value
		String value = intent.getStringExtra("testIntent");
		//根据控件的ID得到响应的控件对象
		myTextView = (TextView)findViewById(R.id.myTextView);
		//为控件设置Text值
		myTextView.setText(value);
		
	}

}

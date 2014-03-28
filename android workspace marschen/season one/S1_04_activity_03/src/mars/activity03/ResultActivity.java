package mars.activity03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.s1_04_activity_03.R;
//1.接受从Activity03当中所传递的值
//2.计算两个值的积
//3.将计算的结果显示在Activity上
public class ResultActivity extends Activity{
	private TextView resultView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		resultView = (TextView)findViewById(R.id.result);
		//得到Intent对象当中的值 
		Intent intent = getIntent();
		String factorOneStr = intent.getStringExtra("one");
		String factorTwoStr = intent.getStringExtra("two");
		int factorOneInt = Integer.parseInt(factorOneStr);
		int factorTwoInt = Integer.parseInt(factorTwoStr);
		//计算两个值的积
		int result = factorOneInt * factorTwoInt;
		resultView.setText(result + "");
	}

}

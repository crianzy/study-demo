package mars.barhandler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.s1_14_progressbarhandler.R;

public class TestBarHandler extends Activity {
	/** Called when the activity is first created. */
	// �����ؼ�����
	ProgressBar bar = null;
	Button startButton = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ���ݿؼ���ID�õ�����ؼ��Ķ���,��Ϊ��ť���ü�����
		bar = (ProgressBar) findViewById(R.id.bar);
		startButton = (Button) findViewById(R.id.startButton);
		startButton.setOnClickListener(new ButtonListener());
	}

	// �����startButton��ťʱ���ͻ�ִ��ButtonListener��onClick����
	class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			bar.setVisibility(View.VISIBLE);
			updateBarHandler.post(updateThread);
		}

	}

	// ʹ�������ڲ�������дHandler���е�handleMessage����
	Handler updateBarHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			bar.setProgress(msg.arg1);
			Bundle bundle = msg.getData();
			updateBarHandler.post(updateThread);
			System.out.println("test---->" + bundle.getString("test"));
		}

	};
	// �߳��࣬����ʹ�������ڲ���ķ�ʽ��������
	Runnable updateThread = new Runnable() {
		int i = 0;

		@Override
		public void run() {
			System.out.println("Begin Thread" + i);
			i = i + 10;
			// �õ�һ����Ϣ����Message������Android����ϵͳ�ṩ
			Message msg = updateBarHandler.obtainMessage();

			// ��msg�����arg1������ֵ����Ϊi,��arg1��arg2��������Ա����������Ϣ���ŵ���ϵͳ�������Ľ���
			msg.arg1 = i;
			Bundle bundle = new Bundle();
			bundle.putString("test", "test bundle");
			msg.setData(bundle);
			try {
				// ���õ�ǰ��ʾ˯��1��
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// ��msg������뵽��Ϣ���е���
			if (i > 100) {
				// �����i��ֵΪ100ʱ���ͽ��̶߳����handler�����Ƴ�
				updateBarHandler.removeCallbacks(updateThread);
				System.out.println(">>>>>>");
			} else {
				updateBarHandler.sendMessage(msg);
				System.out.println("<<<<<<");
			}
		}
	};

	class MyThread extends Thread {
		public void run() {

		}
	}

}
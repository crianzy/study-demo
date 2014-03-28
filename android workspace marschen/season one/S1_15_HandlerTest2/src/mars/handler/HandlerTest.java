package mars.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.example.s1_15_handlertest2.R;

public class HandlerTest extends Activity {
	private Handler handler = new Handler();
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// sendMessage();
		//handler.post(r);
		setContentView(R.layout.main);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("a--->" + System.currentTimeMillis());
		//Thread t = new Thread(r) ;
		//t.start();
		System.out.println("activity--->" + Thread.currentThread().getId());
		System.out.println("activityname--->" + Thread.currentThread().getName());
	}
	
	
	Thread r = new Thread(){
		@Override
		public void start(){
			System.out.println("start---------");
			super.start();
		}
		@Override
		public void run() {
			System.out.println("r1--->" + System.currentTimeMillis());
			// TODO Auto-generated method stub
			System.out.println("handler--->" + Thread.currentThread().getId());
			System.out.println("handlername--->" + Thread.currentThread().getName());
			try {
				Thread.sleep(10000);
				System.out.println("r2---?" + System.currentTimeMillis());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	};

}
package mars.listview;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.s1_12_listview.R;

public class Activity01 extends ListActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map1 = new HashMap<String, String>();
		HashMap<String, String> map2 = new HashMap<String, String>();
		HashMap<String, String> map3 = new HashMap<String, String>();
		map1.put("user_name", "zhangsan");
		map1.put("user_ip", "192.168.0.1");
		map2.put("user_name", "zhangsan");
		map2.put("user_ip", "192.168.0.2");
		map3.put("user_name", "wangwu");
		map3.put("user_ip", "192.168.0.3");
		for (int i = 0; i < 10; i++) {
			list.add(map1);
			list.add(map2);
			list.add(map3);
		}
		MyAdapter listAdapter = new MyAdapter(this, list, R.layout.user1,
				new String[] { "user_name", "user_ip" }, new int[] {
						R.id.user_name, R.id.user_ip });
		setListAdapter(listAdapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		System.out.println("id----------------" + id);
		System.out.println("position----------" + position);
	}

}
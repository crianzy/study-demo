package mars.helloworld;

import android.app.Activity;
import android.os.Bundle;

import com.mars.s1_01_helloword.R;

public class HelloActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}
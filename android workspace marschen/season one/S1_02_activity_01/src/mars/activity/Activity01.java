package mars.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.s1_01_activity_01.R;

/**
 * ����Activity��Ҫ��
 * 1.һ��Activity����һ���࣬���������Ҫ�̳�Activity
 * 2.��Ҫ��дonCreate����
 * 3.ÿһ��Activity����Ҫ��AndroidManifest.xml�ļ����н�������
 * 4.ΪActivity��ӱ�Ҫ�Ŀؼ�
 * @author mars_chenchuan
 *
 */
public class Activity01 extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	//���ø��൱�е�onCreate����
        super.onCreate(savedInstanceState);
        //���õ�ǰ��Activityʹ��main.xml��Ϊ�����ļ�������R.layout.main��main.xml�ļ���R.java�ļ����е�ID
        setContentView(R.layout.main);
        //��main.xml�ļ�����������Ŀؼ���������R.java�ļ����в�����Ӧ��ID�����д�������þ����ڸ������ID��ȡ�ô���ÿؼ��Ķ���
        TextView myTextView = (TextView)findViewById(R.id.myTextView);
        //��һ�е����ú���һ�����ƣ�ֻ������һ��ȡ�õ��Ǵ���ť�Ķ���
        Button myButton = (Button)findViewById(R.id.myButton);
        //ΪTextView�ؼ�����Stringֵ
        myTextView.setText("�ҵĵ�һ��TextView");
        //ΪButton�ؼ�����Stringֵ
        myButton.setText("�ҵĵ�һ��Button" + "\n" + "test");
        
    }
}
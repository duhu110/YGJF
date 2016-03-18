package com.qhduhu.ygjf.activity;

import com.qhduhu.ygjf.R;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DialActivity extends Activity {
	TextView tvname, tvtel, tvmail, tvdept;
	Button btdial, bttex;
	EditText editText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dial);
		Intent intent = getIntent();
		String name = intent.getStringExtra("name");
		Log.d("nameget>>>>>>>>>>>>", name);
		getActionBar().setDisplayShowTitleEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_tab_bg));
		tvdept = (TextView) findViewById(R.id.dial_dept);
		tvmail = (TextView) findViewById(R.id.dial_mail);
		tvname = (TextView) findViewById(R.id.dial_name);
		tvtel = (TextView) findViewById(R.id.dial_tel);
		btdial = (Button) findViewById(R.id.dial_btn_dial);
		bttex = (Button) findViewById(R.id.dial_btn_tex);
		editText=(EditText) findViewById(R.id.callnumb);
		tvname.setText(name);
		btdial.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String phone_number = editText.getText().toString();
	            
		           phone_number = phone_number.trim();//ɾ���ַ����ײ���β���Ŀո�
		            
		           if(phone_number != null && !phone_number.equals(""))
		           {
		                
		                
		               //����ϵͳ�Ĳ��ŷ���ʵ�ֵ绰������
		               //��װһ������绰��intent�����ҽ��绰�����װ��һ��Uri������
		               Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+phone_number));
		               DialActivity.this.startActivity(intent);//�ڲ���
		           }
		       }
		});
		bttex.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				String phone_number = editText.getText().toString();
	            
		           phone_number = phone_number.trim();//ɾ���ַ����ײ���β���Ŀո�
		            
		           if(phone_number != null && !phone_number.equals(""))
		           {
		                
		                
		               //����ϵͳ�Ĳ��ŷ���ʵ�ֵ绰������
		               //��װһ������绰��intent�����ҽ��绰�����װ��һ��Uri������
		               Intent intent = new Intent(Intent.ACTION_SENDTO,Uri.parse("smsto:"+phone_number));
		               DialActivity.this.startActivity(intent);//�ڲ���
		           }
			}
		});
	}
}

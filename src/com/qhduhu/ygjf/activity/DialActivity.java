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
	            
		           phone_number = phone_number.trim();//删除字符串首部和尾部的空格
		            
		           if(phone_number != null && !phone_number.equals(""))
		           {
		                
		                
		               //调用系统的拨号服务实现电话拨打功能
		               //封装一个拨打电话的intent，并且将电话号码包装成一个Uri对象传入
		               Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+phone_number));
		               DialActivity.this.startActivity(intent);//内部类
		           }
		       }
		});
		bttex.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				String phone_number = editText.getText().toString();
	            
		           phone_number = phone_number.trim();//删除字符串首部和尾部的空格
		            
		           if(phone_number != null && !phone_number.equals(""))
		           {
		                
		                
		               //调用系统的拨号服务实现电话拨打功能
		               //封装一个拨打电话的intent，并且将电话号码包装成一个Uri对象传入
		               Intent intent = new Intent(Intent.ACTION_SENDTO,Uri.parse("smsto:"+phone_number));
		               DialActivity.this.startActivity(intent);//内部类
		           }
			}
		});
	}
}

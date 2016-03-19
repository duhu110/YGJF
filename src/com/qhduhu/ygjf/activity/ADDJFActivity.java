package com.qhduhu.ygjf.activity;

import java.util.ArrayList;

import com.qhduhu.ygjf.R;
import com.qhduhu.ygjf.db.DBManager;
import com.qhduhu.ygjf.entity.TXLEntity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ADDJFActivity extends Activity {
	
	private TextView tvtest;
	private Button btsubmit;
	private DBManager db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addjf);
		initactiongbar();
		tvtest=(TextView) findViewById(R.id.add_testtv);
		btsubmit=(Button) findViewById(R.id.addjf_confirm);
		Intent intent = getIntent();
		String string = intent.getStringExtra("addnew");
		tvtest.setText(string);
		db=new DBManager(this);
		btsubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
	
							
			//	db.addtxl(txlEntities);
				Log.d(null, "测试数据增加成功");
			}
		});
		
	}

	private void initactiongbar() {
		getActionBar().setDisplayShowTitleEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_tab_bg));
		
	}

	
	

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.addj, menu);
//		return true;
//	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
        case android.R.id.home:// 点击返回图标事件
            this.finish();
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}

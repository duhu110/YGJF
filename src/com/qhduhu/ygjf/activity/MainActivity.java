package com.qhduhu.ygjf.activity;

import java.lang.reflect.Field;
import java.util.List;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.qhduhu.ygjf.R;
import com.qhduhu.ygjf.adapter.MainFragmentAdapter;
import com.qhduhu.ygjf.db.DBManager;
import com.qhduhu.ygjf.entity.TXLEntity;
import com.qhduhu.ygjf.service.GETTXL;
import com.viewpagerindicator.TabPageIndicator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ViewConfiguration;

public class MainActivity extends SherlockFragmentActivity {
	private MainFragmentAdapter adapter;
	private GETTXL gettxltxl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initActionBar();
		setOverflowShowingAlways();
		initViews();
	}
	private void initActionBar() {
		getSupportActionBar().setDisplayShowCustomEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(false);
		getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_tab_bg));
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.more_menu, menu);
		
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
        case R.id.menu_about:// 点击返回图标事件
            startActivity(new Intent(this, AboutActivity.class));
        case R.id.menu_refresh:
        	Log.d("TAGGGGGGGGGGG", "按钮成功");
        	gettxltxl = new GETTXL();
        	
        	try {
				List<TXLEntity> txlEntities = gettxltxl.getTXL();
				Log.d("taggggggggggg", "获取数据成功");
				DBManager dbManager = new DBManager(this);
				dbManager.addtxl(txlEntities);
			} catch (Exception e) {
				e.printStackTrace();
			}
        default:
            return super.onOptionsItemSelected(item);
        }
	}
	private void setOverflowShowingAlways() {
		try { 
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
			menuKeyField.setAccessible(true);
			menuKeyField.setBoolean(config, false);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	private void initViews() {
		adapter = new MainFragmentAdapter(getSupportFragmentManager(),this);
		ViewPager pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(adapter);
		TabPageIndicator indicator  = (TabPageIndicator) findViewById(R.id.indicator);
		indicator.setViewPager(pager);
	}

}

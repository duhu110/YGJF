package com.qhduhu.ygjf.activity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.qhduhu.ygjf.R;
import com.qhduhu.ygjf.adapter.MainFragmentAdapter;
import com.qhduhu.ygjf.db.DBManager;
import com.qhduhu.ygjf.entity.TXLEntity;
import com.qhduhu.ygjf.service.GetTXLListService;
import com.qhduhu.ygjf.service.GetTXLListServiceIMPL;
import com.viewpagerindicator.TabPageIndicator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ViewConfiguration;

public class MainActivity extends SherlockFragmentActivity {
	private MainFragmentAdapter adapter;
	private GetTXLListService gettxllist = new GetTXLListServiceIMPL();
	private DBManager db ;
	private String TAG = "MainActivity++++++++TAG";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initActionBar();
		setOverflowShowingAlways();
		initViews();
		GettxlThread thread = new GettxlThread();
		thread.start();
	}
	class GettxlThread extends Thread{
		List<TXLEntity> list = new ArrayList<TXLEntity>();
		@Override
		public void run() {
			Log.d(TAG, "thread success");
			try {
				
				list = gettxllist.getTXLList();
				db = new DBManager(getApplicationContext());
				Log.d(TAG, list.get(3).txl_name);
				db.DeleteTXL();
				db.addtxl(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
            
//        case R.id.menu_refresh:
//        	Log.d("TAGGGGGGGGGGG", "按钮成功");
//        	new Thread(new Runnable() {
//    			@Override
//				public void run() {
//    				try {
//						txlEntities = gettxllist.getTXLList();
//						Log.d("taggggggggggg", "获取数据成功");
//					} catch (Exception e) {
//						e.printStackTrace();
//						Log.d("taggggggggggggggggggg", "获取数据失败");
//					}
//					}
//				}
//			).run();
//			System.out.println(txlEntities);
//			db= new DBManager(this);
//        	db.addtxl(txlEntities);
//			db.closeDB();
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

package com.qhduhu.ygjf.activity;

import java.util.ArrayList;

import com.qhduhu.ygjf.R;
import com.qhduhu.ygjf.adapter.GridImageAdapter;
import com.qhduhu.ygjf.adapter.ImageUtils;
import com.qhduhu.ygjf.adapter.WrapHeightGridView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ADDJFActivity extends Activity implements OnClickListener, OnItemClickListener {
	private EditText descrp;
	private Button photo, submit;
	private WrapHeightGridView imggv;
	private ArrayList<Uri> imgUris = new ArrayList<Uri>();
	private GridImageAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addjf);
		initactiongbar();
		Intent intent = getIntent();
		String string = intent.getStringExtra("addnew");
		Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
		descrp = (EditText) findViewById(R.id.addjf_etwhat);
		photo = (Button) findViewById(R.id.add_photo);
		submit = (Button) findViewById(R.id.add_submit);
		imggv = (WrapHeightGridView) findViewById(R.id.add_imggv);
		adapter = new GridImageAdapter(this, imgUris, imggv);
		imggv.setAdapter(adapter);
		imggv.setOnItemClickListener(this);
		photo.setOnClickListener(this);

	}

	private void initactiongbar() {
		getActionBar().setDisplayShowTitleEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_tab_bg));

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case ImageUtils.REQUEST_CODE_FROM_ALBUM:
			if (resultCode == RESULT_CANCELED) {
				return;
			}
			Uri imageUri = data.getData();
			imgUris.add(imageUri);
			updateImgs();
			break;
		case ImageUtils.REQUEST_CODE_FROM_CAMERA:
			if (resultCode == RESULT_CANCELED) {
				ImageUtils.DeleteImageUri(this,ImageUtils.imageUriFromCamera);
			}else {
			Uri imageUriCamera = ImageUtils.imageUriFromCamera;
			imgUris.add(imageUriCamera);
			updateImgs();
			}
			break;
		default:
			break;
		}
	}


	private void updateImgs() {
		if (imgUris.size() > 0) {
			imggv.setVisibility(View.VISIBLE);
			adapter.notifyDataSetChanged();
		}else {
			imggv.setVisibility(View.GONE);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_photo:
			ImageUtils.ShowImagePickDialog(this);
			break;
		case R.id.add_submit:

			break;
		default:
			break;
		}
	}
	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.addj, menu);
	// return true;
	// }
	
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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Object itemAdapter = parent.getAdapter();
		if (itemAdapter instanceof GridImageAdapter) {
			if (position == adapter.getCount() - 1) {
				ImageUtils.ShowImagePickDialog(this);
			}
		}
	}
}

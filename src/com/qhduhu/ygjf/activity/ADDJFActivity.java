package com.qhduhu.ygjf.activity;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.qhduhu.ygjf.R;
import com.qhduhu.ygjf.adapter.GridImageAdapter;
import com.qhduhu.ygjf.adapter.ImageUtils;
import com.qhduhu.ygjf.adapter.WrapHeightGridView;
import com.qhduhu.ygjf.db.DBManager;
import com.qhduhu.ygjf.entity.JfEntity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ADDJFActivity extends Activity implements OnClickListener, OnItemClickListener {
	private EditText descrp, ettype;
	private TextView tvtype;
	private int typeCode = 0;
	private Button photo, submit;
	private WrapHeightGridView imggv;
	private ArrayList<Uri> imgUris = new ArrayList<Uri>();
	private GridImageAdapter adapter;
	DBManager db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addjf);
		Intent intent = getIntent();
		typeCode = intent.getIntExtra("typeCode", 0);
		initactiongbar();
		initview();
		descrp = (EditText) findViewById(R.id.addjf_etwhat);
		photo = (Button) findViewById(R.id.add_photo);
		submit = (Button) findViewById(R.id.add_submit);
		tvtype = (TextView) findViewById(R.id.add_tvtype);
		ettype = (EditText) findViewById(R.id.add_ettype);
		imggv = (WrapHeightGridView) findViewById(R.id.add_imggv);
		adapter = new GridImageAdapter(this, imgUris, imggv);
		imggv.setAdapter(adapter);
		imggv.setOnItemClickListener(this);
		photo.setOnClickListener(this);
		submit.setOnClickListener(this);

	}

	private void initview() {
		descrp = (EditText) findViewById(R.id.addjf_etwhat);
		photo = (Button) findViewById(R.id.add_photo);
		submit = (Button) findViewById(R.id.add_submit);
		tvtype = (TextView) findViewById(R.id.add_tvtype);
		ettype = (EditText) findViewById(R.id.add_ettype);
		imggv = (WrapHeightGridView) findViewById(R.id.add_imggv);
		switch (typeCode) {
		case 1:
			tvtype.setText("读的书名");
			break;
		case 2:
			tvtype.setText("运动类型");
			break;
		case 3:
			tvtype.setText("棋牌类型");
			break;
		case 4:
			tvtype.setText("程序名称");
			break;

		default:
			break;
		}
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
				ImageUtils.DeleteImageUri(this, ImageUtils.imageUriFromCamera);
			} else {
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
		} else {
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
			db = new DBManager(this);
			byte[] pic1, pic2, pic3, pic4, pic5, pic6, pic7, pic8, pic9;
			JfEntity entity = new JfEntity();
			List<JfEntity> entities = new ArrayList<JfEntity>();
			if (adapter.getItem(0) != null) {
				Uri uri1 = adapter.getItem(0);
				Bitmap bitmap1 = GetBitmapFromUri(uri1);
				pic1 = Bitmap2Bytes(bitmap1);
				entity.setJf_pic1(pic1);
			}
			if (adapter.getItem(1) != null) {
				Uri uri2 = adapter.getItem(1);
				Bitmap bitmap2 = GetBitmapFromUri(uri2);
				pic2 = Bitmap2Bytes(bitmap2);
				entity.setJf_pic2(pic2);
			}
			if (adapter.getItem(2) != null) {
				Uri uri3 = adapter.getItem(2);
				Bitmap bitmap3 = GetBitmapFromUri(uri3);
				pic3 = Bitmap2Bytes(bitmap3);
				entity.setJf_pic3(pic3);
			}
			if (adapter.getItem(3) != null) {
				Uri uri4 = adapter.getItem(3);
				Bitmap bitmap4 = GetBitmapFromUri(uri4);
				pic4 = Bitmap2Bytes(bitmap4);
				entity.setJf_pic4(pic4);
			}
			if (adapter.getItem(4) != null) {
				Uri uri5 = adapter.getItem(4);
				Bitmap bitmap5 = GetBitmapFromUri(uri5);
				pic5 = Bitmap2Bytes(bitmap5);
				entity.setJf_pic5(pic5);
			}
			if (adapter.getItem(5) != null) {
				Uri uri6 = adapter.getItem(5);
				Bitmap bitmap6 = GetBitmapFromUri(uri6);
				pic6 = Bitmap2Bytes(bitmap6);
				entity.setJf_pic6(pic6);
			}
			if (adapter.getItem(6) != null) {
				Uri uri7 = adapter.getItem(6);
				Bitmap bitmap7 = GetBitmapFromUri(uri7);
				pic7 = Bitmap2Bytes(bitmap7);
				entity.setJf_pic7(pic7);
			}
			if (adapter.getItem(7) != null) {
				Uri uri8 = adapter.getItem(7);
				Bitmap bitmap8 = GetBitmapFromUri(uri8);
				pic8 = Bitmap2Bytes(bitmap8);
				entity.setJf_pic8(pic8);
			}
			if (adapter.getItem(8) != null) {
				Uri uri9 = adapter.getItem(8);
				Bitmap bitmap9 = GetBitmapFromUri(uri9);
				pic9 = Bitmap2Bytes(bitmap9);
				entity.setJf_pic9(pic9);
			}
			entity.setJf_descrp(descrp.getText().toString());
			entity.setJf_type(tvtype.getText().toString());
			entity.setJf_typedescrp(ettype.getText().toString());
			entity.setJf(3);
			entities.add(entity);
			db.add(entities);
			break;
		default:
			break;
		}
	}

	private byte[] Bitmap2Bytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}

	private Bitmap GetBitmapFromUri(Uri uri) {
		Bitmap bitmap;
		if (uri == null) {
			return null;
		}
		try {
			bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
			return bitmap;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
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

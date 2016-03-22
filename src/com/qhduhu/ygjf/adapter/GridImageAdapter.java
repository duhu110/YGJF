package com.qhduhu.ygjf.adapter;

import java.util.ArrayList;
import com.qhduhu.ygjf.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class GridImageAdapter extends BaseAdapter{
	private Context context;
	private ArrayList<Uri> datas;
	private GridView gv;

	public GridImageAdapter(Context context,ArrayList<Uri> datas,GridView gv) {
		this.context=context;
		this.datas= datas;
		this.gv=gv;
	}

	@Override
	public int getCount() {
		return datas.size() > 0 ? datas.size() + 1 : 0;
	}

	@Override
	public Uri getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@SuppressLint("NewApi")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.item_grid_image, null);
			holder.iv_image = (ImageView) convertView.findViewById(R.id.grid_img_iv);
			holder.iv_image_delete = (ImageView) convertView.findViewById(R.id.grid_img_delete);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		int horizontalSpacing = gv.getHorizontalSpacing();
		int width = (gv.getWidth() - horizontalSpacing * 2
				- gv.getPaddingLeft() - gv.getPaddingRight())/3;
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, width);
		holder.iv_image.setLayoutParams(params);
		if (position < getCount() - 1) {
			Uri item = getItem(position);
			holder.iv_image.setImageURI(item);
			holder.iv_image_delete.setVisibility(View.VISIBLE);
			holder.iv_image_delete.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					datas.remove(position);
					notifyDataSetChanged();
				}
			});
		}else {
			holder.iv_image.setImageResource(R.drawable.plus_squaret);
			holder.iv_image_delete.setVisibility(View.GONE);
		}
		return convertView;
	}
	public static class ViewHolder{
		ImageView iv_image,iv_image_delete;
	}
}

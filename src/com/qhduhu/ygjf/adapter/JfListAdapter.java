package com.qhduhu.ygjf.adapter;

import java.util.List;

import com.qhduhu.ygjf.R;
import com.qhduhu.ygjf.entity.JfEntity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class JfListAdapter extends BaseAdapter {
	private List<JfEntity> list = null;
	private Context mContext;

	public JfListAdapter(Context mContext, List<JfEntity> list) {
		this.mContext = mContext;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	public void updateListView(List<JfEntity> list){
		this.list = list;
		notifyDataSetChanged();
	}
	final static class ViewHolder {
		TextView tvYgName;
		TextView tvType;
		TextView tvDscrp;
		ImageView img1,img2;
	}
	@SuppressLint("InflateParams") @Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (view == null) {
			viewHolder = new ViewHolder();
			view = LayoutInflater.from(mContext).inflate(R.layout.contact_item, null);
			viewHolder.tvYgName = (TextView) view.findViewById(R.id.item_jf_name);
			viewHolder.tvType = (TextView) view.findViewById(R.id.item_jf_type);
			viewHolder.tvDscrp = (TextView) view.findViewById(R.id.item_jf_dscrp);
			viewHolder.img1 = (ImageView) view.findViewById(R.id.item_jf_img1);
			viewHolder.img2 = (ImageView) view.findViewById(R.id.item_jf_img2);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.tvYgName.setText(this.list.get(position).getYg_name());
		String string = this.list.get(position).getJf_type() + this.list.get(position).getJf_typedescrp();
		viewHolder.tvType.setText(string);
		viewHolder.tvDscrp.setText(this.list.get(position).getJf_descrp());
		Bitmap bitmap1,bitmap2;
		bitmap1 = Bytes2Bimap(this.list.get(position).getJf_pic1());
		bitmap2 = Bytes2Bimap(this.list.get(position).getJf_pic2());
		viewHolder.img1.setImageBitmap(bitmap1);
		viewHolder.img2.setImageBitmap(bitmap2);
		
		return view;
	}
	public Bitmap Bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }
}

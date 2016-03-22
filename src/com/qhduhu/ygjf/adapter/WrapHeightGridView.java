package com.qhduhu.ygjf.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class WrapHeightGridView extends GridView{

	public WrapHeightGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public WrapHeightGridView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public WrapHeightGridView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int heightspec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST );
		super.onMeasure(widthMeasureSpec, heightspec);
	}

}

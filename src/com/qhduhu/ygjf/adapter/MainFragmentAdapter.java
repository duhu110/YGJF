package com.qhduhu.ygjf.adapter;

import com.qhduhu.ygjf.fragment.NewJFFragment;
import com.qhduhu.ygjf.fragment.PYQFragment;
import com.qhduhu.ygjf.fragment.TXLFragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainFragmentAdapter extends FragmentPagerAdapter {
	private String[] titles = new String[] { "新增积分", "工友圈", "通讯录", "更多" };

	public MainFragmentAdapter(FragmentManager fm) {
		super(fm);
			}

	public MainFragmentAdapter(FragmentManager fm, Context context) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		switch (arg0) {
		case 0:
			return NewJFFragment.newInstance();
		case 1:
			return PYQFragment.newInstance();
		case 2:
			return TXLFragment.newInstance();
		case 3:
			return TXLFragment.newInstance();
		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return titles.length;
	}

	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return POSITION_NONE;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		return titles[position];
	}

}

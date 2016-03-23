package com.qhduhu.ygjf.fragment;

import java.util.List;

import com.actionbarsherlock.app.SherlockFragment;
import com.qhduhu.ygjf.R;
import com.qhduhu.ygjf.adapter.JfListAdapter;
import com.qhduhu.ygjf.db.DBManager;
import com.qhduhu.ygjf.entity.JfEntity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class PYQFragment extends SherlockFragment implements OnRefreshListener {
	private ListView listView;
	private JfListAdapter adapter;
	private static final int REFRESH_COMPLETE = 0X110;
	private SwipeRefreshLayout mSwipeLayout;
	private DBManager db;
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case REFRESH_COMPLETE:
				getData();
				adapter.notifyDataSetChanged();
				mSwipeLayout.setRefreshing(false);
				break;

			}
		};
	};

	public static PYQFragment newInstance() {
		PYQFragment fragment = new PYQFragment();
		return fragment;
	}

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_pyq, null);
		listView = (ListView) view.findViewById(R.id.pyq_listview);
		
		getData();
		listView.setAdapter(adapter);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mSwipeLayout = (SwipeRefreshLayout) getActivity().findViewById(R.id.id_swipe_ly);
		mSwipeLayout.setColorScheme(android.R.color.holo_green_dark, android.R.color.holo_green_light,
				android.R.color.holo_orange_light, android.R.color.holo_red_light);
		mSwipeLayout.setOnRefreshListener(this);
	}

	private void getData() {
		List<JfEntity> list;
		db = new DBManager(getActivity());
		list = db.query();
		db.closeDB();
		adapter = new JfListAdapter(getActivity(), list);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putString(null, "fixbug");
		super.onSaveInstanceState(outState);
	}

	public void onRefresh() {
		// Log.e("xxx", Thread.currentThread().getName());
		// UI Thread
		mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);

	}
}
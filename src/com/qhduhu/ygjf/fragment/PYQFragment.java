package com.qhduhu.ygjf.fragment;

import java.util.List;

import com.actionbarsherlock.app.SherlockFragment;
import com.qhduhu.ygjf.R;
import com.qhduhu.ygjf.adapter.JfListAdapter;
import com.qhduhu.ygjf.db.DBManager;
import com.qhduhu.ygjf.entity.JfEntity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class PYQFragment extends SherlockFragment {
	private ListView listView;
	private JfListAdapter adapter;
	DBManager db;

	public static PYQFragment newInstance(){
		PYQFragment fragment = new PYQFragment();
		return fragment;
	}

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_pyq, null);
		listView=(ListView) view.findViewById(R.id.pyq_listview);
		List<JfEntity> list;
		db = new DBManager(getActivity());
		list = db.query();
		db.closeDB();
		adapter = new JfListAdapter(getActivity(), list);
		listView.setAdapter(adapter);
		return view;
	}

	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putString(null, "fixbug");
		super.onSaveInstanceState(outState);
	}
}
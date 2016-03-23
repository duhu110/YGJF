package com.qhduhu.ygjf.fragment;

import com.actionbarsherlock.app.SherlockFragment;
import com.qhduhu.ygjf.R;
import com.qhduhu.ygjf.activity.ADDJFActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class NewJFFragment extends SherlockFragment implements OnClickListener {
	private int readTypeCode = 1;
	private int sportTypeCode = 2;
	private int chessTypeCode = 3;
	private int devTypeCode = 4;
	RelativeLayout read,sport,dev,chess;
	public static NewJFFragment newInstance(){
		NewJFFragment fragment = new NewJFFragment();
		return fragment;
	}
	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_newjf, null);
		

		return view;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		read=(RelativeLayout) getActivity().findViewById(R.id.new_readbook);
		sport=(RelativeLayout) getActivity().findViewById(R.id.new_sport);
		dev=(RelativeLayout) getActivity().findViewById(R.id.new_dev);
		chess=(RelativeLayout) getActivity().findViewById(R.id.new_chess);
		read.setOnClickListener(this);
		sport.setOnClickListener(this);
		dev.setOnClickListener(this);
		chess.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.new_readbook:
			startActivity(new Intent(getActivity(), ADDJFActivity.class).putExtra("typeCode", readTypeCode));
			break;
		case R.id.new_sport:
			startActivity(new Intent(getActivity(), ADDJFActivity.class).putExtra("typeCode", sportTypeCode));
			break;
		case R.id.new_dev:
				startActivity(new Intent(getActivity(), ADDJFActivity.class).putExtra("typeCode", devTypeCode));
		
		break;
		case R.id.new_chess:
			startActivity(new Intent(getActivity(), ADDJFActivity.class).putExtra("typeCode", chessTypeCode));
			break;
		}
		
	}
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		outState.putString("nullpoiter", "fixbug");
		super.onSaveInstanceState(outState);
	}

}

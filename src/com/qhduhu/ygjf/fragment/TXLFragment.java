package com.qhduhu.ygjf.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.qhduhu.ygjf.R;
import com.qhduhu.ygjf.db.DBManager;
import com.qhduhu.ygjf.entity.TXLEntity;
import android.annotation.SuppressLint;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class TXLFragment extends SherlockFragment implements OnItemClickListener, OnItemLongClickListener {
	private ListView listView;
	private DBManager dbManager;
	private SimpleAdapter adapter;
	private EditText sarch;

	public static TXLFragment newInstance() {
		TXLFragment fragment = new TXLFragment();
		return fragment;
	}

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_txl, null);
		listView = (ListView) view.findViewById(R.id.txl_lv);
		dbManager = new DBManager(getActivity());
		adapter = new SimpleAdapter(getActivity(), getData(), R.layout.contact_item,
				new String[] { "name", "tel", "dept" },
				new int[] { R.id.txl_item_name, R.id.txl_item_tel, R.id.txl_item_dept });
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		listView.setOnItemLongClickListener(this);
		sarch=(EditText) view.findViewById(R.id.txl_serchet);
		
		return view;
	}

	private List<Map<String, Object>> getData() {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<TXLEntity> txlEntities = dbManager.querytxl();
		dbManager.closeDB();

		for (TXLEntity txlEntity : txlEntities) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", txlEntity.txl_name);
			map.put("tel", txlEntity.txl_tel);
			map.put("dept", txlEntity.txl_dept);
			map.put("mail", txlEntity.txl_mail);
			Log.d("tagggggggggggggg", map.toString());
			list.add(map);
		}

		return list;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putString(null, "fixbug");
		super.onSaveInstanceState(outState);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		Map<String, Object> map = new HashMap<String, Object>();
		map = (Map<String, Object>) this.adapter.getItem(arg2);
		Log.d("tagggggggggggggg", map.toString());

		String phone_number = map.get("tel").toString();

		Log.d("tagggggggggggggggg", phone_number);
		phone_number = phone_number.trim();// ɾ���ַ����ײ���β���Ŀո�
		if (phone_number != null && !phone_number.equals("")) {
			// ����ϵͳ�Ĳ��ŷ���ʵ�ֵ绰������
			// ��װһ������绰��intent�����ҽ��绰�����װ��һ��Uri������
			Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone_number));
			getActivity().startActivity(intent);// �ڲ���
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map = (Map<String, Object>) this.adapter.getItem(position);
		Log.d("tagggggggggggggg", map.toString());
		final String string = map.get("name").toString();
		listView.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
			@Override
			public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
				menu.setHeaderTitle(string);
				menu.add(0, 1, Menu.NONE, "���Ͷ���");
				menu.add(0, 2, Menu.NONE, "���Ƶ����а�");
				menu.add(0, 3, Menu.NONE, "�����ʼ�");
				menu.add(0, 4, Menu.NONE, "ȡ��");
			}
		});

		return false;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo adapterContextMenuInfo = (AdapterContextMenuInfo) item.getMenuInfo();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map = (Map<String, Object>) this.adapter.getItem(adapterContextMenuInfo.position);
		Log.d("tagggggggggggggg", map.toString());
		String name = map.get("name").toString();
		String phone_number = map.get("tel").toString();
		String dept = map.get("dept").toString();
		String mail = map.get("mail").toString();
		
		
		switch (item.getItemId()) {
		case 1:
												
	           phone_number = phone_number.trim();//ɾ���ַ����ײ���β���Ŀո�
	           if(phone_number != null && !phone_number.equals(""))
	           {
	               //����ϵͳ�Ĳ��ŷ���ʵ�ֵ绰������
	               //��װһ������绰��intent�����ҽ��绰�����װ��һ��Uri������
	               Intent intent = new Intent(Intent.ACTION_SENDTO,Uri.parse("smsto:"+phone_number));
	               getActivity().startActivity(intent);//�ڲ���
	           }
			break;
		case 2:
			String string = "����:"+name+";�绰:"+phone_number+";����:"+dept+"������Ϣ���ԡ��ҵĹ��ᡱ���뾡�찲װ��";
			ClipboardManager clipboardManager =
					(ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
			clipboardManager.setText(string);
			Toast.makeText(getActivity(), name+"��Ϣ���Ƴɹ���", Toast.LENGTH_SHORT).show();
			break;
		case 3:
			//����Intent����  
			Intent intent = new Intent();  
			//���ö�����  
			intent.setAction(Intent.ACTION_SEND);  
			//���öԷ��ʼ���ַ  
			intent.putExtra(Intent.EXTRA_EMAIL, new String[]    
			{ mail,mail });   
			//���ñ�������  
			intent.putExtra(Intent.EXTRA_SUBJECT, "�ҵĹ���");  
			//�����ʼ��ı�����  
			intent.putExtra(Intent.EXTRA_TEXT, "�����ҵĹ���APP");  
			//����һ���µ�ACTIVITY,"Sending mail..."�����������   
			// ACTIVITY�ĵȴ�ʱ��ʱ����ʾ������  
			startActivity(Intent.createChooser(intent, "Sending    mail..."));          
			break;
		case 4:
			break;

		default:
			break;
		}return super.onContextItemSelected(item);
}
}

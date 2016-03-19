package com.qhduhu.ygjf.db;

import java.util.ArrayList;
import java.util.List;

import com.qhduhu.ygjf.entity.Jf;
import com.qhduhu.ygjf.entity.TXLEntity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBManager {
	private DBHelper helper;  
    private SQLiteDatabase db;
	
    public DBManager(Context context) {
    	helper = new DBHelper(context);  
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);  
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里  
        db = helper.getWritableDatabase();  
	}
    public void add(List<Jf> jfs) {
    	db.beginTransaction();  //开始事务  
        try {  
            for (Jf jf : jfs) {  
                db.execSQL("INSERT INTO jf VALUES(null, ?, ?, ?, ?)",
                		new Object[]{jf.yg_name,jf.jf_descrp,jf.jf_pic,jf.jf});  
            }  
            db.setTransactionSuccessful();  //设置事务成功完成  
        } finally {  
            db.endTransaction();    //结束事务  
        }  
	}
    public void inserttxl(String name,String tel,String dept,String mail) {
		db.beginTransaction();
		try {
			String sql = "INSERT INTO txl VALUES(null, "+name+","+tel+" ,"+dept+" ,"+mail+" )";
			Log.d("DBManager11111", sql);
			db.execSQL(sql);
			db.setTransactionSuccessful();
		}  finally {  
            db.endTransaction();    //结束事务  
        }  
	}
    public void addtxl(List<TXLEntity> txlentities) {
    	db.beginTransaction();  //开始事务  
        try {  
            for (TXLEntity txlEntity : txlentities) {  
                db.execSQL("INSERT INTO txl VALUES(null, ?, ?, ?, ?)",
                		new Object[]{txlEntity.txl_name,txlEntity.txl_dept,txlEntity.txl_tel,txlEntity.txl_mail});  
            }  
            db.setTransactionSuccessful();  //设置事务成功完成  
        } finally {  
            db.endTransaction();    //结束事务  
        }  
	}
     public void DeleteTXL() {
    	 db.beginTransaction();  //开始事务  
         try {  
             
                 db.execSQL("DELETE FROM txl");  
              
             db.setTransactionSuccessful();  //设置事务成功完成  
         } finally {  
             db.endTransaction();    //结束事务  
         }  
	}
    
//    public void updateAge(Person person) {  
//        ContentValues cv = new ContentValues();  
//        cv.put("age", person.age);  
//        db.update("person", cv, "name = ?", new String[]{person.name});  
//    }
//    public void deleteOldPerson(Person person) {  
//        db.delete("person", "age >= ?", new String[]{String.valueOf(person.age)});  
//    }  
    public List<Jf> query() {  
        ArrayList<Jf> jfs = new ArrayList<Jf>();  
        Cursor c = queryTheCursor();  
        while (c.moveToNext()) {  
            Jf jf = new Jf();  
            jf._id = c.getInt(c.getColumnIndex("_id"));  
            jf.yg_name = c.getString(c.getColumnIndex("yg_name"));  
            jf.jf_descrp = c.getString(c.getColumnIndex("jf_descrp"));
            jf.jf_pic = c.getBlob(c.getColumnIndex("jf_pic"));
            jf.jf = c.getInt(c.getColumnIndex("jf"));  
            jfs.add(jf);  
        }  
        c.close();  
        return jfs;  
    }
    public List<TXLEntity> querytxl() {  
        ArrayList<TXLEntity> txlEntities = new ArrayList<TXLEntity>();  
        Cursor c = queryTheCursorTXL();  
        while (c.moveToNext()) {  
            TXLEntity txlEntity = new TXLEntity();  
            txlEntity.txl_name = c.getString(c.getColumnIndex("txl_name"));  
            txlEntity.txl_tel = c.getString(c.getColumnIndex("txl_tel"));  
            txlEntity.txl_dept = c.getString(c.getColumnIndex("txl_dept"));  
            txlEntity.txl_mail = c.getString(c.getColumnIndex("txl_mail"));  
            txlEntities.add(txlEntity);  
        }  
        c.close();  
        return txlEntities;  
    }
    
    
    
    public Cursor queryTheCursor() {
		// TODO Auto-generated method stub
		 Cursor c = db.rawQuery("SELECT * FROM jf", null);  
	     return c;  
	}  
    public Cursor queryTheCursorTXL() {
		 Cursor c = db.rawQuery("SELECT * FROM txl", null);  
	     return c;  
	} 
	public void closeDB() {  
        db.close();  
    }  
    

}

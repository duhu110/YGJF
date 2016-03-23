package com.qhduhu.ygjf.db;

import java.util.ArrayList;
import java.util.List;

import com.qhduhu.ygjf.entity.JfEntity;
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
    public void add(List<JfEntity> jfs) {
    	db.beginTransaction();  //开始事务  
        try {  
//        	"(_id INTEGER PRIMARY KEY AUTOINCREMENT, yg_name VARCHAR,"
//                    + " jf_descrp VARCHAR,jf_type VARCHAR,jf_typedescrp VARCHAR,"
//                    + "jf_pic1 BLOB,jf_pic2 BLOB,jf_pic3 BLOB,jf_pic4 BLOB,"
//                    + "jf_pic5 BLOB,jf_pic6 BLOB,jf_pic7 BLOB,jf_pic8 BLOB,"
//                    + "jf_pic9 BLOB,jf INTEGER)");
            for (JfEntity jf : jfs) {  
                db.execSQL("INSERT INTO jf VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                		new Object[]{jf.getYg_name(),jf.getJf_descrp(),jf.getJf_type(),
                				jf.getJf_typedescrp(),jf.getJf_pic1(),
                				jf.getJf_pic2(),jf.getJf_pic3(),jf.getJf_pic4(),
                				jf.getJf_pic5(),jf.getJf_pic6(),jf.getJf_pic7(),
                				jf.getJf_pic8(),jf.getJf_pic9(),jf.getJf()});  
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
    public List<JfEntity> query() {  
        ArrayList<JfEntity> jfs = new ArrayList<JfEntity>();  
        Cursor c = queryTheCursor();  
        while (c.moveToNext()) {  
            JfEntity jf = new JfEntity();  
            jf.set_id(c.getInt(c.getColumnIndex("_id")));  
            jf.setYg_name(c.getString(c.getColumnIndex("yg_name")));  
            jf.setJf_descrp(c.getString(c.getColumnIndex("jf_descrp")));
            jf.setJf_type(c.getString(c.getColumnIndex("jf_type")));
            jf.setJf_typedescrp(c.getString(c.getColumnIndex("jf_typedescrp")));
            jf.setJf_pic1(c.getBlob(c.getColumnIndex("jf_pic1")));
            jf.setJf_pic2(c.getBlob(c.getColumnIndex("jf_pic2")));
            jf.setJf_pic3(c.getBlob(c.getColumnIndex("jf_pic3")));
            jf.setJf_pic4(c.getBlob(c.getColumnIndex("jf_pic4")));
            jf.setJf_pic5(c.getBlob(c.getColumnIndex("jf_pic5")));
            jf.setJf_pic6(c.getBlob(c.getColumnIndex("jf_pic6")));
            jf.setJf_pic7(c.getBlob(c.getColumnIndex("jf_pic7")));
            jf.setJf_pic8(c.getBlob(c.getColumnIndex("jf_pic8")));
            jf.setJf_pic9(c.getBlob(c.getColumnIndex("jf_pic9")));
            jf.setJf(c.getInt(c.getColumnIndex("jf")));  
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

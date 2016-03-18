package com.qhduhu.ygjf.entity;

public class TXLEntity {
    public String txl_name;  
    public String txl_dept;  
    public String txl_tel;  
    public String txl_mail;
		
    public TXLEntity() {
		// TODO Auto-generated constructor stub
	}
    public TXLEntity(String txl_name,String txl_tel,  String txl_dept, String txl_mail) {
		this.txl_name = txl_name;
		this.txl_dept = txl_dept;
		this.txl_tel = txl_tel;
		this.txl_mail = txl_mail;
	}  
}

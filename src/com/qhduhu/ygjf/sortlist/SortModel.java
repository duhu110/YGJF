package com.qhduhu.ygjf.sortlist;

public class SortModel {

	private String sortLetters;  //��ʾ����ƴ��������ĸ
	private String name;   //��ʾ������
	private String tel;   //��ʾ������
	private String dept;   //��ʾ������
	private String mail;   //��ʾ������
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSortLetters() {
		return sortLetters;
	}
	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}
}

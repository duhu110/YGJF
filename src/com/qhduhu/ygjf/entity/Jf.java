package com.qhduhu.ygjf.entity;

public class Jf {
	 	public int _id;  
	    public String yg_name;  
	    public String jf_descrp;  
	    public byte[] jf_pic;
	    public int jf;
	    
	    public Jf() {
			
		}
		public Jf( String yg_name, String jf_descrp, byte[] jf_pic, int jf) {
			this.yg_name = yg_name;
			this.jf_descrp = jf_descrp;
			this.jf_pic = jf_pic;
			this.jf = jf;
		}
	    
}

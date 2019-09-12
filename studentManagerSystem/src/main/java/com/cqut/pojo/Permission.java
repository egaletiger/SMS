package com.cqut.pojo;

public class Permission {
	private int id;
	private String pname;
	
	public Permission() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Permission(int id, String pname) {
		super();
		this.id = id;
		this.pname = pname;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}

	@Override
	public String toString() {
		return "Permisson [id=" + id + ", pname=" + pname + "]";
	}
}

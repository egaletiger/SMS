package com.cqut.pojo;


public class Teacher extends User{
	private Page studentPage;
	private String institute;//所在学院
	public String getInstitute() {
		return institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
	}
	public Page getStudentPage() {
		return studentPage;
	}

	public void setStudentPage(Page studentPage) {
		this.studentPage = studentPage;
	}
}

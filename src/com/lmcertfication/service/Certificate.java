package com.cert.service;

public class Certificate {
	String employeeId;
	String employeeName;
	String certificationId;
	String certificationMode;
	String completedOn;
	String score;
	String grade;
	String status;
	
	public Certificate(String employeeId, String employeeName, String certificationId, String certificationMode, String completedOn, String score, String grade, String status){
		this.employeeId = employeeId; 
		this.employeeName = employeeName; 
		this.certificationId = certificationId; 
		this.certificationMode = certificationMode;
		this.completedOn = completedOn; 
		this.score = score;
		this.grade = grade;
		this.status = status;		
	}
	
	public void setEmployeeId(String employeeId) {
		
		this.employeeId = employeeId; 
	}

public String getEmployeeId() {
		
		return employeeId; 
	}

public void setEmployeeName(String employeeName) {
		
		this.employeeName = employeeName; 
	}

public String getEmployeeName() {
	
	return employeeName; 
}

public void setCertificationId(String certificationId) {
	
	this.certificationId = certificationId; 
}

public String getCertificationId() {
	
	return certificationId; 
}

public void setCertificationMode(String certificationMode) {
	
	this.certificationMode = certificationMode; 
}

public String getCertificationMode() {
	
	return certificationMode; 
}

public void setCompletedOn(String completedOn) {
	
	this.completedOn = completedOn; 
}

public String getCompletedOn() {
	
	return completedOn; 
}

public void setScore(String score) {
	
	this.score = score; 
}

public String getScore() {
	
	return score; 
}

public void setGrade(String grade) {
	
	this.grade = grade; 
}

public String getGrade() {
	
	return grade; 
}

public void setStatus(String status) {
	
	this.status = status; 
}

public String getStatus() {
	
	return status; 
}


}

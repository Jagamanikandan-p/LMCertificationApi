package com.cert.util;

import org.bson.Document;

import com.mongodb.client.MongoCursor;

public class QueryDatabase {
	
	DBCommands certificateDB = new DBCommands("DB_CERTIFICATION_DETAILS");
	
	public MongoCursor<Document> searchCetificatesbyEmpId(String empId) {
		
		Document query = new Document("EmployeeId", empId);
		MongoCursor<Document> response = certificateDB.findRecord(query);
		return response;
	}
	
	public MongoCursor<Document> searchCetificatesbyEmpName(String empName) {
		
		Document query = new Document("EmployeeName", empName);
		MongoCursor<Document> response = certificateDB.findRecord(query);
		return response;
	}
	
	public MongoCursor<Document> searchEmployeesByCertificate(String certificateId) {
		
		Document query = new Document("CertificationId", certificateId);
		MongoCursor<Document> response = certificateDB.findRecord(query);
		return response;
	}
	
	public void saveCertificate(String employeeId, String employeeName, String certificationId, String certificationMode, String completedOn, String score, String grade, String status) {
		 Document doc = new Document("EmployeeId", employeeId)
	                .append("EmployeeName", employeeName)
	                .append("CertificationId", certificationId)
	                .append("CertificationMode", certificationMode)
	                .append("CompletedOn", completedOn)
	                .append("Score", score)
	                .append("Grade", grade)
	                .append("Status", status);
		
		Document record = new Document(doc);
		System.out.println(doc);
		certificateDB.saveRecord(record);
	}
}

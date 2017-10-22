package com.cert.util;

import org.bson.Document;

import com.mongodb.client.MongoCursor;

public class QueryDatabase {
	
	public MongoCursor<Document> searchCetificatesbyEmpId(String empId) {
		
		DBConnection db = new DBConnection("DB_CERTIFICATION_DETAILS");
		Document query = new Document("EmployeeId", empId);
		
		MongoCursor<Document> response = db.runQuery(query);
		db.closeDBConnection();
		return response;
	}
	
	public MongoCursor<Document> searchCetificatesbyEmpName(String empName) {
		
		DBConnection db = new DBConnection("DB_CERTIFICATION_DETAILS");
		Document query = new Document("EmployeeName", empName);
		
		MongoCursor<Document> response = db.runQuery(query);
		db.closeDBConnection();
		return response;
	}

}

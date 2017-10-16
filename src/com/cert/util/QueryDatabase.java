package com.cert.util;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.util.JSON;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

public class QueryDatabase {
	
	public FindIterable<Document> searchCetificatesbyEmpId(String empId) {
		
		DBConnection db = new DBConnection("DB_CERTIFICATION_DETAILS");
		Document query = new Document("EmployeeId", empId);
		
		FindIterable<Document> response = db.runQuery(query);
		db.closeDBConnection();
		return response;
	}

}

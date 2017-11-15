package com.lmcertfication.dataaccess;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;

import com.lmcertfication.util.DBCommands;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class CertificationData {
	
	DBCommands certificateDB = new DBCommands("DB_CERTIFICATION_DETAILS");
	
	public MongoCursor<Document> searchCetificatesbyEmpId(String empId) {
		
		Document query = new Document("EmployeeId", empId);
		MongoCursor<Document> response = certificateDB.findRecord(query);
		certificateDB.closeDBConnection();
		return response;
	}
	
	public MongoCursor<Document>  searchCertificateById(ObjectId id){
		
		Document query = new Document("_id", id);
		MongoCursor<Document> response = certificateDB.findRecord(query);
		certificateDB.closeDBConnection();
		return response;
	}
	
	public MongoCursor<Document> searchCetificatesbyEmpName(String empName) {
		
		Document query = new Document("EmployeeName", empName);
		MongoCursor<Document> response = certificateDB.findRecord(query);
		certificateDB.closeDBConnection();
		return response;
	}
	
	public MongoCursor<Document> searchEmployeesByCertificate(String certificateId) {
		
		Document query = new Document("CertificationId", certificateId);
		MongoCursor<Document> response = certificateDB.findRecord(query);
		certificateDB.closeDBConnection();
		return response;
	}
	
	
public MongoCursor<Document> searchCertificateByYear(String year) {
		
		//Document regex = new Document("$regex", "/" + year + "/");
		//Document query = new Document("CompletedOn", "/"+year+"/");
		Document query = new Document("CompletedOn", "/"+year+"/");
		
		//Document query = new Document(); 
		//Document regex = new Document();
		//regex.append("$regex", year); 
		//query.append("year", regex);
		
		System.out.println(query);
		MongoCursor<Document> response = certificateDB.findRecord(query);
		certificateDB.closeDBConnection();
		return response;
	}
	
	public void saveCertificate1(String employeeId, String employeeName, String certificationId, String certificationMode, String completedOn, String score, String grade, String status) {
		 Document record = new Document("EmployeeId", employeeId)
	                .append("EmployeeName", employeeName)
	                .append("CertificationId", certificationId)
	                .append("CertificationMode", certificationMode)
	                .append("CompletedOn", completedOn)
	                .append("Score", score)
	                .append("Grade", grade)
	                .append("Status", status);
				
		certificateDB.saveRecord(record);
		certificateDB.closeDBConnection();
	}
	
	public ObjectId saveCertificate(String newCertificate) throws JSONException {
		
		JSONObject jObject  = new JSONObject(newCertificate); 
		Document record = new Document("EmployeeId", jObject.getString("employeeId"))
                .append("EmployeeName", jObject.getString("employeeName"))
                .append("CertificationId", jObject.getString("certificationName"))
                .append("CertificationMode", jObject.getString("certificationMode"))
                .append("CompletedOn", jObject.getString("completedOn"))
                .append("Score", jObject.getString("score"))
                .append("Grade", jObject.getString("grade"))
                .append("Status", jObject.getString("status"));
			
		certificateDB.saveRecord(record);
		ObjectId id = record.getObjectId("_id");
		return id;
	}
	
	
	public ObjectId modifyCertificate(String newCertificate) throws JSONException {
		
		JSONObject jObject  = new JSONObject(newCertificate); 
		ObjectId oid = new ObjectId(jObject.getString("oid"));
		Document query = new Document("_id", oid);
		Document record = new Document("EmployeeId", jObject.getString("employeeId"))
                .append("EmployeeName", jObject.getString("employeeName"))
                .append("CertificationId", jObject.getString("certificationName"))
                .append("CertificationMode", jObject.getString("certificationMode"))
                .append("CompletedOn", jObject.getString("completedOn"))
                .append("Score", jObject.getString("score"))
                .append("Grade", jObject.getString("grade"))
                .append("Status", jObject.getString("status"));
					
		UpdateResult result = certificateDB.updateRecord(query, record);
		System.out.println(result);
		return oid;
	}	
	
public String removeCertificate(String certificateId){
		
		Document query = new Document("_id", new ObjectId(certificateId));
		System.out.println(certificateId);
		DeleteResult result = certificateDB.deleteRecord(query);
		certificateDB.closeDBConnection();
		if (result.toString()=="AcknowledgedDeleteResult{deletedCount=1}")
			return "Certificate Deleted Successfully";
		else
			return result.toString();
		
	}
}
package com.lmcertfication.dataaccess;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;

import com.lmcertfication.util.DBCommands;
import com.mongodb.client.MongoCursor;

public class UserData {
	DBCommands loginDB = new DBCommands("DB_LOGIN_DETAILS");
	DBCommands registrationDB = new DBCommands("DB_REGISTRATION_DETAILS");
	
	public MongoCursor<Document> validateUser(String username, String password) {
		
		Document query = new Document("UserId", username)
						.append("Password", password);
		MongoCursor<Document> result = loginDB.findRecord(query);
		loginDB.closeDBConnection();
		return result;
	}
	
	public MongoCursor<Document> getUserByUserId(String userId) {
		
		Document query = new Document("EmployeeId", userId);
		Document userFilter = new Document("EmployeeId", 1)
				.append("EmployeeName", 1)
				.append("_id", 0);
		MongoCursor<Document> response = registrationDB.findRecord(query, userFilter);
		registrationDB.closeDBConnection();
		return response;
	}
	
	public MongoCursor<Document> getUserByUsername(String username) {
		
		Document query = new Document("EmployeeName", username);
		Document userFilter = new Document("EmployeeId", 1)
				.append("EmployeeName", 1)
				.append("_id", 0);
		MongoCursor<Document> response = registrationDB.findRecord(query, userFilter);
		registrationDB.closeDBConnection();
		return response;
		}
	
	public MongoCursor<Document> getUserById(ObjectId id_user) {
		
		Document query = new Document("_id", id_user);
		MongoCursor<Document> response = registrationDB.findRecord(query);
		registrationDB.closeDBConnection();
		return response;
	}
	
	public MongoCursor<Document> retrieveUserDetails(String userId) {
			
			Document query = new Document("EmployeeId", userId);
			Document userFilter = new Document("EmployeeId", 1)
					.append("EmployeeName", 2)
					.append("EmailId", 3)
					.append("Role", 4)
					.append("_id", 0);
			MongoCursor<Document> result = registrationDB.findRecord(query, userFilter);
			registrationDB.closeDBConnection();
			return result;
		}


	public ObjectId saveUserDetails(String newUser) throws JSONException {
		
		JSONObject jObject  = new JSONObject(newUser); 
		Document record = new Document("EmployeeId", jObject.getString("employeeId"))
	            .append("EmployeeName", jObject.getString("employeeName"))
	            .append("EmailId", jObject.getString("emailId"))
	            .append("DateOfJoining", jObject.getString("dateOfJoining"))
	            .append("Role", jObject.getString("role"));
			
		registrationDB.saveRecord(record);
		ObjectId id = record.getObjectId("_id");
		return id;
	}
	
	public ObjectId saveLoginDetails (String newUser) throws JSONException {

		JSONObject jObject  = new JSONObject(newUser); 
		Document record = new Document("UserId", jObject.getString("employeeId"))
	            .append("Password", jObject.getString("password"));
			
		loginDB.saveRecord(record);
		ObjectId id = record.getObjectId("_id");
		return id;
		
	}
}
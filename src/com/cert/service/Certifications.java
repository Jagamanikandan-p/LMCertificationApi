package com.cert.service;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;
import com.cert.util.QueryDatabase;
import com.mongodb.client.FindIterable;

@Path("/certificate")
public class Certifications {

	@GET
	@Path("/getCertificatesByEmpId/{empId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Document> getCertificatesByEmpId(@PathParam("empId") String empId){
	
		QueryDatabase queryDB = new QueryDatabase();
		FindIterable<Document> queryResult = queryDB.searchCetificatesbyEmpId(empId);
		//Document document = queryResult.first();
	    // String certificateList = document.toJson();
		//ArrayList<JSONObject> certificateList = new ArrayList<>();
		//for (Document document : queryResult) {
		// System.out.println("Jaga");
			//certificateList.add(((Document) queryResult).toJson());
	      //}
		ArrayList<Document> certificateList = new ArrayList<Document>();
		queryResult.into(certificateList);
		//String certificateList = JSONParser.serialize(queryResult);
		//String certificateList =  queryResult.toString();
		//System.out.println(certificateList);
	    return certificateList;
	}
}
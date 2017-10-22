package com.cert.service;

import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.bson.Document;
import com.cert.util.QueryDatabase;
import com.mongodb.client.MongoCursor;

@Path("/certificate")
public class Certifications {

	@GET
	@Path("/getCertificatesByEmpId/{empId}")
	@Produces( MediaType.APPLICATION_JSON )
	public String getCertificatesByEmpId(@PathParam("empId") String empId){
	
		QueryDatabase queryDB = new QueryDatabase();
		MongoCursor<Document> queryResult = queryDB.searchCetificatesbyEmpId(empId);
	    ArrayList<String> certificateList = new ArrayList<String>();
		while (queryResult.hasNext()) {
			certificateList.add(queryResult.next().toJson());			
		}
	    return certificateList.toString();
	}
	
	@GET
	@Path("/getCertificatesByEmpName/{empName}")
	@Produces( MediaType.APPLICATION_JSON )
	public String getCertificatesByEmpName(@PathParam("empName") String empName){
	
		QueryDatabase queryDB = new QueryDatabase();
		MongoCursor<Document> queryResult = queryDB.searchCetificatesbyEmpName(empName);
	    ArrayList<String> certificateList = new ArrayList<String>();
		while (queryResult.hasNext()) {
			certificateList.add(queryResult.next().toJson());			
		}
	    return certificateList.toString();
	}
}
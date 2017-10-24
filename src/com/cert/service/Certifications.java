package com.cert.service;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;
import com.cert.util.QueryDatabase;
import com.cert.util.Utilities;
import com.mongodb.client.MongoCursor;

@Path("/certificate")
public class Certifications {
	
	MongoCursor<Document> queryResult;
	Utilities utilities = new Utilities();
	//Certificate newCertificate = new Certificate();

	@GET
	@Path("/getCertificatesByEmployee/{empDetail}")
	@Produces( MediaType.APPLICATION_JSON )
	public String getCertificatesByEmployee(@PathParam("empDetail") String empDetail){
	
		QueryDatabase queryDB = new QueryDatabase();
		if(utilities.isNumeric(empDetail)) 
		{
			queryResult = queryDB.searchCetificatesbyEmpId(empDetail);
		}
		else
		{
			queryResult = queryDB.searchCetificatesbyEmpName(empDetail);
		}
	    ArrayList<String> certificateList = new ArrayList<String>();
		
		while (queryResult.hasNext()) {
			certificateList.add(queryResult.next().toJson());			
		}
	    return certificateList.toString();
	}
	
	
	
	@GET
	@Path("/getEmployeesByCertificate/{certificateId}")
	@Produces( MediaType.APPLICATION_JSON )
	public String getEmployeesByCertificate(@PathParam("certificateId") String certificateId){
	
		QueryDatabase queryDB = new QueryDatabase();
		MongoCursor<Document> queryResult = queryDB.searchEmployeesByCertificate(certificateId);
	    ArrayList<String> certificateList = new ArrayList<String>();
		while (queryResult.hasNext()) {
			certificateList.add(queryResult.next().toJson());			
		}
	    return certificateList.toString();
	}
	
	@POST
	@Path("/addNewCertificate1")
	@Produces( MediaType.TEXT_PLAIN )
	@Consumes( MediaType.APPLICATION_JSON )
	public String addNewCertificate1(Certificate newCertificate){
	
		//QueryDatabase queryDB = new QueryDatabase();
		//queryDB.saveCertificate(newCertificate);
		System.out.println("Jaga");
		System.out.println(newCertificate.getEmployeeId());
	    return "Certificate Saved";
	}
	
	@GET
	@Path("/addNewCertificate/{employeeId}/{employeeName}/{certificationId}/{certificationMode}/{completedOn}/{score}/{grade}/{status}")
	@Produces( MediaType.TEXT_PLAIN )

	public String addNewCertificate(@PathParam("employeeId") String employeeId, @PathParam("employeeName") String employeeName, @PathParam("certificationId") String certificationId, @PathParam("certificationMode") String certificationMode, @PathParam("completedOn") String completedOn, @PathParam("score") String score, @PathParam("grade") String grade, @PathParam("status") String status){
	
		QueryDatabase queryDB = new QueryDatabase();
		//queryDB.saveCertificate(employeeId, employeeName, certificationId, certificationMode, completedOn, score, grade, status);
		System.out.println(employeeId);
		System.out.println(employeeName);
		System.out.println(certificationId);
		System.out.println(certificationMode);
		System.out.println(completedOn);
		System.out.println(score);
		System.out.println(grade);
		System.out.println(status);
		
	    return "Certificate Saved";
	}
}
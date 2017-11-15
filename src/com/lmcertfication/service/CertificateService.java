package com.lmcertfication.service;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONException;

import com.lmcertfication.dataaccess.CertificationData;
import com.lmcertfication.util.Utilities;
import com.mongodb.client.MongoCursor;

@Path("/certificate")
public class CertificateService {
	
	Utilities utilities = new Utilities();
	CertificationData certidicateData = new CertificationData();

	@GET
	@Path("/getCertificatesByEmployee/{empDetail}")
	@Produces( MediaType.APPLICATION_JSON )
	public String getCertificatesByEmployee(@PathParam("empDetail") String empDetail){
	
		MongoCursor<Document> queryResult;
		if(utilities.isNumeric(empDetail)) 
		{
			queryResult = certidicateData.searchCetificatesbyEmpId(empDetail);
		}
		else
		{
			queryResult = certidicateData.searchCetificatesbyEmpName(empDetail);
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
	
		MongoCursor<Document> queryResult = certidicateData.searchEmployeesByCertificate(certificateId);
	    ArrayList<String> certificateList = new ArrayList<String>();
		while (queryResult.hasNext()) {
			certificateList.add(queryResult.next().toJson());			
		}
	    return certificateList.toString();
	}
	
	@GET
	@Path("/getCertificateByYear/{year}")
	@Produces( MediaType.APPLICATION_JSON )
	public String getCertificateByYear(@PathParam("year") String year){
	
		MongoCursor<Document> queryResult = certidicateData.searchCertificateByYear(year);
	    ArrayList<String> certificateList = new ArrayList<String>();
		
	    while (queryResult.hasNext()) {
			certificateList.add(queryResult.next().toJson());			
		}
	    
	    return certificateList.toString();
	}
	
	@POST
	@Path("/addNewCertificate")
	@Produces( MediaType.APPLICATION_JSON )
	@Consumes( MediaType.APPLICATION_JSON )
	public String addNewCertificate(String newCertificate) throws JSONException {
		
		ObjectId id = certidicateData.saveCertificate(newCertificate);
		
		MongoCursor<Document> queryResult = certidicateData.searchCertificateById(id);
	    ArrayList<String> addedCertificate = new ArrayList<String>();
		
	    while (queryResult.hasNext()) {
	    	addedCertificate.add(queryResult.next().toJson());			
		}
	    
	    return addedCertificate.toString();
	}
	
	@PUT
	@Path("/updateCertificate")
	@Produces( MediaType.APPLICATION_JSON  )
	@Consumes( MediaType.APPLICATION_JSON )
	public String updateCertificate(String newCertificate) throws JSONException {
		
		ObjectId id = certidicateData.modifyCertificate(newCertificate);
		MongoCursor<Document> queryResult = certidicateData.searchCertificateById(id);
	    ArrayList<String> addedCertificate = new ArrayList<String>();
		
	    while (queryResult.hasNext()) {
	    	addedCertificate.add(queryResult.next().toJson());			
		}
	    
	    return addedCertificate.toString();
	}
	
	@DELETE
	@Path("/deleteCertificate/{certificateId}")
	@Produces( MediaType.TEXT_PLAIN )
	public String deleteCertificate(@PathParam("certificateId") String certificateId) throws JSONException {
		
		return certidicateData.removeCertificate(certificateId);
	}	
	@GET
	@Path("/addNewCertificate1/{employeeId}/{employeeName}/{certificationId}/{certificationMode}/{completedOn}/{score}/{grade}/{status}")
	@Produces( MediaType.TEXT_PLAIN )

	public String addNewCertificate1(@PathParam("employeeId") String employeeId, @PathParam("employeeName") String employeeName, @PathParam("certificationId") String certificationId, @PathParam("certificationMode") String certificationMode, @PathParam("completedOn") String completedOn, @PathParam("score") String score, @PathParam("grade") String grade, @PathParam("status") String status){
	
		certidicateData.saveCertificate1(employeeId, employeeName, certificationId, certificationMode, completedOn, score, grade, status);
	    return "Certificate Saved";
	}
}


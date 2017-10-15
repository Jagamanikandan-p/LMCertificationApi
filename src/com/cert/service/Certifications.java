package com.cert.service;

import java.util.Iterator;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;
import org.bson.json.JsonMode;

import com.cert.util.DBConnection;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.Cursor;
import com.mongodb.DBCursor;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.util.JSON;

@Path("/certificate")
public class Certifications {

	@GET
	@Path("/getCertificatesByEmpId/{empId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCertificatesByEmpId(@PathParam("empId") String empId){
	
		DBConnection db = new DBConnection("DB_CERTIFICATION_DETAILS");
		
		//JsonMode Response[];
				
		FindIterable<Document> iterDoc = db.searchCetificatesbyEmpId(empId);

	      // MongoCursor<Document> it = iterDoc.iterator(); 
	    
	      //while (it.hasNext()) {  
	      // Response = Response + it.next();
		String Response = JSON.serialize(iterDoc);
		//Block<Document> printBlock = new Block<Document>() {
		//       @Override
		//       public void apply(final Document document) {
		//           System.out.println(document.toJson());
		//       }
		//};
		
		//iterDoc.forEach(printBlock);
	    //  Response = Response + document.toJson()
		  db.closeDBConnection();
	      return Response;
	}
}
package com.jaga.service;

import javax.ws.rs.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/world")
public class FirstRESTService {
	
@GET
@Path("/getSum")
@Produces(MediaType.APPLICATION_JSON)

public String getSum(int a, int b) {
	return ("Sum of " + a + " and " + b +" is " +a+b );
}

@GET
@Path("/sayHello")
public String getText() {
	return ("Hello Jaga!! Success.");
}
}

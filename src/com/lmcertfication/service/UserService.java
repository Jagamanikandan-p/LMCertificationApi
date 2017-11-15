package com.lmcertfication.service;

import java.util.ArrayList;

import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONException;

import com.lmcertfication.dataaccess.UserData;
import com.lmcertfication.util.Utilities;
import com.mongodb.client.MongoCursor;

@Path("/user")
public class UserService {

		UserData userData = new UserData();

		@GET
		@Path("/authenticateUser/{username}/{password}")
		@Produces( MediaType.APPLICATION_JSON )
		public String authenticateUser(@PathParam("username") String username, @PathParam("password") String password ){
		
				MongoCursor<Document> result = userData.validateUser(username, password);
				
				if (result.hasNext()) {
					MongoCursor<Document> user = userData.retrieveUserDetails(username);
					if (user.hasNext())
						return user.next().toJson().toString();
					else 
						return null;
					}
				else
					return null;
				//00 - LoginFailed
				//01 = Login Successful. User Role <Group Lead>
				//02 = Login Successful. User Role <L&D Spoc>
				//03 = Login Successful. User Role <Team Lead>
				//04 = Login Successful. User Role <Team Member>
		}
		

		@GET
		@Path("/getUser/{user}")
		@Produces( MediaType.APPLICATION_JSON )
		public String getUser(@PathParam("user") String user){
			
			MongoCursor<Document> queryResult;
			Utilities utilities = new Utilities();
			if(utilities.isNumeric(user)) 
			{
				queryResult = userData.getUserByUserId(user);
			}
			else
			{
				queryResult = userData.getUserByUsername(user);
			}
		
			ArrayList<String> userList = new ArrayList<String>();
			
			while (queryResult.hasNext()) {
				userList.add(queryResult.next().toJson());			
			}
		    return userList.toString();
}
		
		@POST
		@Path("/registerNewuser")
		@Produces( MediaType.APPLICATION_JSON )
		@Consumes( MediaType.APPLICATION_JSON )
		public String registerNewuser(String newUser) throws JSONException {
			
			ObjectId id_user = userData.saveUserDetails(newUser);
			ObjectId id_login = userData.saveLoginDetails(newUser);
			
			MongoCursor<Document> result = userData.getUserById(id_user);
			
		    if (result.hasNext()) {
		    	return result.next().toJson().toString();			
			}
		    else
		    	return null;
		}
}
package com.lmcertfication.util;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class DBCommands {
	
	MongoClient mongo = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));; 
	MongoDatabase db = mongo.getDatabase("LMCertificationsDB");
	MongoCollection<Document> dbCollection;
	
	public DBCommands(String collection) {
		dbCollection = db.getCollection(collection);		
	}
	
	public MongoCursor<Document> findRecord(Document query) {
	
		return dbCollection.find(query).iterator();
	}
	
	public MongoCursor<Document> findRecord(Document query, Document  userFilter) {
		
		return dbCollection.find(query).projection(userFilter).iterator();
	}
	
	public void saveRecord(Document record) {
		
		dbCollection.insertOne(record);
	}
	
	public UpdateResult updateRecord(Document query, Document record) {
		
		return dbCollection.replaceOne(query, record);
	}
	
public DeleteResult deleteRecord(Document query) {
		
		return dbCollection.deleteOne(query);
	}
	
	public void closeDBConnection() {
		
		mongo.close();
	}
}












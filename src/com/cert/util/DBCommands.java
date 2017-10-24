package com.cert.util;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

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
	
	public void saveRecord(Document record) {
		
		dbCollection.insertOne(record);
	}
	
	public void closeDBConnection() {
		
		mongo.close();
	}
}

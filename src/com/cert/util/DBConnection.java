package com.cert.util;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class DBConnection {
	
	MongoClient mongo = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));; 
	MongoDatabase db = mongo.getDatabase("LMCertificationsDB");
	MongoCollection<Document> dbCollection;
	
	public DBConnection(String collection) {
		dbCollection = db.getCollection(collection);		
	}
	
	public MongoCursor<Document> runQuery(Document query) {
	
		return dbCollection.find(query).iterator();
	}
	
	public void closeDBConnection() {
		
		mongo.close();
	}
}

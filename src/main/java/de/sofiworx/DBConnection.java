package de.sofiworx;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * @author Ulrich Cech
 */
@Startup
@Singleton
public class DBConnection {

    @PostConstruct
    private void init() {
        MongoClientURI uri = new MongoClientURI(
                "mongodb://batman:123456@test-shard-00-00-jgt7z.mongodb.net:27017,test-shard-00-01-jgt7z.mongodb.net:27017,test-shard-00-02-jgt7z.mongodb.net:27017/admin?ssl=true&replicaSet=test-shard-0&authSource=admin");

        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("test");
        final String collectionName = "myfirstcollection";
        database.createCollection(collectionName);

        Document docBeforeUpdate = new Document("key1", "value1");
        database.getCollection(collectionName).insertOne(docBeforeUpdate);
        final FindIterable<Document> documents = database.getCollection(collectionName).find();
        for (Document document : documents) {
            System.out.println("document = " + document.toJson());
        }
    }

}

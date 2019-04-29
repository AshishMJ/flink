package com.ashish.flink.mongo;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.bson.Document;
import org.bson.conversions.Bson;

public class FociAuditSinkFunction extends RichSinkFunction<Tuple3<String, Document, Document>> {
    private MongoCollection<Document> auditCollection;
    private UpdateOptions updateOptions;

    private static final String DATABASE_NAME = "foci_audit";
    private static final String COLLECTION_NAME = "foci_audit";

    @Override
    public void invoke(Tuple3<String, Document, Document> auditData, Context context) {
        try {
            String orderId = auditData.f0;

            //Filters.
            Bson filters = Filters.eq(MongoDocumentKeys.ORDER_ID, orderId);

            //Updates.
            Bson orderIdUpdate = Updates.set(MongoDocumentKeys.ORDER_ID, orderId);
            Bson eventsUpdate = Updates.addToSet(MongoDocumentKeys.EVENTS, auditData.f1);
            auditCollection.updateOne(filters, Updates.combine(orderIdUpdate, eventsUpdate), updateOptions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void open(Configuration config) {
        MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017));
        auditCollection = mongoClient.getDatabase(DATABASE_NAME).getCollection(COLLECTION_NAME);
        updateOptions = new UpdateOptions().upsert(true);
    }
}

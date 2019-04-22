package com.ashish.flink.mongo;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.bson.Document;
import org.bson.conversions.Bson;

public class MongoSinkFunction extends RichSinkFunction<FociAudit> {
    private MongoCollection<Document> auditCollection;
    private UpdateOptions updateOptions;

    private static final String DATABASE_NAME = "foci_audit";
    private static final String COLLECTION_NAME = "foci_audit";

    @Override
    public void invoke(FociAudit auditData, Context context) throws Exception {
        Bson filters = Filters.eq("orderId", auditData.getOrderId());
        Bson orderIdUpdate = Updates.set("orderId", auditData.getOrderId());

        FociEventDto event = auditData.getEvent();
        Document eventDocument = new Document();
        eventDocument.put("eventName", event.getEventName());
        eventDocument.put("payload", event.getPayload());
        eventDocument.put("createdBy", event.getCreatedBy());
        eventDocument.put("timeStamp", event.getTimeStamp());
        Bson eventsUpdate = Updates.addToSet("events", eventDocument);

        UpdateResult updateResult = auditCollection.updateOne(filters, Updates.combine(orderIdUpdate, eventsUpdate), updateOptions);

        System.out.println("MatchedCount  : " + updateResult.getMatchedCount());
        System.out.println("ModifiedCount : " + updateResult.getModifiedCount());
    }

    @Override
    public void open(Configuration config) throws Exception {
        MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017));
        auditCollection = mongoClient.getDatabase(DATABASE_NAME).getCollection(COLLECTION_NAME);
        updateOptions = new UpdateOptions().upsert(true);
    }
}

package com.ashish.flink.mappers;

import com.ashish.flink.mongo.MongoDocumentKeys;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple3;
import org.bson.Document;

import java.util.Date;

public class EventToMongoDocMapper implements MapFunction<String, Tuple3<String, Document, Document>> {

    /**
     * Parse the input event payload and converts it into mongo documents.
     *
     * @param value The Input Value
     * @return output
     * @throws Exception
     */
    @Override
    public Tuple3<String, Document, Document> map(String value) {
        Document root = Document.parse(value);

        //Extract Mandatory Data from event.
        Document header = root.get("header", Document.class);
        String orderId = header.get("orderId", String.class);
        String eventName = header.get("eventName", String.class);
        String createdBy = header.get("createdBy", String.class);
        String timeStamp = header.get("timestamp", String.class);
        String payload = root.get("payload", String.class);
        Document additionalAttributes = root.get("additionalAttributes", Document.class);

        //Populate Mongo Document.
        Document fociEvent = new Document();
        fociEvent.append(MongoDocumentKeys.EVENT_NAME, eventName)
                .append(MongoDocumentKeys.PAYLOAD, payload)
                .append(MongoDocumentKeys.CREATED_BY, createdBy)
                .append(MongoDocumentKeys.TIMESTAMP, new Date(Long.valueOf(timeStamp)))
                .append(MongoDocumentKeys.ADDITIONAL_ATTRIBUTES, additionalAttributes);

        return Tuple3.of(orderId, fociEvent, null);
    }
}

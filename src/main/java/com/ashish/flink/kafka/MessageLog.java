package com.ashish.flink.kafka;

import java.util.Map;
import java.util.UUID;

public class MessageLog<T> {
    private static final String FOCI = "FOCI";

    private static final long serialVersionUID = 1L;

    private MessageLog.Header header;

    private T payload;

    private Map<String, Object> extraData;

    public MessageLog(EventType eventType, String orderId) {
        this.header = new MessageLog.Header();
        this.header.setEventID(UUID.randomUUID().toString());
        this.header.setEventName(eventType);
        this.header.setProducerName(FOCI);
        this.header.setTimestamp(String.valueOf(System.currentTimeMillis()));
        this.header.setSchemaVersionNo(String.valueOf(1));
        this.header.setOrderId(orderId);
    }

    public MessageLog() {
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public Map<String, Object> getExtraData() {
        return extraData;
    }

    public void setExtraData(Map<String, Object> extraData) {
        this.extraData = extraData;
    }

    @Override
    public String toString() {
        return "FMSOutboundMessage{" +
                "header=" + header +
                ", payload=" + payload +
                '}';
    }

    public static class Header{

        private String schemaVersionNo;
        private EventType eventName;
        private String producerName;
        private String eventID;
        private String timestamp;
        private String orderId;
        private String createdBy;

        public String getSchemaVersionNo() {
            return schemaVersionNo;
        }

        public void setSchemaVersionNo(String schemaVersionNo) {
            this.schemaVersionNo = schemaVersionNo;
        }

        public EventType getEventName() {
            return eventName;
        }

        public void setEventName(EventType eventName) {
            this.eventName = eventName;
        }

        public String getProducerName() {
            return producerName;
        }

        public void setProducerName(String producerName) {
            this.producerName = producerName;
        }


        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getEventID() {
            return eventID;
        }

        public void setEventID(String eventID) {
            this.eventID = eventID;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        @Override
        public String toString() {
            return "Header{" +
                    "schemaVersionNo='" + schemaVersionNo + '\'' +
                    ", eventName=" + eventName +
                    ", producerName='" + producerName + '\'' +
                    ", eventID='" + eventID + '\'' +
                    ", timestamp='" + timestamp + '\'' +
                    ", orderId='" + orderId + '\'' +
                    ", createdBy='" + createdBy + '\'' +
                    '}';
        }
    }

    public enum EventType{
        OMS_TO_FOCI_REQUEST,
        FOCI_TO_MCSE_REQUEST,
        FOCI_TO_MCSE_REQUEST_RESERVATION,
        FOCI_TO_FMS_SOURCING_RESPONSE;
    }
}

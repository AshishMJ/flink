package com.ashish.flink.kafka;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

public class MessageLog<T> implements Serializable {

    private static final String FOCI = "FOCI";

    private static final long serialVersionUID = 1L;

    private MessageLog.Header header;

    private T payload;

    private Map<String, Object> additionalAttributes;

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

    public Map<String, Object> getAdditionalAttributes() {
        return additionalAttributes;
    }

    public void setAdditionalAttributes(Map<String, Object> additionalAttributes) {
        this.additionalAttributes = additionalAttributes;
    }

    @Override
    public String toString() {
        return "MessageLog{" +
                "header=" + header +
                ", payload=" + payload +
                ", additionalAttributes=" + additionalAttributes +
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
        FOCI_TO_IMS_RESERVATION_REQUEST,
        MCSE_TO_FOCI_RESPONSE,
        IMS_TO_FOCI_RESERVATION_RESPONSE,
        OMS_TO_FOCI_CANCEL_REQUEST,
        FOCI_TO_IMS_CANCEL_REQUEST,
        FOCI_TO_OMS_BACKORDER_STATUS,
        FOCI_TO_FMS_SOURCING_RESPONSE;
    }
}

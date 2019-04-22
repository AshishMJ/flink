package com.ashish.flink.mongo;

public class FociEventDto {
    private String eventName;
    private String payload;
    private String createdBy;
    private String timeStamp;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "FociEventDto{" +
                "eventName='" + eventName + '\'' +
                ", payload='" + payload + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}

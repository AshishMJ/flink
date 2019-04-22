package com.ashish.flink.mongo;

import java.io.Serializable;

public class FociAudit implements Serializable {
    private String orderId;
    private FociEventDto event;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public FociEventDto getEvent() {
        return event;
    }

    public void setEvent(FociEventDto event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "FociAudit{" +
                "orderId='" + orderId + '\'' +
                ", events=" + event +
                '}';
    }
}

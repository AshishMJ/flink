package com.ashish.flink.mappers;

import com.ashish.flink.kafka.MessageLog;
import com.ashish.flink.mongo.FociAudit;
import com.ashish.flink.mongo.FociEventDto;
import org.apache.flink.api.common.functions.MapFunction;

public class EventToMongoDocMapper implements MapFunction<MessageLog<String>, FociAudit> {

    @Override
    public FociAudit map(MessageLog<String> data) throws Exception {
        MessageLog.Header header = data.getHeader();

        FociAudit fociAudit = new FociAudit();
        fociAudit.setOrderId(header.getOrderId());

        //CreateEvent.
        FociEventDto fociEventDto = new FociEventDto();
        fociEventDto.setEventName(header.getEventName().name());
        fociEventDto.setPayload(data.getPayload());
        fociEventDto.setCreatedBy(header.getCreatedBy());
        fociEventDto.setTimeStamp(header.getTimestamp());
        fociAudit.setEvent(fociEventDto);

        return fociAudit;
    }
}

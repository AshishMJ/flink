package com.ashish.flink.kafka;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ProducerUtility {

    private KafkaProducer<String, String> kafkaProducer;

    public ProducerUtility() {
        configProducer();
    }

    public void configProducer() {
        Properties props = new Properties();

        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("retries", "0");
        props.setProperty("batch.size", "12912423");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("linger.ms", "1");
        props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty("block.on.buffer.full", "true");
        props.setProperty("serializer.class", "kafka.serializer.StringEncoder");
        props.setProperty("request.required.acks", "1");
        props.setProperty("metadata.broker.list", "localhost:9092");
        props.setProperty("max.request.size", "1600000");
        this.kafkaProducer = new KafkaProducer<String, String>(props);
        System.out.println("Kafka producer bootstrap.server " + props.getProperty("bootstrap.servers"));
    }

    public void sendMessage(String body, String topic, boolean printToLog) throws ExecutionException, InterruptedException {
        System.out.println("Send to Kafka start");
        Future<RecordMetadata> fut = this.kafkaProducer.send(new ProducerRecord<>(topic, body));
        System.out.println(fut.get());
        System.out.println("Send to Kafka end");
        if (printToLog) {
            System.out.println("successful send to Kafka topic [" + topic + "] with message [" + body + "]");
        }
    }

    public static void main(String args[]) {
        ProducerUtility util = new ProducerUtility();

        MessageLog<String> messageLog = new MessageLog<>(MessageLog.EventType.FOCI_TO_MCSE_REQUEST, "2");
        messageLog.getHeader().setCreatedBy("inbound-engine");
        messageLog.setPayload("{\"orderId\":\"2\"}");

        try {
            String event = new ObjectMapper().writeValueAsString(messageLog);
            util.sendMessage(event, "foci_audit", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
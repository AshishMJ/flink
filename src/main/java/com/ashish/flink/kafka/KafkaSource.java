package com.ashish.flink.kafka;


import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;

import java.util.Properties;

public class KafkaSource {

    private static final String TOPIC_NAME = "foci_audit";
    private static final String CONSUMER_GROUP_ID = "foci-audit-consumer";

    public static FlinkKafkaConsumer010<String> getSource() {
        FlinkKafkaConsumer010<String> consumer = new FlinkKafkaConsumer010<>(TOPIC_NAME, new SimpleStringSchema(), constructProperties());
        return consumer;
    }

    private static Properties constructProperties() {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("group.id", CONSUMER_GROUP_ID);
        return props;
    }
}

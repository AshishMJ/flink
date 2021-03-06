package com.ashish.flink.jobs;

import com.ashish.flink.kafka.KafkaSource;
import com.ashish.flink.mappers.EventToMongoDocMapper;
import com.ashish.flink.mongo.FociAuditSinkFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class StartUp {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> dataStreamSource = env.addSource(KafkaSource.getSource());

        dataStreamSource.
                map(new EventToMongoDocMapper())
                .addSink(new FociAuditSinkFunction());

        env.execute("Start");

    }
}

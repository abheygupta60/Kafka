package com.abheygupta.kafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class TestConsumer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("group.id", "test");
        props.setProperty("enable.auto.commit", "false");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("max.poll.records", "1");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        //consumer.subscribe(Arrays.asList("test"));
        TopicPartition tp = new TopicPartition("test",0);
        consumer.assign(Arrays.asList(tp));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            System.out.printf("Size %d%n",records.count());
            for (ConsumerRecord<String, String> record : records) {
                int j = Integer.valueOf(record.value());
                if(j==50){
                    consumer.commitAsync();
                }
                    System.out.printf("interation = %d offset = %d, key = %s, value = %s%n",j, record.offset(), record.key(), record.value());
            }
        }
    }
}

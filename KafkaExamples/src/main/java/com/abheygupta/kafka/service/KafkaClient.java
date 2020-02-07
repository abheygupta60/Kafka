package com.abheygupta.kafka.service;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class KafkaClient {

    Admin KafkaAdmin;

    KafkaClient(Map<String, Object> conf){
        KafkaAdmin = Admin.create(conf);
    }

    private boolean topicExists(String name) throws ExecutionException, InterruptedException {
        ListTopicsResult listTopicsResult = KafkaAdmin.listTopics();
        KafkaFuture<Set<String>> topicResult =  listTopicsResult.names();
        Set<String> topString =  topicResult.get();

        if(topString.contains(name))
            return true;
        else
            return false;
    }

    public void createTopic(String name, int numPartitions, short replicationFactor) throws ExecutionException, InterruptedException {
        if (topicExists(name)){
            System.out.printf("Topic %s existed already.%n",name);
            return;
        }
        NewTopic topic = new NewTopic(name, numPartitions, replicationFactor);
        KafkaAdmin.createTopics(Arrays.asList(topic));
        while(true){
            if (topicExists(name)){
                System.out.printf("Topic %s created successfully.%n",name);
                break;
            }
        }
    }

    public static void main(String[] args) {

        Map<String, Object> conf =  new HashMap<>();
        conf.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        KafkaClient setup = new KafkaClient(conf);
        try {
            setup.createTopic("test5", 1,(short)1);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

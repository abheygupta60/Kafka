#!/bin/bash

echo "Downloading Kafka"
cd ~/
mkdir Kafka
cd Kafka
mkdir background
wget http://mirrors.estointernet.in/apache/kafka/2.4.0/kafka_2.12-2.4.0.tgz
tar -xvf kafka_2.12-2.4.0.tgz
cd kafka_2.12-2.4.0
echo "Starting Zookeeper"
bin/zookeeper-server-start.sh config/zookeeper.properties &> ~/Kafka/background/zookeeper.log &
echo "Starting Borker"
bin/kafka-server-start.sh config/server.properties &> ~/Kafka/background/broker.log &

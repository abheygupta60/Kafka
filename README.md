# Kafka
My work with Kafka in Java language

Step 1.) Execute Following script

	a.) sh setupKafka.sh

OR

Step 1.) Download Kafka
	
	a.) wget https://www.apache.org/dyn/closer.cgi?path=/kafka/2.4.0/kafka_2.12-2.4.0.tg
	b.) tar -xzf kafka_2.12-2.4.0.tgz
	c.) cd kafka_2.12-2.4.0
	
Step 2.) Start the server

	Kafka uses ZooKeeper so you need to first start a ZooKeeper server 
	if you don't already have one. You can use the convenience script 
	packaged with kafka to get a quick-and-dirty single-node ZooKeeper instance.

	> bin/zookeeper-server-start.sh config/zookeeper.properties

	Now start the Kafka server:

	> bin/kafka-server-start.sh config/server.properties

	
Step 3.) Download Code & Import in any IDE supporting maven	

# Kafka
Projeto exemplo para producao e consumo de mensagens no Kafka


./zookeeper-server-start.sh ../config/zookeeper.properties 

./kafka-server-start.sh ../config/server.properties 

./kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic users
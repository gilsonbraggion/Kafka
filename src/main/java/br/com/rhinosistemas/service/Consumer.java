package br.com.rhinosistemas.service;

import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

	private KafkaConsumer<String, String> kafkaConsumer;

	public Consumer() {

		Properties consumerProperties = new Properties();
		consumerProperties.put("bootstrap.servers", "localhost:9092");
		consumerProperties.put("group.id", "group_id");
		consumerProperties.put("zookeeper.session.timeout.ms", "6000");
		consumerProperties.put("zookeeper.sync.time.ms", "2000");
		consumerProperties.put("auto.commit.enable", "true");
		consumerProperties.put("auto.commit.interval.ms", "1000");
		consumerProperties.put("consumer.timeout.ms", "-1");
		consumerProperties.put("max.poll.records", "10");
		consumerProperties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		consumerProperties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		kafkaConsumer = new KafkaConsumer<String, String>(consumerProperties);
		kafkaConsumer.subscribe(Arrays.asList("users"));
	}

	// @KafkaListener(topics = "users", groupId = "group_id", autoStartup =
	// "false")
	// public void consume(String message) throws IOException {
	// // logger.info(String.format("#### -> Consumed message -> %s",
	// // message));
	//
	// Gson gson = new Gson();
	// User usuario = gson.fromJson(message, User.class);
	// logger.info(String.format("#### -> Consumed usuario -> %s", usuario));
	//
	// }

	@Scheduled(fixedRate = 15000)
	public void testeConsumo() {
		
		ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
		System.out.println("Records " + records.count());
		
		Map<TopicPartition, OffsetAndMetadata> commitMessage = new HashMap<>();

		for (ConsumerRecord<String, String> record : records) {
			
			System.out.println("lendo mensagem : " + record.value());
			commitMessage.put(new TopicPartition(record.topic(), record.partition()), new OffsetAndMetadata(record.offset() + 1));

			kafkaConsumer.commitSync(commitMessage);
			System.out.println(new Date().toString());
		}
		
	}

}
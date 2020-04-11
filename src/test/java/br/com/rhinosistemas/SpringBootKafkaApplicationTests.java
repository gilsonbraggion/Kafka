package br.com.rhinosistemas;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootKafkaApplicationTests {

	@Test
	void contextLoads() {
	}

	
	
	/**
	 * Para realizar o start do Kafka: 
	 * 
	 * ./zookeeper-server-start.sh ../config/zookeeper.properties 
	 * 
	 * ./kafka-server-start.sh ../config/server.properties 
	 * 
	 */
	
	/**
	 * Para criar o tópico no Kakfa: 
	 * 
	 * $ kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test 
	 * 
	 */
	
	
}

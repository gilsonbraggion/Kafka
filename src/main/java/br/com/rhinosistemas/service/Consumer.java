package br.com.rhinosistemas.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.com.rhinosistemas.model.User;

@Service
public class Consumer {
	
	private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

	@KafkaListener(topics = "usersDireto", groupId = "group_id")
	public void consume(String message) throws IOException {
		// logger.info(String.format("#### -> Consumed message -> %s",
		// message));

		Gson gson = new Gson();
		User usuario = gson.fromJson(message, User.class);
		logger.info(String.format("#### -> Consumed usuario -> %s", usuario));

	}

}
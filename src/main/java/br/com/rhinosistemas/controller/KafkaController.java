package br.com.rhinosistemas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.rhinosistemas.model.User;
import br.com.rhinosistemas.service.Producer;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

	@Autowired
	private Producer producer;

	@PostMapping(value = "/publish")
	public void sendMessageToKafkaTopic(@RequestBody User user) {

		Gson gson = new Gson();

		String jsonUser = gson.toJson(user);
		this.producer.sendMessage(jsonUser);
	}
}
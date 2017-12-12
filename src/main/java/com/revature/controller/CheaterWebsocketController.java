package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.CheaterWebSocket;

@RestController
@RequestMapping("websocket")
//need allowCredentials, but won't need origins after bundling
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
public class CheaterWebsocketController {

	@Autowired
	CheaterWebSocket cws;
	
	@GetMapping
	public List<String> getChat() {
		return cws.getChat();
	}
	@PostMapping("chat")
	public List<String> AddToChat(@RequestBody String chat) {
		cws.addToChat(chat);
		return cws.getChat();
	}
}

package com.conor.bootbot.api.controller;

//import com.conor.bootbot.api.model.Chat;
import com.conor.bootbot.service.ChatService;
//import com.conor.bootbot.service.PromptService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChatController {

    private ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService){
        this.chatService = chatService;
    }

    @PostMapping("/chat")
    public String getChat(@RequestBody String message){
        return chatService.sendMessage(message);
    }


}

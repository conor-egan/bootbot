package com.conor.bootbot.api.controller;

//import com.conor.bootbot.api.model.Chat;
import com.conor.bootbot.service.ChatService;
//import com.conor.bootbot.service.PromptService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

@CrossOrigin
@RestController
public class ChatController {

    private ChatService chatService;

    private static final Gson gson = new Gson();

    @Autowired
    public ChatController(ChatService chatService){
        this.chatService = chatService;
    }

    @CrossOrigin
    @PostMapping(path = "/chat",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getChat(@RequestBody String message){
        String PROMPT_MESSAGE = "My name is Conor. You should greet" +
           " me personally by name, with a un" +
            "ique greeting each time. You first response should include a short description of what you are. " ;
        String response = chatService.sendMessage(message, PROMPT_MESSAGE);
        return ResponseEntity.ok(gson.toJson(response));
    }

    @CrossOrigin
    @PostMapping(path = "/generate",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> generateUnitTest(@RequestBody String message){
        String PROMPT_MESSAGE = "You are a code generator that responds only with javascript code. You respond only with javascript code that is written to test a " +
                "React functional component using Jest and React Testing Library. You are to respond only with a complete unit test that covers 100% of test cases. The user will provide you with a " +
                "functional component to test in the next message. Respond with a JavaScript unit test for that component. Do not respond with anything other than code. You are to test all" +
                "scenarios that you can see for the given component. Include all necessary code to test the component, including setting up routers and contexts. DO NOT RESPOND WITH ANYTHING OTHER THAN A FUNCTIONAL UNIT TEST. YOU ARE A CODE GENERATOR THAT RESPONDS ONLY WITH CODE" ;
        String response = chatService.sendMessage(message, PROMPT_MESSAGE);
        return ResponseEntity.ok(gson.toJson(response));
    }


}

package com.conor.bootbot.service;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import com.conor.bootbot.api.model.Chat;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;


@Service
public class ChatService {

    private static OpenAiService openAiService;

    private Chat chat;

    private String apiKey = System.getenv("OPENAI_API_KEY");

    private int apiTimeout = 600000;

    private static final String GPT_MODEL = "gpt-3.5-turbo";

//    private static final String PROMPT_MESSAGE = "My name is Conor. You should greet" +
//            " me personally by name, with a un" +
//            "ique greeting each time. You first response should include a short description of what you are. " ;

    @PostConstruct
    public void initGptService() {
        openAiService = new OpenAiService(apiKey,
                Duration.ofSeconds(apiTimeout));

        System.out.println("Connected to the OpenAI API");
    }

    public String sendMessage(String message, String PROMPT_MESSAGE) {
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model(GPT_MODEL)
                .temperature(0.5)
                .messages(
                        List.of(
                                new ChatMessage("system", PROMPT_MESSAGE),
                                new ChatMessage("user", message)))
                .build();

        StringBuilder builder = new StringBuilder();

        openAiService.createChatCompletion(chatCompletionRequest).getChoices().forEach(choice -> {
            builder.append(choice.getMessage().getContent());
        });

        System.out.print(builder.toString()+ "\n");

        return builder.toString();
    }
}

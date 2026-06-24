package com.shivang.notification_service.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiReminderService {

    private final ChatClient chatClient;

    public AiReminderService(
            ChatClient.Builder builder) {

        this.chatClient = builder.build();
    }

    public String generateReminder(
            String captain,
            String team,
            String tournament) {

        return chatClient.prompt()
                .user("""
                        Generate a reminder using:
                        
                        Captain: %s
                        Team: %s
                        Tournament: %s
                        
                        Do not invent date, time or venue if they are unavailable.
                        """
                        .formatted(
                                captain,
                                team,
                                tournament))
                .call()
                .content();
    }
}
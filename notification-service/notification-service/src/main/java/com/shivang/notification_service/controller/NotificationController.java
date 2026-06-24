package com.shivang.notification_service.controller;

import com.shivang.notification_service.service.AiReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {

    private final AiReminderService aiReminderService;

    @GetMapping("/test")
    public String test() {

        return aiReminderService.generateReminder(
                "Shivang",
                "AIT Warriors",
                "AIT Squash Championship"
        );
    }
}
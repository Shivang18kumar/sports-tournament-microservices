package com.shivang.notification_service.scheduler;

import com.shivang.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReminderScheduler {

    private final NotificationService notificationService;

    @Scheduled(cron = "*/1 * * * * *")
    public void sendReminder() {

        notificationService.generateReminder();
    }
}
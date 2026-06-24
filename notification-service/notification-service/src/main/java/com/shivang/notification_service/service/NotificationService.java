package com.shivang.notification_service.service;

import com.shivang.notification_service.dto.TeamRegistrationResponse;
import com.shivang.notification_service.dto.TeamResponse;
import com.shivang.notification_service.dto.TournamentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final RestTemplate restTemplate;
    private final AiReminderService aiReminderService;

    public void generateReminder() {

        ResponseEntity<List<TeamRegistrationResponse>> response =
                restTemplate.exchange(
                        "http://TEAM-SERVICE/teams/registrations",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {}
                );

        List<TeamRegistrationResponse> registrations =
                response.getBody();

        if (registrations == null ||
                registrations.isEmpty()) {

            System.out.println(
                    "No registrations found."
            );

            return;
        }

        for (TeamRegistrationResponse registration
                : registrations) {

            TeamResponse team =
                    restTemplate.getForObject(
                            "http://TEAM-SERVICE/teams/"
                                    + registration.getTeamId(),
                            TeamResponse.class
                    );

            TournamentResponse tournament =
                    restTemplate.getForObject(
                            "http://TOURNAMENT-SERVICE/tournaments/"
                                    + registration.getTournamentId(),
                            TournamentResponse.class
                    );

            String reminder =
                    aiReminderService.generateReminder(
                            team.getCaptainEmail(),
                            team.getTeamName(),
                            tournament.getName()
                    );

            System.out.println(
                    "\n=================================\n"
                            + "TEAM ID : "
                            + registration.getTeamId()
                            + "\n"
                            + reminder
                            + "\n=================================\n"
            );
        }
    }
}
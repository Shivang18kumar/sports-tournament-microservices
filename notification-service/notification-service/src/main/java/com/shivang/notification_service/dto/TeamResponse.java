package com.shivang.notification_service.dto;

import lombok.Data;

@Data
public class TeamResponse {

    private Long id;
    private String teamName;
    private String captainEmail;
    private Long tournamentId;
}
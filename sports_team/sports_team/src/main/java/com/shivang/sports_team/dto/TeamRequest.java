package com.shivang.sports_team.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TeamRequest {

    @NotBlank(message = "Team name is required")
    private String teamName;

    private Long tournamentId;
}
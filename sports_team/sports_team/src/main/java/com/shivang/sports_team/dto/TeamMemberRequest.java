package com.shivang.sports_team.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TeamMemberRequest {

    private Long teamId;

    @NotBlank
    private String playerEmail;
}
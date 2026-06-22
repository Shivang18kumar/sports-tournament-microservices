package com.shivang.tournament_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateTournamentRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String sport;

    @NotBlank
    private String location;

    private LocalDate startDate;

    private LocalDate endDate;
}
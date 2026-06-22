package com.shivang.tournament_service.controller;

import com.shivang.tournament_service.dto.CreateTournamentRequest;
import com.shivang.tournament_service.entity.Tournament;
import com.shivang.tournament_service.service.TournamentService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {

    private final TournamentService service;

    public TournamentController(
            TournamentService service) {
        this.service = service;
    }

    @PreAuthorize(
            "hasRole('ADMIN') or hasRole('ORGANIZER')"
    )
    @PostMapping
    public Tournament createTournament(
            @Valid
            @RequestBody
            CreateTournamentRequest request) {

        return service.createTournament(request);
    }

    @GetMapping
    public List<Tournament> getAllTournaments() {
        return service.getAllTournaments();
    }

    @GetMapping("/{id}")
    public Tournament getTournamentById(
            @PathVariable Long id) {

        return service.getTournamentById(id);
    }

    @GetMapping("/whoami")
    public String whoAmI(
            @RequestHeader(
                    value = "X-User-Email",
                    required = false)
            String email) {

        return email;
    }

}
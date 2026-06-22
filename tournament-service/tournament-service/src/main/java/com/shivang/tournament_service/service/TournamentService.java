package com.shivang.tournament_service.service;

import com.shivang.tournament_service.dto.CreateTournamentRequest;
import com.shivang.tournament_service.entity.Tournament;
import com.shivang.tournament_service.entity.TournamentStatus;
import com.shivang.tournament_service.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService {

    private final TournamentRepository repository;

    public TournamentService(
            TournamentRepository repository) {
        this.repository = repository;
    }

//    public Tournament createTournament(
//            Tournament tournament) {
//
//        return repository.save(tournament);
//    }
    public Tournament createTournament(
            CreateTournamentRequest request) {

        Tournament tournament =
                Tournament.builder()
                        .name(request.getName())
                        .sport(request.getSport())
                        .location(request.getLocation())
                        .startDate(request.getStartDate())
                        .endDate(request.getEndDate())
                        .status(TournamentStatus.UPCOMING)
                        .createdBy("SYSTEM")
                        .build();

        return repository.save(tournament);
    }

    public List<Tournament> getAllTournaments() {
        return repository.findAll();
    }

    public Tournament getTournamentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Tournament Not Found"));
    }
}
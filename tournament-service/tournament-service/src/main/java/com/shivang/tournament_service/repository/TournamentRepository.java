package com.shivang.tournament_service.repository;

import com.shivang.tournament_service.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository
        extends JpaRepository<Tournament, Long> {
}
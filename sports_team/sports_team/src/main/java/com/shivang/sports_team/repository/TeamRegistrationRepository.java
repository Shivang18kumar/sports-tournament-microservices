package com.shivang.sports_team.repository;

import com.shivang.sports_team.entity.TeamRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRegistrationRepository
        extends JpaRepository<TeamRegistration, Long> {

    boolean existsByTeamIdAndTournamentId(
            Long teamId,
            Long tournamentId
    );
}
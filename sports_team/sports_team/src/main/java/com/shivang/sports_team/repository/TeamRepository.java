package com.shivang.sports_team.repository;

import com.shivang.sports_team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
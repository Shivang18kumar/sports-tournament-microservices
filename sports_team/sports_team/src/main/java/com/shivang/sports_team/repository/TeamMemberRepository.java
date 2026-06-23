package com.shivang.sports_team.repository;

import com.shivang.sports_team.entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {

    List<TeamMember> findByTeamId(Long teamId);

    boolean existsByTeamIdAndPlayerEmail(
            Long teamId,
            String playerEmail
    );
}
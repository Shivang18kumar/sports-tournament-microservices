
package com.shivang.sports_team.controller;

import com.shivang.sports_team.dto.TeamRequest;
import com.shivang.sports_team.entity.Team;
import com.shivang.sports_team.entity.TeamMember;
import com.shivang.sports_team.entity.TeamRegistration;
import com.shivang.sports_team.service.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    @PreAuthorize("hasRole('PLAYER')")
    public Team createTeam(
            @RequestBody @Valid TeamRequest request,
            Authentication authentication) {

        String email = authentication.getName();

        return teamService.createTeam(
                request,
                email
        );
    }
    @PostMapping("/{teamId}/join")
    @PreAuthorize("hasRole('PLAYER')")
    public TeamMember joinTeam(
            @PathVariable Long teamId,
            Authentication authentication) {

        String email = authentication.getName();

        return teamService.joinTeam(
                teamId,
                email
        );
    }

    @GetMapping("/{teamId}/members")
    public List<TeamMember> getMembers(
            @PathVariable Long teamId) {

        return teamService.getTeamMembers(teamId);
    }


    @PostMapping("/{teamId}/register/{tournamentId}")
    @PreAuthorize("hasRole('PLAYER')")
    public TeamRegistration registerTeam(
            @PathVariable Long teamId,
            @PathVariable Long tournamentId) {

        return teamService.registerTeam(
                teamId,
                tournamentId
        );
    }



}
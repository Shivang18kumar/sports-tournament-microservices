package com.shivang.sports_team.service;

import com.shivang.sports_team.dto.TeamRequest;
import com.shivang.sports_team.dto.TournamentResponse;
import com.shivang.sports_team.entity.Team;
import com.shivang.sports_team.entity.TeamMember;
import com.shivang.sports_team.entity.TeamRegistration;
import com.shivang.sports_team.exception.BadRequestException;
import com.shivang.sports_team.exception.ResourceNotFoundException;
import com.shivang.sports_team.repository.TeamMemberRepository;
import com.shivang.sports_team.repository.TeamRegistrationRepository;
import com.shivang.sports_team.repository.TeamRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final RestTemplate restTemplate;
    private final TeamRegistrationRepository teamRegistrationRepository;


    public Team createTeam(
            TeamRequest request,
            String captainEmail) {
        try {

            TournamentResponse tournament =
                    restTemplate.getForObject(
                            "http://TOURNAMENT-SERVICE/tournaments/" +
                                    request.getTournamentId(),
                            TournamentResponse.class
                    );

            System.out.println("Tournament Found = " + tournament);

        } catch (Exception e) {

            e.printStackTrace();

            throw new ResourceNotFoundException(
                    "Tournament not found"
            );
        }

        Team team = Team.builder()
                .teamName(request.getTeamName())
                .captainEmail(captainEmail)
                .tournamentId(request.getTournamentId())
                .build();

        return teamRepository.save(team);
    }

    public TeamMember joinTeam(Long teamId,
                               String playerEmail) {

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() ->
                   new ResourceNotFoundException("Team not found"));

        if(teamMemberRepository
                .existsByTeamIdAndPlayerEmail(
                        teamId,
                        playerEmail)) {

            throw new BadRequestException("Player already joined this team");
        }

        TeamMember member = TeamMember.builder()
                .teamId(teamId)
                .playerEmail(playerEmail)
                .build();

        return teamMemberRepository.save(member);
    }

    public List<TeamMember> getTeamMembers(Long teamId) {
        return teamMemberRepository.findByTeamId(teamId);
    }

    public TeamRegistration registerTeam(
            Long teamId,
            Long tournamentId) {

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Team not found"));

        try {

            restTemplate.getForObject(
                    "http://TOURNAMENT-SERVICE/tournaments/" +
                            tournamentId,
                    TournamentResponse.class
            );

        } catch (Exception e) {

            throw new ResourceNotFoundException(
                    "Tournament not found");
        }

        if (teamRegistrationRepository
                .existsByTeamIdAndTournamentId(
                        teamId,
                        tournamentId)) {

            throw new BadRequestException(
                    "Team already registered");
        }

        TeamRegistration registration =
                TeamRegistration.builder()
                        .teamId(teamId)
                        .tournamentId(tournamentId)
                        .build();

        return teamRegistrationRepository
                .save(registration);
    }

    public Team getTeam(Long teamId) {

        return teamRepository.findById(teamId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Team not found"
                        ));
    }

    public List<TeamRegistration> getAllRegistrations() {

        return teamRegistrationRepository.findAll();
    }



}
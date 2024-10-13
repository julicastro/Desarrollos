package com.challenge.app.challenge.service.impl;

import com.challenge.app.challenge.dto.TeamDto;
import com.challenge.app.challenge.exception.TeamNotFoundException;
import com.challenge.app.challenge.exception.TeamNotSavedException;
import com.challenge.app.challenge.perseistence.entity.Team;
import com.challenge.app.challenge.perseistence.repository.TeamRepository;
import com.challenge.app.challenge.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Team createOne(TeamDto dto) {
        Team team = new Team();
        return teamMapper(dto, team);
    }

    @Override
    public Team updateOneById(Long id, TeamDto dto) {
        Team team = teamRepository.findById(id).orElseThrow(TeamNotFoundException::new);
        return teamMapper (dto, team);
    }

    private Team teamMapper(TeamDto dto, Team team) {
        team.setName(dto.getName());
        team.setLeague(dto.getLeague());
        team.setCountry(dto.getCountry());
        try {
            teamRepository.save(team);
        } catch (Exception e) {
            throw new TeamNotSavedException();
        }
        return team;
    }




}

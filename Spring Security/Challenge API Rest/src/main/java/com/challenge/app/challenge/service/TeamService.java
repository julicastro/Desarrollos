package com.challenge.app.challenge.service;

import com.challenge.app.challenge.dto.TeamDto;
import com.challenge.app.challenge.perseistence.entity.Team;

public interface TeamService {

    Team createOne(TeamDto dto);

    Team updateOneById(Long id, TeamDto dto);

}

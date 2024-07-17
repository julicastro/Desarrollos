package com.challenge.app.challenge.service.impl;

import com.challenge.app.challenge.dto.EquipoDto;
import com.challenge.app.challenge.exception.TeamNotFoundException;
import com.challenge.app.challenge.exception.TeamNotSavedException;
import com.challenge.app.challenge.perseistence.entity.Equipo;
import com.challenge.app.challenge.perseistence.repository.EquipoRepository;
import com.challenge.app.challenge.service.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipoServiceImpl implements EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    @Override
    public Equipo createOne(EquipoDto dto) {
        Equipo team = new Equipo();
        return teamMapper(dto, team);
    }

    @Override
    public Equipo updateOneById(Long id, EquipoDto dto) {
        Equipo team = equipoRepository.findById(id).orElseThrow(() -> new TeamNotFoundException("Equipo no encontrado"));
        return teamMapper (dto, team);
    }

    private Equipo teamMapper(EquipoDto dto, Equipo team) {
        team.setNombre(dto.getNombre());
        team.setLiga(dto.getLiga());
        team.setPais(dto.getPais());
        try {
            equipoRepository.save(team);
        } catch (Exception e) {
            throw new TeamNotSavedException("La solicitud es inválida");
        }
        return team;
    }




}

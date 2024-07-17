package com.challenge.app.challenge.service;

import com.challenge.app.challenge.dto.EquipoDto;
import com.challenge.app.challenge.perseistence.entity.Equipo;

public interface EquipoService {

    Equipo createOne(EquipoDto dto);

    Equipo updateOneById(Long id, EquipoDto dto);

}

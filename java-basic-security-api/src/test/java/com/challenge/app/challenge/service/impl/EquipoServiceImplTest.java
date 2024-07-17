package com.challenge.app.challenge.service.impl;

import com.challenge.app.challenge.dto.EquipoDto;
import com.challenge.app.challenge.exception.TeamNotFoundException;
import com.challenge.app.challenge.exception.TeamNotSavedException;
import com.challenge.app.challenge.perseistence.entity.Equipo;
import com.challenge.app.challenge.perseistence.entity.User;
import com.challenge.app.challenge.perseistence.repository.EquipoRepository;
import com.challenge.app.challenge.perseistence.util.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EquipoServiceImplTest {

    @Mock
    private EquipoRepository equipoRepository;

    @InjectMocks
    private EquipoServiceImpl equipoService;

    private User testUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createOne_shouldCreateNewTeam() {
        EquipoDto dto = new EquipoDto();
        dto.setNombre("Equipo Test");
        dto.setLiga("Liga Test");
        dto.setPais("País Test");

        Equipo equipoGuardado = new Equipo();
        equipoGuardado.setNombre("Equipo Test");
        equipoGuardado.setLiga("Liga Test");
        equipoGuardado.setPais("País Test");

        when(equipoRepository.save(any(Equipo.class))).thenAnswer(invocation -> {
            Equipo equipo = invocation.getArgument(0);
            equipo.setId(1L); /* simulo un repositorio real que asigna manualmente un ID */
            return equipo;
        });

        Equipo result = equipoService.createOne(dto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Equipo Test", result.getNombre());

        verify(equipoRepository, times(1)).save(any(Equipo.class));
    }

    @Test
    void createOne_shouldThrowTeamNotSavedException() {
        EquipoDto dto = new EquipoDto();
        /* lleno los datos para que no salten las validaciones */
        dto.setNombre("Test");
        dto.setLiga("Test");
        dto.setPais("Test");

        when(equipoRepository.save(any(Equipo.class))).thenThrow(new RuntimeException("Error al guardar"));

        assertThrows(TeamNotSavedException.class, () -> equipoService.createOne(dto));
    }

    /*

    @Test
    void updateOneById_shouldUpdateExistingTeam() {
        Long id = 1L;
        EquipoDto dto = new EquipoDto();
        dto.setNombre("Nuevo Nombre");
        dto.setLiga("Nueva Liga");
        dto.setPais("Nuevo País");

        Equipo existingTeam = new Equipo();
        existingTeam.setId(id);
        existingTeam.setNombre("Equipo Antiguo");
        existingTeam.setLiga("Liga Antigua");
        existingTeam.setPais("País Antiguo");

        when(equipoRepository.findById(id)).thenReturn(Optional.of(existingTeam));
        when(equipoRepository.save(any(Equipo.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Equipo result = equipoService.updateOneById(id, dto);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Nuevo Nombre", result.getNombre());
        assertEquals("Nueva Liga", result.getLiga());
        assertEquals("Nuevo País", result.getPais());

        verify(equipoRepository, times(1)).findById(id);
        verify(equipoRepository, times(1)).save(any(Equipo.class));
    }

    @Test
    void updateOneById_shouldThrowTeamNotFoundException() {
        Long id = 1L;
        EquipoDto dto = new EquipoDto();

        when(equipoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(TeamNotFoundException.class, () -> equipoService.updateOneById(id, dto));
    }

    */
}
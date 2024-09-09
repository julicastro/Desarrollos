package com.challenge.app.challenge.controller;

import com.challenge.app.challenge.dto.TeamDto;
import com.challenge.app.challenge.exception.TeamNotFoundException;
import com.challenge.app.challenge.exception.TeamNotSavedException;
import com.challenge.app.challenge.perseistence.entity.Team;
import com.challenge.app.challenge.perseistence.repository.TeamRepository;
import com.challenge.app.challenge.service.TeamService;
import jakarta.validation.Valid;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamService teamService;

    @GetMapping
    public ResponseEntity<Page<Team>> findAll(Pageable pageable) {
        Page<Team> teams = teamRepository.findAll(pageable);
        if (teams.hasContent()) {
            return ResponseEntity.ok(teams);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOneById(@PathVariable Long id) {
        Team team = teamRepository.findById(id).orElseThrow(TeamNotSavedException::new);
        return ResponseEntity.ok(team);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Team>> findAllByName(@RequestParam String name, Pageable pageable) {
        Page<Team> teams = teamRepository.findByNameContaining(name, pageable);
        if (teams.hasContent()) {
            return ResponseEntity.ok(teams);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Object> createOne(@RequestBody @Valid TeamDto dto) {
        Team team = teamService.createOne(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(team);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOneById(@PathVariable Long id, @RequestBody @Valid TeamDto dto) {
        Team team = teamService.updateOneById(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(team);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable Long id) {
        Team team = teamRepository.findById(id).orElseThrow(TeamNotFoundException::new);
        teamRepository.delete(team);
        return ResponseEntity.noContent().build();
    }

}

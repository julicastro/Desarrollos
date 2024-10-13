package com.challenge.app.challenge.perseistence.repository;

import com.challenge.app.challenge.perseistence.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Page<Team> findByNameContaining(String name, Pageable pageable);

}

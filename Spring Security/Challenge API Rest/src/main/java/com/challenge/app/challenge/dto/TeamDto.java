package com.challenge.app.challenge.dto;

import jakarta.validation.constraints.NotBlank;

public class TeamDto {

    @NotBlank
    private String name;

    @NotBlank
    private String league;

    @NotBlank
    private String country;

    public TeamDto(String name, String league, String country) {
        this.name = name;
        this.league = league;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

package com.iwaconsolti.league.demo.service;

import com.iwaconsolti.league.demo.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemoService {

    private final List<String> names = new ArrayList<>();

    private final TeamRepository teamRepository;

    public void insertName(final String name){
        names.add(name);
    }
    public List<String> getNames(){
        return names;
    }


    @Autowired
    public DemoService(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    public String getAllTeams() {
        return teamRepository.getTeams();
    }
}

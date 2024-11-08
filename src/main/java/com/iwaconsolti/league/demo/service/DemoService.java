package com.iwaconsolti.league.demo.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemoService {

    private final List<String> names = new ArrayList<>();

    public void insertName(final String name){
        names.add(name);
    }

    public List<String> getNames(){
        return names;
    }
}

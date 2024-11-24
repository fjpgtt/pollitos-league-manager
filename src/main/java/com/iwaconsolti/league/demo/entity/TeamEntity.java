package com.iwaconsolti.league.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="teams")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int score;
    @OneToMany(mappedBy = "team")
    private List<PlayerEntity> players = new ArrayList<>();


    public TeamEntity(long id, String name, int score) {
    }
}

package com.iwaconsolti.league.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="players")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;

    @Column(name="name", nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="team_id",nullable = true)
    private TeamEntity team;
}

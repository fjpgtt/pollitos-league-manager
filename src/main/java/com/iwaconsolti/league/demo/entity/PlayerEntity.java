package com.iwaconsolti.league.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="players")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String team;
}

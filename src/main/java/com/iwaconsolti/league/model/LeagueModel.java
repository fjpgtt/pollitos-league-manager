package com.iwaconsolti.league.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "league")
public class LeagueModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idLeague")
    private int idLeague;

    @Column(name = "nameleague")
    private String nameleague;



}

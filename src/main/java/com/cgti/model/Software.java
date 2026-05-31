package com.cgti.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Software {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreSoft;

    @ManyToMany
    @JoinTable(
        name = "equipo_software",
        joinColumns = @JoinColumn(name = "software_id"),
        inverseJoinColumns = @JoinColumn(name = "equipo_id")
    )
    private List<Equipo> equipos;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreSoft() { return nombreSoft; }
    public void setNombreSoft(String nombreSoft) { this.nombreSoft = nombreSoft; }

    public List<Equipo> getEquipos() { return equipos; }
    public void setEquipos(List<Equipo> equipos) { this.equipos = equipos; }
}

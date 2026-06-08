package com.cgti.model; // pertenece al paquete 

import jakarta.persistence.*; // permite mapero en base de datos
import java.util.List; // permite usar lista 

@Entity // crea la entidad en base de datos
public class Software { // crea la clase 

    @Id // llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // genera la llave primaria
    private Long id;

    private String nombreSoft;

    @ManyToMany // muchos a muchos
    @JoinTable(                                       //*genera una tabla de relaxcion entre sofware y equipo donde se intercambian las llaves primarias *//
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

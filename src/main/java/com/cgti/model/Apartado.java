package com.cgti.model; // pertenece a pacquete

import jakarta.persistence.*; // permite mapeo en base de datos
import java.time.LocalDateTime; // libreria importada para las fechas

@Entity // nombre en base de datos
public class Apartado {

    @Id //  clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // el valor se genera automáticamente por la base de datos
    private Long id;

    @ManyToOne // mucho a uno 
    private Usuario usuario; // muchos apartados pueden pertenecer a un usuario

    @ManyToOne
    private Laboratorio laboratorio; // muchos apartados pueden estar asociados al mismo laboratorio
    
    @ManyToOne
    private Equipo equipo; // muchos apartados pueden pertenecer a un equi´po

    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String proposito;

    @Enumerated(EnumType.STRING) //  el enum EstadoApartado se almacena como String en la base de datos 
    private EstadoApartado estado;

    // geters y seters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Laboratorio getLaboratorio() { return laboratorio; }
    public void setLaboratorio(Laboratorio laboratorio) { this.laboratorio = laboratorio; }

    public Equipo getEquipo() { return equipo; }
    public void setEquipo(Equipo equipo) { this.equipo = equipo; }

    public LocalDateTime getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDateTime fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDateTime getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDateTime fechaFin) { this.fechaFin = fechaFin; }

    public String getProposito() { return proposito; }
    public void setProposito(String proposito) { this.proposito = proposito; }

    public EstadoApartado getEstado() { return estado; }
    public void setEstado(EstadoApartado  estado) { this.estado =  estado; }
}

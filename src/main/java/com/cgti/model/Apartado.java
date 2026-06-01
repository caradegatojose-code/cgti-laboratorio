package com.cgti.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Apartado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Laboratorio laboratorio;

    @ManyToOne
    @JoinColumn(nullable = true)
    private Equipo equipo;

    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String proposito;

    @Enumerated(EnumType.STRING)
    private EstadoApartado estado;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario u) { this.usuario = u; }

    public Laboratorio getLaboratorio() { return laboratorio; }
    public void setLaboratorio(Laboratorio l) { this.laboratorio = l; }

    public Equipo getEquipo() { return equipo; }
    public void setEquipo(Equipo e) { this.equipo = e; }

    public LocalDateTime getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDateTime f) { this.fechaInicio = f; }

    public LocalDateTime getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDateTime f) { this.fechaFin = f; }

    public String getProposito() { return proposito; }
    public void setProposito(String p) { this.proposito = p; }

    public EstadoApartado getEstado() { return estado; }
    public void setEstado(EstadoApartado e) { this.estado = e; }
}

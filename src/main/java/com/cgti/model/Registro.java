package com.cgti.model; // pertenece al paquete 

import jakarta.persistence.*; // permite el mapeo en base de datos
import java.time.LocalDateTime; // 

@Entity // nombre en base de datos
public class Registro {

    @Id // valor primario
    @GeneratedValue(strategy = GenerationType.IDENTITY) // genera autoaticamente el valor
    private Long id;

    private LocalDateTime horaEntrada;
    private LocalDateTime horaSalida;
    private String proposito;

    @ManyToOne // uno a muchos
    private Laboratorio laboratorio; // un registro pertenece a un lavoratorii

    @ManyToOne
    private Usuario usuario; // un registro pertenece a un usuario

    // getters y setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getHoraEntrada() { return horaEntrada; }
    public void setHoraEntrada(LocalDateTime horaEntrada) { this.horaEntrada = horaEntrada; }

    public LocalDateTime getHoraSalida() { return horaSalida; }
    public void setHoraSalida(LocalDateTime  horaSalida) { this.horaSalida =  horaSalida; }

    public String getProposito() { return proposito; }
    public void setProposito(String proposito) { this.proposito = proposito; }

    public Laboratorio getLaboratorio() { return plaboratorio; }
    public void setLaboratorio(Laboratorio laboratorio) { this.laboratorio = laboratorio; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario  usuario) { this.usuario =  usuario; }
}

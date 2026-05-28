package com.cgti.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombrePaterno;
    private String nombreMaterno;
    private String nombre;
    private LocalDate fechaNacimiento;

    @Column(nullable = true)
    private String telefono;

    @Column(unique = true, nullable = false)
    private String correo;

    private String contrasena;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Transient
    public int getEdad() {
        return Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombrePaterno() { return nombrePaterno; }
    public void setNombrePaterno(String n) { this.nombrePaterno = n; }

    public String getNombreMaterno() { return nombreMaterno; }
    public void setNombreMaterno(String n) { this.nombreMaterno = n; }

    public String getNombre() { return nombre; }
    public void setNombre(String n) { this.nombre = n; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate f) { this.fechaNacimiento = f; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String t) { this.telefono = t; }

    public String getCorreo() { return correo; }
    public void setCorreo(String c) { this.correo = c; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String c) { this.contrasena = c; }

    public Rol getRol() { return rol; }
    public void setRol(Rol r) { this.rol = r; }
}

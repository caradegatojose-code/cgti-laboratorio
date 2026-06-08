package com.cgti.model; // pertenece al paquete 

import jakarta.persistence.*; // mapeo en base de datos
import java.time.LocalDate;
import java.time.Period;

@Entity // crea la entidad en base de datos
@Inheritance(strategy = InheritanceType.JOINED) //iene su propia tabla, y cada subclase tiene su propia tabla adicional, vinculada por clave foránea "el es el padre "
    
public class Usuario { // crea la clase Usuario

    @Id // clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // se genera automaticamnete la cave primaria
    private Long id;

    private String nombrePaterno;
    private String nombreMaterno;
    private String nombre;
    private LocalDate fechaNacimiento;

    @Column(nullable = true) // no es obligatorio el numero de telefono 
    private String telefono;

    @Column(unique = true, nullable = false) // es obligatorio el correo y tiene que ser unico
    private String correo;

    private String contrasena;

    @Enumerated(EnumType.STRING) // en el enum los valores son String
    private Rol rol; // el rol viene del rol

    @Transient // dato calculado no guardado 
    public int getEdad() {
        return Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
    }
    //geters y setters 

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

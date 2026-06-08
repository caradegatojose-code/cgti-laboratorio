package com.cgti.model; // pertenece al paquete 
import jakarta.persistence.*; // permite mapeo en base de datos
import java.util.List; // Importa la interfaz List del paquete se usa para las listas 

@Entity // nombre en base de datos
public class Equipo {
  
  @Id // clave primaria
  @GeneratedValue(strategy = GenerationType.IDENTITY) // genera automaticamente un valor 
  private Long id;
  private String nombreEqp;
  private String observacion; 

  @ManyToOne // uno a muchos
  private Laboratorio laboratorio; // muchos equipos pertenecen a un laboratorio

  @ManyToMany(mappedBy = "equipos") // muchos equipós pertenecen a muchos softwares y relacion inversa el sofware
  private List<Software> softwares;

  public List<Software> getSoftwares() { return softwares; }
  public void setSoftwares(List<Software> softwares) { this.softwares = softwares; }

  public Long getId(){return id;}
  public void setId (Long id){this.id = id ;}

  public String getNombreEqp(){return nombreEqp;}
  public void setNombreEqp(String nombreEqp){this.nombreEqp = nombreEqp;} 

  public String getObservacion() {return observacion;}
  public void setObservacion( String observacion) {this.observacion = observacion ;}
}

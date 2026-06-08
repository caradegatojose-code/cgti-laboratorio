package com.cgti.model; // pertenece al paquete 
import jakarta.persistence.*; // permite mapeo en base de datos
import java.util.List; // Importa la interfaz List del paquete se usa para las listas 

@Entity // n
public class Equipo {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nombreEqp;
  private String observacion; 

  @ManyToOne
  private Laboratorio laboratorio;

  @ManyToMany(mappedBy = "equipos")
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

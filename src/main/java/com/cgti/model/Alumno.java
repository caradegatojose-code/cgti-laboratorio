package com.cgti.model;
import jakarta.persistence.*;

@Entity
public class Alumno extends Usuario {
  
  private String  matricula;
  private int cuatrimestre;
  private String carrera;
  private String grupo;

  public String getGrupo() { return grupo; }
  public void setGrupo(String grupo) { this.grupo = grupo; }
 
 public String getMatricula(){ return matricula; }
  public void setMatricula (String  matricula) {this.matricula = matricula; }

  public int getCuatrimestre(){return cuatrimestre;}
  public void setCuatrimestre (int cuatrimestre){this.cuatrimestre = cuatrimestre;}

  public String  getCarrera(){return carrera;}
  public void setCarrera(String carrera){this.carrera = carrera;}

}

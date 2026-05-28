package com.cgti.model;
import jakarta.persistence.*;

@Entity
public class Docente extends Usuario {
  
  private String numeroEmpleado;
  private String  materia;
  private String grupo;


  public String getNumeroEmpleado() {return numeroEmpleado; }
  public void setNumeroEmpleado(String numeroEmpleado){this.numeroEmpleado = numeroEmpleado ;}

  public String getMateria (){ return materia ;}
  public void setMateria(String  materia){this.materia = materia ;}


  public String getGrupo() { return grupo; }
  public void setGrupo(String grupo) { this.grupo = grupo; }
}

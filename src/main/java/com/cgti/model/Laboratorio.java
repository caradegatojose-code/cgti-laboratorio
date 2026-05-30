package com.cgti.model;

import jakarta.persistence.*;

@Entity
public class Laboratorio {

  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  private Long id;

  private String nombreLab;

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getNombreLab(){return nombreLab;}
  public void setNombreLab(String nombreLab) { this.nombreLab = nombreLab; }
}

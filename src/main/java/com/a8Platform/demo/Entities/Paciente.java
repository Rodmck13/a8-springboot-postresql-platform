package com.a8Platform.demo.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Getter
@Setter
@Entity
public class Paciente {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @NotNull private String nombre;
  @NotNull private String domicilio;
  @NotNull private String fechaNac;
  @NotNull private String phoneNumber;
  private String descripcion;
  @NotNull private Calendar created_at;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getDomicilio() {
    return domicilio;
  }

  public void setDomicilio(String domicilio) {
    this.domicilio = domicilio;
  }

  public String getFechaNac() {
    return fechaNac;
  }

  public void setFechaNac(String fechaNac) {
    this.fechaNac = fechaNac;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Calendar getCreated_at() {
    return created_at;
  }

  public void setCreated_at(Calendar created_at) {
    this.created_at = created_at;
  }
}

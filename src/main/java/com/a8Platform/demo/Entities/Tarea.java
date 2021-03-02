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
public class Tarea {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String usuario;
  @NotNull private String descripcion;
  @NotNull private boolean realizado;;
  @NotNull private Calendar created_at;
}

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
public class Contraste {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @NotNull private Calendar created_at;
  @NotNull private float mililitrosRestantes;
}

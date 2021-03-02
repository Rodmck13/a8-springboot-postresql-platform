package com.a8Platform.demo.Service;

import com.a8Platform.demo.Entities.SalaEspera;
import com.a8Platform.demo.Repository.PacienteRepository;
import com.a8Platform.demo.Repository.SalaEsperaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

@Service
public class SalaEsperaService {

  @Autowired SalaEsperaRepository salaEsperaRepository;
  @Autowired PacienteRepository pacienteRepository;

  public ResponseEntity<String> savePersona(SalaEspera domain) {
    LocalDateTime date = LocalDateTime.now();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    try {
      SalaEspera salaEspera = new SalaEspera();
      salaEspera.setNombre(domain.getNombre());
      salaEspera.setAtendido(false);
      salaEspera.setCreated_at(date.format(dateTimeFormatter));
      salaEspera.setTipoEstudio(domain.getTipoEstudio());
      salaEsperaRepository.save(salaEspera);
      return new ResponseEntity<>(
          "Persona guardada satisfactoriamente en sala de espera", HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
    }
  }

}

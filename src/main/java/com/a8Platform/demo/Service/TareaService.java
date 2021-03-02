package com.a8Platform.demo.Service;

import com.a8Platform.demo.Entities.Tarea;
import com.a8Platform.demo.Repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class TareaService {
  @Autowired TareaRepository tareaRepository;

  public ResponseEntity<String> saveTarea(Tarea domain) {
    Calendar date = Calendar.getInstance();
    try {
      Tarea tarea = new Tarea();
      tarea.setDescripcion(domain.getDescripcion());
      tarea.setRealizado(false);
      tarea.setUsuario(domain.getUsuario());
      tarea.setCreated_at(date);
      tareaRepository.save(tarea);
      return new ResponseEntity<>("Tarea guardada satisfactoriamente", HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
    }
  }

  public ResponseEntity<String> stateChanged(Integer id) {
    Tarea tarea = tareaRepository.findById(id).get();
    if (tarea != null) {
      if (tarea.isRealizado()) {
        try {
          tarea.setUsuario(tarea.getUsuario());
          tarea.setRealizado(false);
          tarea.setDescripcion(tarea.getDescripcion());
          tarea.setCreated_at(tarea.getCreated_at());
          tareaRepository.save(tarea);
          return new ResponseEntity<>("Paciente actualizado satisfactoriamente", HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
      } else {
        try {
          tarea.setUsuario(tarea.getUsuario());
          tarea.setRealizado(true);
          tarea.setDescripcion(tarea.getDescripcion());
          tarea.setCreated_at(tarea.getCreated_at());
          tareaRepository.save(tarea);
          return new ResponseEntity<>("Paciente actualizado satisfactoriamente", HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
      }
    }
    return new ResponseEntity<>("Algo salió mal", HttpStatus.BAD_REQUEST);
  }
}

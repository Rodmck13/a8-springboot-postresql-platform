package com.a8Platform.demo.Controllers;

import com.a8Platform.demo.Entities.Tarea;
import com.a8Platform.demo.Repository.TareaRepository;
import com.a8Platform.demo.Service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/tarea")
public class TareaController {

  @Autowired private TareaService tareaService;
  @Autowired private TareaRepository tareaRepository;

  @PostMapping(path = "/add")
  public ResponseEntity<String> addNewTarea(@RequestBody(required = false) Tarea tarea) {

    try {
      return tareaService.saveTarea(tarea);

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>("Algo salió mal al guardar la tarea", HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(path = "/all")
  public @ResponseBody Iterable<Tarea> getAll() {
    return tareaRepository.findAll();
  }

  @GetMapping(path = "/getById")
  public ResponseEntity<Optional<Tarea>> getById(@RequestParam Integer id) {
    Optional<Tarea> tarea = tareaRepository.findById(id);
    if (tarea.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(tarea);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping(path = "/deleteById")
  public ResponseEntity<String> deleteById(@RequestParam Integer id) {
    try {
      tareaRepository.deleteById(id);
      return new ResponseEntity<>("Tarea eliminada correctamente", HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping(path = "/deleteAll")
  public ResponseEntity<String> deleteAll() {
    try {
      tareaRepository.deleteAll();
      return new ResponseEntity<>("Tareas eliminadas", HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(path = "/stateChanged")
  public ResponseEntity<String> stateChanged(@RequestParam Integer id) {
    try {
      return tareaService.stateChanged(id);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}

package com.a8Platform.demo.Controllers;

import com.a8Platform.demo.Entities.SalaEspera;
import com.a8Platform.demo.Repository.SalaEsperaRepository;
import com.a8Platform.demo.Service.SalaEsperaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/salaEspera")
public class SalaEsperaController {

  @Autowired private SalaEsperaService salaEsperaService;
  @Autowired private SalaEsperaRepository salaEsperaRepository;

  @PostMapping(path = "/add")
  public ResponseEntity<String> addNew(@RequestBody(required = false) SalaEspera salaEspera) {

    try {
      return salaEsperaService.savePersona(salaEspera);

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(
          "Algo salió mal al guardar la persona a la sala de espera", HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(path = "/all")
  public @ResponseBody Iterable<SalaEspera> getAll() {
    return salaEsperaRepository.findAll();
  }

  @DeleteMapping(path = "/deleteAll")
  public ResponseEntity<String> deleteAll() {
    try {
      salaEsperaRepository.deleteAll();
      return new ResponseEntity<>(
          "Personas de sala de espera eliminadas correctamente", HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping(path = "/deleteById")
  public ResponseEntity<String> deleteById(@RequestParam Integer id) {
    try {
      salaEsperaRepository.deleteById(id);
      return new ResponseEntity<>("Paciente en sala de espera eliminado", HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}

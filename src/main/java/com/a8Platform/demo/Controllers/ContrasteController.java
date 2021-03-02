package com.a8Platform.demo.Controllers;

import com.a8Platform.demo.Repository.ContrasteRepository;
import com.a8Platform.demo.Service.ContrasteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/contraste")
public class ContrasteController {

  @Autowired private ContrasteService contrasteService;
  @Autowired private ContrasteRepository contrasteRepository;

  @PostMapping(path = "/recargarContraste")
  public ResponseEntity<String> recargarContraste(
      @RequestBody(required = true) float cantidadContraste) {
    try {
      if (contrasteService.recargarContraste(cantidadContraste)) {
        return new ResponseEntity<>("Recarga de contraste exitosa", HttpStatus.OK);
      } else {
        return new ResponseEntity<>(
            "Algo salió mal al recargar el contraste", HttpStatus.BAD_REQUEST);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping(path = "/restarContraste")
  public ResponseEntity<String> restarContraste(
      @RequestBody(required = true) float cantidadContrasteARestar) {
    try {
      if (contrasteService.restarContraste(cantidadContrasteARestar)) {
        return new ResponseEntity<>("Se restó el contraste satisfactoriamente", HttpStatus.OK);
      } else {
        return new ResponseEntity<>(
            "Algo salió mal al restar la cantidad del contraste", HttpStatus.BAD_REQUEST);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping(path = "/modificarContraste")
  public ResponseEntity<String> modificarContraste(
      @RequestBody(required = true) float cantidadContraste) {
    try {
      if (contrasteService.modificarContraste(cantidadContraste)) {
        return new ResponseEntity<>("Se modificó el contraste satisfactoriamente", HttpStatus.OK);
      } else {
        return new ResponseEntity<>(
            "Algo salió mal al modificar la cantidad del contraste", HttpStatus.BAD_REQUEST);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(path = "/last")
  public ResponseEntity<String> getLAst() {
    try {
      float contrasteActual = contrasteService.getLast();
      String contrasteActualString = String.valueOf(contrasteActual);
      return new ResponseEntity<>(contrasteActualString, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
    }
  }
}

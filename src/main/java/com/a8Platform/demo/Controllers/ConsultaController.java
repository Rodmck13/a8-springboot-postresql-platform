package com.a8Platform.demo.Controllers;

import com.a8Platform.demo.Entities.Consulta;
import com.a8Platform.demo.Entities.ReporteConsulta;
import com.a8Platform.demo.Repository.ConsultaRepository;
import com.a8Platform.demo.Repository.IReporteConsulta;
import com.a8Platform.demo.Service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/consulta")
public class ConsultaController {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private ConsultaService consultaService;

    @PostMapping(path = "/add")
    public ResponseEntity<String> addNewConsulta(@RequestBody Consulta consulta) {
        try {
            return consultaService.saveConsulta(consulta);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                    "Algo salió mal al guardar el registro de consulta", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Consulta> getAll() {
        return consultaRepository.findAll();
    }

    @GetMapping(path = "/getById")
    public ResponseEntity<Optional<Consulta>> getById(@RequestParam Integer id) {
        Optional<Consulta> consulta = consultaRepository.findById(id);
        if (consulta.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(consulta);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/deleteById")
    public ResponseEntity<String> deleteById(@RequestParam Integer id) {
        try {
            consultaRepository.deleteById(id);
            return new ResponseEntity<>("Consulta eliminada correctamente", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/deleteAll")
    public ResponseEntity<String> deleteAll() {
        try {
            consultaRepository.deleteAll();
            return new ResponseEntity<>("Consultas eliminadas", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/getAllByPacienteId")
    public @ResponseBody
    ResponseEntity<List<Consulta>> getAllByPacienteId(@RequestParam Integer id) {
        try {
            List<Consulta> consulta = consultaService.getAllByPacienteId(id);
            if (consulta != null) {
                return new ResponseEntity<>(consulta, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/getReporteConsulta")
    public @ResponseBody
    ResponseEntity<List<IReporteConsulta>> getReporteConsulta(@RequestBody(required = true) ReporteConsulta reporteConsulta) {
        try {
            List<IReporteConsulta> consulta = consultaService.getReporteConsulta(reporteConsulta);
            if (consulta != null) {
                return new ResponseEntity<>(consulta, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
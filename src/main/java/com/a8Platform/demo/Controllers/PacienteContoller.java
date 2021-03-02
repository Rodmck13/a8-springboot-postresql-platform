package com.a8Platform.demo.Controllers;

import com.a8Platform.demo.Entities.Paciente;
import com.a8Platform.demo.Repository.PacienteRepository;
import com.a8Platform.demo.Service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/paciente")
public class PacienteContoller {

    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping(path = "/add")
    public ResponseEntity<String> addNewPaciente(@RequestBody(required = true) Paciente paciente) {

        try {
            return pacienteService.savePaciente(paciente);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Algo salió mal al guardar el paciente", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/all/{pageNo}/{pageSize}")
    public @ResponseBody
    Iterable<Paciente> getAll(@PathVariable int pageNo,
                              @PathVariable int pageSize) {
        return pacienteService.findPaginated(pageNo, pageSize);
    }

    @GetMapping(path = "/allPagesTotal/{pageNo}/{pageSize}")
    public @ResponseBody
    int getTotalPages(@PathVariable int pageNo,
                       @PathVariable int pageSize) {
        return pacienteService.findTotalPages(pageNo, pageSize);
    }

    @GetMapping(path = "/getById")
    public ResponseEntity<Optional<Paciente>> getById(@RequestParam Integer id) {
        Optional<Paciente> patient = pacienteRepository.findById(id);
        if (patient.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(patient);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/updateById")
    public ResponseEntity<String> updateById(
            @RequestParam Integer id, @RequestBody(required = true) Paciente paciente) {
        try {
            return pacienteService.updateById(id, paciente);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/deleteById")
    public ResponseEntity<String> deleteById(@RequestParam Integer id) {
        try {
            pacienteRepository.deleteById(id);
            return new ResponseEntity<>("Paciente eliminado correctamente", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/deleteAll")
    public ResponseEntity<String> deleteAll() {
        try {
            pacienteRepository.deleteAll();
            return new ResponseEntity<>("Pacientes eliminados", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/autoComplete/{text}/{pageNo}/{pageSize}")
    @ResponseBody
    public List<Paciente> autoComplete(@PathVariable String text, @PathVariable int pageNo,
                                       @PathVariable int pageSize ) {
        List<Paciente> pacientes = pacienteService.findByNombreContainingIgnoreCase(text, pageNo, pageSize);
        return pacientes;
    }

    @GetMapping(path = "/getIdFromName")
    public ResponseEntity<Optional<Paciente>> getIdFromName(@RequestParam String name) {
        Optional<Paciente> patient = pacienteRepository.getIdByNombre(name);
        if (patient.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(patient);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

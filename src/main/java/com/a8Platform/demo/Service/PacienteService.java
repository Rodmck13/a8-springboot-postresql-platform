package com.a8Platform.demo.Service;

import com.a8Platform.demo.Entities.Paciente;
import com.a8Platform.demo.Repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    public ResponseEntity<String> savePaciente(Paciente domain) {
        Calendar date = Calendar.getInstance();
        try {
            Paciente paciente = new Paciente();
            paciente.setNombre(domain.getNombre());
            paciente.setDomicilio(domain.getDomicilio());
            paciente.setFechaNac(domain.getFechaNac());
            paciente.setDescripcion(domain.getDescripcion());
            paciente.setPhoneNumber(domain.getPhoneNumber());
            paciente.setCreated_at(date);
            pacienteRepository.save(paciente);
            return new ResponseEntity<>("Paciente guardado satisfactoriamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> updateById(Integer id, Paciente paciente) {
        Paciente obj = pacienteRepository.findById(id).get();
        if (obj != null) {
            try {
                obj.setNombre(paciente.getNombre());
                obj.setDomicilio(paciente.getDomicilio());
                obj.setFechaNac(paciente.getFechaNac());
                obj.setDescripcion(paciente.getDescripcion());
                obj.setPhoneNumber(paciente.getPhoneNumber());
                pacienteRepository.save(obj);
                return new ResponseEntity<>("Paciente actualizado satisfactoriamente", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(
                    "Hubo un problema al modificar el paciente", HttpStatus.BAD_REQUEST);
        }
    }

    public Iterable<Paciente> findPaginated(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Paciente> pagedResult = pacienteRepository.findAll(paging);
        return pagedResult.toList();
    }

    public int findTotalPages(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Paciente> pagedResult = pacienteRepository.findAll(paging);
        return pagedResult.getTotalPages();
    }

    public List<Paciente> findByNombreContainingIgnoreCase(String text, int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Paciente> pagedResult = pacienteRepository.findByNombreContainingIgnoreCase(text, paging);
        return pagedResult.toList();
    }
}

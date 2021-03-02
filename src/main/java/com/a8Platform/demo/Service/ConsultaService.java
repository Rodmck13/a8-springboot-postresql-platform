package com.a8Platform.demo.Service;

import com.a8Platform.demo.Entities.Consulta;
import com.a8Platform.demo.Entities.Paciente;
import com.a8Platform.demo.Entities.ReporteConsulta;
import com.a8Platform.demo.Repository.ConsultaRepository;
import com.a8Platform.demo.Repository.IReporteConsulta;
import com.a8Platform.demo.Repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    ConsultaRepository consultaRepository;
    @Autowired
    PacienteRepository pacienteRepository;
    @Autowired
    ContrasteService contrasteService;

    public ResponseEntity<String> saveConsulta(Consulta domain) {
        Calendar date = Calendar.getInstance();
        try {
            if (isvalidadPaciente(domain.getPacienteId())) {
                Consulta consulta = new Consulta();
                consulta.setDescripcion(domain.getDescripcion());
                consulta.setPacienteId(domain.getPacienteId());
                consulta.setCreated_at(date);
                consulta.setCosto(domain.getCosto());
                consulta.setContraste(domain.getContraste());
                consulta.setImagen(domain.isImagen());
                consulta.setTomografia(domain.isTomografia());
                if (domain.getContraste() == 0) {
                    consultaRepository.save(consulta);
                    return new ResponseEntity<>("Consulta guardada satisfactoriamente", HttpStatus.OK);
                } else {
                    if (domain.getContraste() > 0) {
                        if (contrasteService.restarContraste(domain.getContraste())) {
                            consultaRepository.save(consulta);
                            return new ResponseEntity<>("Consulta guardada satisfactoriamente", HttpStatus.OK);
                        } else
                            return new ResponseEntity<>(
                                    "Algo salió mal al guardar el contraste", HttpStatus.BAD_REQUEST);
                    }
                }
            } else {
                return new ResponseEntity<>("El id del paciente no existe", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Algo salió mal", HttpStatus.BAD_REQUEST);
    }

    public boolean isvalidadPaciente(Integer pacienteId) {
        Optional<Paciente> paciente = pacienteRepository.findById(pacienteId);
        if (paciente.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    public List<Consulta> getAllByPacienteId(Integer id) {
        try {
            List<Consulta> result = consultaRepository.getAllByPacienteId(id);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<IReporteConsulta> getReporteConsulta(ReporteConsulta reporteConsulta) {
        try {
            if (reporteConsulta.getNombre() == null) {
                List<IReporteConsulta> result;
                if (reporteConsulta.getCreated_at() == null) {
                    result = consultaRepository.getReporteConsultaByAllTypes(reporteConsulta.isImagen(), reporteConsulta.isTomografia());
                } else {
                    result = consultaRepository.getReporteConsultaByAllDate(reporteConsulta.getCreated_at());
                }
                return result;
            } else {
                List<IReporteConsulta> result;
                String getAll = "*";
                if(reporteConsulta.getNombre().equals(getAll)){
                    result = consultaRepository.getReporteConsultaAll();
                }else
                {
                    if (reporteConsulta.getCreated_at() == null) {
                        result = consultaRepository.getReporteConsultaByAllTypesAndName(reporteConsulta.isImagen(), reporteConsulta.isTomografia(), reporteConsulta.getNombre());
                    } else {
                        result = consultaRepository.getReporteConsultaByAllDateAndName(reporteConsulta.getCreated_at(), reporteConsulta.getNombre());
                    }
                }
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

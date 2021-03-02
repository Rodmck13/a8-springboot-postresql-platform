package com.a8Platform.demo.Repository;

import com.a8Platform.demo.Entities.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface PacienteRepository extends PagingAndSortingRepository<Paciente, Integer> {
    @Override
    Optional<Paciente> findById(Integer integer);

    Page<Paciente> findByNombreContainingIgnoreCase(String name, Pageable pageable);


    @Query(value = "select * from public.paciente where nombre = :nombre", nativeQuery = true)
    Optional<Paciente> getIdByNombre(String nombre);
}

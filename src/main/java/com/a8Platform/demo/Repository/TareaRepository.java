package com.a8Platform.demo.Repository;

import com.a8Platform.demo.Entities.Consulta;
import com.a8Platform.demo.Entities.Tarea;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TareaRepository extends CrudRepository<Tarea, Integer> {

    @Override
    Optional<Tarea> findById(Integer integer);
}

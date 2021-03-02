package com.a8Platform.demo.Repository;

import com.a8Platform.demo.Entities.Contraste;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ContrasteRepository extends CrudRepository<Contraste, Integer> {

  Optional<Contraste> findTopByOrderByIdDesc();
}

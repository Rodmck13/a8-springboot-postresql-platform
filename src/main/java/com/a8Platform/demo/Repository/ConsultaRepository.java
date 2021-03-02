package com.a8Platform.demo.Repository;

import com.a8Platform.demo.Entities.Consulta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public interface ConsultaRepository extends CrudRepository<Consulta, Integer> {
    @Override
    Optional<Consulta> findById(Integer integer);

    @Query(value = "SELECT c FROM Consulta c where c.pacienteId = :id")
    List<Consulta> getAllByPacienteId(@Param("id") Integer id);

    //All by tipe
    @Query(
            value = "select p.nombre, c.descripcion, c.created_at, c.costo, c.contraste, c.imagen, c.tomografia from paciente p\n" +
                    " inner join consulta c on p.id = c.paciente_id where c.imagen = :imagen and c.tomografia = :tomografia",
            nativeQuery = true)
    List<IReporteConsulta> getReporteConsultaByAllTypes(@Param("imagen") Boolean imagen,
                                                        @Param("tomografia") Boolean tomografia);

    //All By Date
    @Query(
            value = "select p.nombre, c.descripcion, c.created_at, c.costo, c.contraste, c.imagen, c.tomografia from paciente p\n" +
                    " inner join consulta c on p.id = c.paciente_id WHERE CAST(c.created_at AS VARCHAR) like ('%' || :fecha || '%')\n",
            nativeQuery = true)
    List<IReporteConsulta> getReporteConsultaByAllDate(@Param("fecha") String fecha);


    //Name and type
    @Query(
            value = "select p.nombre, c.descripcion, c.created_at, c.costo, c.contraste, c.imagen, c.tomografia from paciente p\n" +
                    "inner join consulta c on p.id = c.paciente_id where c.imagen = :imagen and c.tomografia = :tomografia and p.nombre = :name",
            nativeQuery = true)
    List<IReporteConsulta> getReporteConsultaByAllTypesAndName(@Param("imagen") Boolean imagen,
                                                               @Param("tomografia") Boolean tomografia, @Param("name") String nombre);

    //name and Date
    @Query(
            value = "select p.nombre, c.descripcion, c.created_at, c.costo, c.contraste, c.imagen, c.tomografia from paciente p\n" +
                    " inner join consulta c on p.id = c.paciente_id WHERE CAST(c.created_at AS VARCHAR) like ('%' || :fecha || '%') and p.nombre = :name",
            nativeQuery = true)
    List<IReporteConsulta> getReporteConsultaByAllDateAndName(@Param("fecha") String fecha, @Param("name") String nombre);

    @Query(
            value = "select p.nombre, c.descripcion, c.created_at, c.costo, c.contraste, c.imagen, c.tomografia from paciente p\n" +
                    " inner join consulta c on p.id = c.paciente_id",
            nativeQuery = true)
    List<IReporteConsulta> getReporteConsultaAll();
}




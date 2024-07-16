package com.literaturachallenge.literatura.interfacs;

import com.literaturachallenge.literatura.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AutorRepository extends JpaRepository<Autor,Long> {
    //@Query("SELECT a FROM Autor a WHERE a.nombre=:nombre")
    //Autor traerAutorPorNombre(String nombre);
    @Query("SELECT a FROM Autor a WHERE a.nombre=:nombre")
    Autor traerAutorPorNombre(String nombre);
    @Query("SELECT a FROM Autor a WHERE a.fechaNacimiento<:age AND a.fechaMuerte>:age")
    List<Autor> traerAutoresPorAge(int age);






}

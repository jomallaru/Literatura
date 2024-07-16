package com.literaturachallenge.literatura.interfacs;



import com.literaturachallenge.literatura.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {
    @Query("SELECT l FROM Libro l WHERE l.title=:nombre")
    Libro traerLibroPorNombre(String nombre);
    @Query("SELECT l.title FROM Libro l")
    List<String> traerNombreLibros();

    @Query(value = "SELECT * FROM libros WHERE :lenguaje = ANY(lenguajes)", nativeQuery = true)
    List<Libro> traerLibrosPorLenguaje(@Param("lenguaje") String lenguaje);
}

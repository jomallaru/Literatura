package com.literaturachallenge.literatura.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    private String nombreAuthor;
    private String[] generos;
    private List<String> lenguajes;
    private Integer descargas;

    @ManyToOne
    private Autor autor;
    public Libro() {
    }
    public Libro(LibroData l){
        this.title=l.title();
        this.generos=l.generos();
        this.lenguajes=l.lenguajes();
        this.descargas=l.descargas();
        this.nombreAuthor=l.autores()[0].nombre();
    }

    public String getNombreAuthor() {
        return nombreAuthor;
    }

    public void setNombreAuthor(String nombreAuthor) {
        this.nombreAuthor = nombreAuthor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String[] getGeneros() {
        return generos;
    }

    public void setGeneros(String[] generos) {
        this.generos = generos;
    }

    public List<String> getLenguajes() {
        return lenguajes;
    }

    public void setLenguajes(List<String> lenguajes) {
        this.lenguajes = lenguajes;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }


}

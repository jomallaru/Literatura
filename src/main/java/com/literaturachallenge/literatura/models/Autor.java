package com.literaturachallenge.literatura.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private Integer fechaNacimiento;
    private Integer fechaMuerte;
    @OneToMany(mappedBy = "autor",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Libro> listaLibros;

    public void setListaLibros(Libro libro,Autor autor) {

        List<Libro> listaLibros =traerLibro(autor);
        libro.setAutor(this);
        listaLibros.add(libro);
        this.listaLibros = listaLibros;
    }
    public List<Libro> traerLibro(Autor autor){
        return autor.listaLibros==null?new ArrayList<>():autor.listaLibros;
    }





    public Autor() {
    }
    public Autor(AutorData dataAutor){
        this.nombre=dataAutor.nombre();
        this.fechaNacimiento=dataAutor.fechaNacimiento();
        this.fechaMuerte= dataAutor.fechaMuerte();
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaMuerte() {
        return fechaMuerte;
    }

    public void setFechaMuerte(Integer fechaMuerte) {
        this.fechaMuerte = fechaMuerte;
    }

    public List<Libro> getListaLibros() {
        return listaLibros;
    }


}

package com.literaturachallenge.literatura.principal;


import com.literaturachallenge.literatura.interfacs.AutorRepository;

import com.literaturachallenge.literatura.interfacs.LibroRepository;
import com.literaturachallenge.literatura.models.*;

import com.literaturachallenge.literatura.service.Conversiones;
import com.literaturachallenge.literatura.service.LlamadasApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.*;
@Service
public class Principal {
    LlamadasApi llamadasApi =new LlamadasApi();
    AutorRepository repostoryAutor;
    LibroRepository repositoryLibro;
    Conversiones conversiones = new Conversiones();
    String URL_BASE = "https://gutendex.com/books/?search=";
    String MENU_PRINCIPAL= """
            Bienvenido ami aplicacion:
            \n1-Buscar/Registrar Libro
            \n2-Listar libros Registrados
            \n3-Listar autores registrados
            \n4-Listar autores vivos en un determinado año
            \n5-Listar libros por Idioma 
            \n0-Salir
            """;
    String SUB_MENU= """
            1-español
            2-ingles
            3-frances
            4-portugues
            """;



    Scanner sc=new Scanner(System.in);




    @Autowired
    public Principal(AutorRepository autorRepository, LibroRepository libroRepository){
        this.repostoryAutor=autorRepository;
        this.repositoryLibro=libroRepository;
    }
    public void inicio(){
        System.out.println("iniciando la app");
        var option = -1;
        while (option!=0){
            System.out.println(MENU_PRINCIPAL);
                option=validarEntero();
                switch (option){
                    case 1:
                        buscarLibro();
                        break;
                    case 2:
                        traerLibrosRegistrados();
                        break;
                    case 3:
                        traerAutoresRegistrados();
                        break;
                    case 4:
                        traerAutoresPorAgeIngresado();
                        break;
                    case 5:
                        traerLibrosIdiomas();
                        break;
                    case 0:
                        break;
                    case -1:
                        break;
                    default:
                        System.out.println("Debe ingresar una opcion valida");
                        break;
                }


        }


    }



    private void buscarLibro() {
        System.out.println("Ingrese el nombre del libro:");
        String titulo = sc.nextLine();

        //System.out.println("Soy libroNombre="+repositoryLibro.traerLibroPorNombre(titulo));



        if(!buscarLibroEnDB(titulo)) {
            DataApi dato = obtenerDatos(titulo);
            Optional<LibroData> libroBuscado = dato.listaLibros().stream()
                    .findFirst();
            if (libroBuscado.isPresent()) {
                LibroData libroEncontrado = libroBuscado.get();
                AutorData autorData = libroBuscado.get().autores()[0];
                if (!buscarAutorEnDB(autorData.nombre())) {
                    crearAutor(libroEncontrado);

                } else {
                    agregarLibroAutor(libroEncontrado);
                }
            } else {
                System.out.println("Sin resultados");
            }
        }else{
            System.out.println("Libro ya ingresado");
            Libro libro =repositoryLibro.traerLibroPorNombre(titulo);
            mostrarLibro(libro);
        }
    }


    private boolean buscarLibroEnDB(String titulo){
        return repositoryLibro.traerLibroPorNombre(titulo)!=null;
    }
    private boolean buscarAutorEnDB(String nombre){
        return repostoryAutor.traerAutorPorNombre(nombre) != null;
    }
    private DataApi obtenerDatos(String titulo){
        String codificado=codificarTitulo(titulo);
        var json = llamadasApi.obtenerDatos(URL_BASE + codificado);
        return conversiones.convertirAClase(json, DataApi.class);
    }





    private String codificarTitulo(String dato){
        @SuppressWarnings("deprecation")
        String codificado= URLEncoder.encode(dato);
        return codificado.replace("+","%20");
    }
    private void mostrarLibro(Libro libro){
        String lenguajes=formatoIdioma(libro);
        System.out.println(libro);
        String titulo = libro.getTitle()==null?"Sin titulo": libro.getTitle();
        String autor = libro.getAutor()==null?"Sin autor": libro.getAutor().getNombre();
        int descargas = libro.getDescargas()==null?0:libro.getDescargas();
        String INFO_LIBRO=String.format(
                "--------------------------Libro--------------------------"+
                        "\nTitulo: %s"+
                        "\nAutor: %s"+
                        "\nIdioma: %s"+
                        "\nNumero de Descargas: %d"+
                        "\n---------------------------------------------------------"

                ,titulo,autor, lenguajes,descargas);
        System.out.println(INFO_LIBRO);
    }
    private void mostrarAutor(Autor autor){
        List<String> nombreLibros = new ArrayList<>();
        autor.getListaLibros().forEach(e->nombreLibros.add(e.getTitle()));
        System.out.println("Soy los libros " + nombreLibros);
        String nombre = autor.getNombre()==null?"Sin autor":autor.getNombre();
        int fechaNacimiento = autor.getFechaNacimiento()==null?0: autor.getFechaNacimiento();
        int fechaMuerte = autor.getFechaMuerte()==null?0: autor.getFechaMuerte();

        String INFO_AUTOR=String.format(
                "--------------------------Autor--------------------------"+
                        "\nNombre: %s"+
                        "\nFecha Nacimiento: %d"+
                        "\nFecha Fallecimiento: %d"+
                        "\nLista de Libros: %s"+
                        "\n---------------------------------------------------------"

                ,nombre,fechaNacimiento,fechaMuerte,nombreLibros);
        System.out.println(INFO_AUTOR);
    }
    private String formatoIdioma(Libro libro){
        String lenguajes="";
        for (String lenguaje : libro.getLenguajes()) {
            switch (lenguaje){
                case "es":
                    lenguajes+="Español";
                    break;
                case "en":
                    lenguajes+="Ingles";
                    break;
                case "fr":
                    lenguajes+="Frances";
                    break;
                case "pt":
                    lenguajes+="Portugues";
                    break;
                default:
                    break;
            }

        }
        return lenguajes;
    }
    private boolean crearAutor(LibroData libroData){
        try{
            Autor autor = new Autor(libroData.autores()[0]);
            Libro libro = new Libro(libroData);
            autor.setListaLibros(libro, autor);
            repostoryAutor.save(autor);
            mostrarLibro(libro);
            System.out.println("Autor con Libro guardado exitosamente!!");
            return true;
        }catch (Exception e){
            System.out.println("Ocurrio un error inesperado ala hora de guardar el libro");
            return false;
        }
    }
    private void agregarLibroAutor(LibroData libroData){
        Autor autor = repostoryAutor.traerAutorPorNombre(libroData.autores()[0].nombre());
        Libro libro = new Libro(libroData);
        autor.setListaLibros(libro, autor);
        mostrarLibro(libro);
        repostoryAutor.save(autor);
    }
    private void traerLibrosRegistrados() {
        List<Libro> listaLibros = repositoryLibro.findAll();
        System.out.println("Soy libro lista " + listaLibros);
        System.out.println(listaLibros);
        if( listaLibros!=null && listaLibros.size()!=0){
            listaLibros.forEach(this::mostrarLibro);
        }else{
            System.out.println("No hay libros guardados en la base de datos");
        }
    }
    private void traerAutoresRegistrados() {
        List<Autor> listaAutores = repostoryAutor.findAll();
        if(listaAutores!=null && listaAutores.size()!=0 ){
            listaAutores.forEach(this::mostrarAutor);
        }else {
            System.out.println("No hay autores guardados en la base de datos");
        }
    }
    private void traerAutoresPorAgeIngresado() {
        System.out.println("Ingrese apartir de que año quiere realizar su busqueda");
        int age= sc.nextInt();
        sc.nextLine();
        List<Autor> listaAutores = repostoryAutor.traerAutoresPorAge(age);
        if(listaAutores!=null && listaAutores.size()==0){
            System.out.println("No hay autores registrados apartir de ese año");
        }else{
            listaAutores.forEach(this::mostrarAutor);
        }

    }
    @SuppressWarnings("null")
    private void traerLibrosIdiomas() {
        int option =-1;
        String lenguaje = "es";
        while(option>4 | option<=0 ) {
            System.out.println("Ingrese un lenguage");
            option = validarEntero();

            switch (option) {
                case 1:
                    lenguaje = "es";
                    break;
                case 2:
                    lenguaje = "en";
                    break;
                case 3:
                    lenguaje = "fr";
                    break;
                case 4:
                    lenguaje = "pt";
                    break;
                case -1:
                    break;
                default:
                    System.out.println("Debe ingresar una opcion valida");
                    break;
            }
        }
          List<Libro>  listaLibros = repositoryLibro.traerLibrosPorLenguaje(lenguaje);
        if(listaLibros==null && listaLibros.size()==0){
            System.out.println("No hay libros en ese idioma");
        }else{
            listaLibros.forEach(this::mostrarLibro);
        }

    }
    private int validarEntero(){
        int option=-1;
        while(true){

        try{
            option=sc.nextInt();
            sc.nextLine();
            break;
        }catch (InputMismatchException e){
            System.out.println("Debe ingresar un numero entero");
            sc.nextLine();
        }
        }
        return option;
    }





}

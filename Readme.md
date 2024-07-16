# Aplicación de Biblioteca de libros
# Índice

1. [Descripcion](#Descripción)
2. [Funcionalidades](#Funcionalidades)
3. [Instalación](#Instalación)

4. [Uso](#Uso)
5. [Tecnologías](#TecnologíasUtilizadas)
6. [Créditos](#Créditos)
7. [Ejemplos](#Ejemplos)
    1. [Menu](#Menu)
    2. [Registrar](#Registrar)
    2. [Lista](#Lista )


## Descripción
Esta aplicación te permite buscar libros por titulo y guardarlos en una base de datos personal.
Utiliza la datos de una api publica llamada [Gutendex](https://gutendex.com/) en principio realiza una busqueda en esta api, luego agrega el dato a su base de datos si encuentra un resultado

## Funcionalidades
- **Buscar/Registrar Libro**: Permite al usuario buscar un libro por título. Primero realiza la busqueda en la base de datos y luego si no existe coincidencia realiza una busqueda en la api y la sube a la base de datos.
- **Listar libros Registrados**: Muestra todos los libros registrados en la base de datos.
- **Listar autores registrados**: Muestra todos los autores registrados en la base de datos.
- **Listar autores vivos en un determinado año**: Permite al usuario ingresar un año y lista todos los autores que están vivos en ese año.
- **Listar libros por Idioma**: Permite al usuario seleccionar un idioma y lista todos los libros disponibles en ese idioma.

## Instalación
1. Clona este repositorio en tu máquina local.
2. Abre el proyecto en tu IDE favorito.
3. Configurar el entorno de desarrollo con las debidas dependencias 
4. Debe agregar variables de ambiente o cambiar los valores de aplication properties 
- DB_HOST
- DB_USERNAME
- DB_PASSWORD
- DB_NAME
- DB_PORT

5. Si Agrego las respectivas variables de entorno Recuerde REINICIAR!! la pc de lo contrario no reconocera las variables de entorno.
6. Ejecute la aplicacion.

## Uso
1. Cuando se inicia la aplicación, se muestra un menú principal con las opciones disponibles.
2. Selecciona la opción deseada ingresando el número correspondiente.
3. Sigue las instrucciones en pantalla para utilizar cada funcionalidad.

## Tecnologías Utilizadas
- Java 17 
- Spring Boot
- Hibernate
- MySQL (u otra base de datos relacional)//Verificar compatibilidad en los respectivos repositorio
- Maven 
- PostgreSQL

## Créditos
Este proyecto fue desarrollado por [DANI_DEV](https://github.com/daniDev6).


## Ejemplos
### Menu
![Menu Inicial](./img/menuInicio.png)
### Registrar
![Ejemplo Registrar](./img/ejemploRegistrar.png) 
### Lista 
![Ejemplo Lista Libros](./img/ejemploListaLibros.png)

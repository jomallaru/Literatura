package com.literaturachallenge.literatura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public record LibroData(
        @JsonAlias("id") Long id,
        @JsonAlias("title") String title,
        @JsonAlias("authors") AutorData[] autores,
        @JsonAlias("subjects") String[] generos,
        @JsonAlias("languages") List<String> lenguajes,
        @JsonAlias("download_count") Integer descargas

) {
        @Override
        public String toString() {
                return "\nLibroData{" +
                        "id=" + id +
                        ", title='" + title + '\'' +
                        ", autores=" + autores +
                        ", generos=" + Arrays.toString(generos) +
                        ", lenguajes=" + lenguajes +
                        ", descargas=" + descargas +
                        '}';
        }
}

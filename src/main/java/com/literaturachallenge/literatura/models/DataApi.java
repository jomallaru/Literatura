package com.literaturachallenge.literatura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataApi(
        @JsonAlias("count") Integer cantidad,
        @JsonAlias("next") String nextPag,
        @JsonAlias("previous") String backPag,
        @JsonAlias("results") List<LibroData> listaLibros
) {

}

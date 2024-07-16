package com.literaturachallenge.literatura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LlamadasApi {
    public  String obtenerDatos(String url){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        String json;
        try{

        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
        json=response.body();
            System.out.println("entrando al servicio");
        }catch (IOException | InterruptedException e){
            System.out.println("Error al hacer el request");
            json=null;
        }
        return json;
    }
}

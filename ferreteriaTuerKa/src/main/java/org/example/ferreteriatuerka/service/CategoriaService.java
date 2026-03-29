package org.example.ferreteriatuerka.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.ferreteriatuerka.model.Categoria;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class CategoriaService {

    private static final String URL = "http://localhost:5251/api/categorias";
    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    public List<Categoria> getCategorias() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        Type listType = new TypeToken<List<Categoria>>() {}.getType();
        return gson.fromJson(response.body(), listType);
    }
}

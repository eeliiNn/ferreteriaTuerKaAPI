package org.example.ferreteriatuerka.service;

import com.google.gson.Gson;
import org.example.ferreteriatuerka.model.Producto;
import com.google.gson.reflect.TypeToken;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.lang.reflect.Type;

public class ProductoService {
    private static final String url = "http://localhost:5251/api/productos";
    HttpClient client = HttpClient.newHttpClient();
    Gson gson = new Gson();

    public List<Producto> getProductos() throws Exception{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET().build();
        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
        Type listType = new TypeToken<List<Producto>>() {}.getType();
        return gson.fromJson(response.body(), listType);

    }
    public Producto getProducto(int id) throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/" + id))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        return gson.fromJson(response.body(), Producto.class);
    }
    public Producto crearProducto(Producto nuevo) throws Exception {

        String json = gson.toJson(nuevo);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        return gson.fromJson(response.body(), Producto.class);
    }

    public Producto actualizarProducto(int id, Producto cambios) throws Exception {

        String json = gson.toJson(cambios);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/" + id))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        return gson.fromJson(response.body(), Producto.class);
    }
    public boolean eliminarProducto(int id) throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/" + id))
                .DELETE()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode() == 200 || response.statusCode() == 204;
    }
}
package org.example.ferreteriatuerka.service;

import com.google.gson.Gson;
import org.example.ferreteriatuerka.model.Proveedor;
import com.google.gson.reflect.TypeToken;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.lang.reflect.Type;

public class ProveedorService {

    private static final String url = "http://localhost:5251/api/proveedores";
    HttpClient client = HttpClient.newHttpClient();
    Gson gson = new Gson();

    public List<Proveedor> getProveedores() throws Exception{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET().build();
        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
        Type listType = new TypeToken<List<Proveedor>>() {}.getType();
        return gson.fromJson(response.body(), listType);

    }
    public Proveedor buscarPorNombre(String nombre) throws Exception {

        List<Proveedor> lista = getProveedores();

        return lista.stream()
                .filter(p -> p.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    public Proveedor getProveedor(int id) throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/" + id))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        return gson.fromJson(response.body(), Proveedor.class);
    }
    public Proveedor crearProveedor(Proveedor nuevo) throws Exception {

        String json = gson.toJson(nuevo);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        return gson.fromJson(response.body(), Proveedor.class);
    }

    public Proveedor actualizarProveedor(int id, Proveedor cambios) throws Exception {

        String json = gson.toJson(cambios);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/" + id))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        return gson.fromJson(response.body(), Proveedor.class);
    }
    public boolean eliminarProveedor(int id) throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/" + id))
                .DELETE()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode() == 200 || response.statusCode() == 204;
    }
}

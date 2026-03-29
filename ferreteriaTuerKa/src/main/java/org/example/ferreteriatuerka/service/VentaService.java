package org.example.ferreteriatuerka.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.ferreteriatuerka.model.Venta;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class VentaService {

    private static final String BASE = "http://localhost:5251/api";
    private static final String URL_VENTAS = BASE + "/ventas";

    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    private void validarRespuesta(HttpResponse<String> response) {
        int code = response.statusCode();
        if (code < 200 || code >= 300) {
            throw new RuntimeException("HTTP " + code + " -> " + response.body());
        }
    }

    public List<Venta> getVentas() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_VENTAS))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        validarRespuesta(response);

        Type listType = new TypeToken<List<Venta>>() {}.getType();
        List<Venta> lista = gson.fromJson(response.body(), listType);
        return (lista == null) ? List.of() : lista;
    }

    public Venta getVenta(int id) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_VENTAS + "/" + id))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        validarRespuesta(response);

        return gson.fromJson(response.body(), Venta.class);
    }

    public Venta crearVenta(Venta nueva) throws Exception {
        String json = gson.toJson(nueva);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_VENTAS))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        validarRespuesta(response);

        return gson.fromJson(response.body(), Venta.class);
    }

    public Venta actualizarVenta(int id, Venta cambios) throws Exception {
        String json = gson.toJson(cambios);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_VENTAS + "/" + id))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        validarRespuesta(response);

        return gson.fromJson(response.body(), Venta.class);
    }

    public boolean eliminarVenta(int id) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_VENTAS + "/" + id))
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.statusCode() == 200 || response.statusCode() == 204;
    }
}
package org.example.ferreteriatuerka.service;
import org.example.ferreteriatuerka.model.LoginRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.ferreteriatuerka.model.Admin;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class AdminService {

    private static final String LOGIN_URL = "http://localhost:5251/api/auth/login";
    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    public List<Admin> getUsuarios() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(LOGIN_URL))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        Type listType = new TypeToken<List<Admin>>() {}.getType();
        return gson.fromJson(response.body(), listType);
    }

    public Admin login(String email, String password) throws Exception {

        LoginRequest loginData = new LoginRequest(email, password);

        String json = gson.toJson(loginData);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(LOGIN_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());


        if (response.statusCode() == 200) {
            return gson.fromJson(response.body(), Admin.class);
        }
        return null;
    }
}
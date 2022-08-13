package br.com.rldcarvalho.topfilmes;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ImdbApiClient {

    private String apiKey;

    public ImdbApiClient(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getBody() throws IOException, InterruptedException, URISyntaxException {
        String apiPath = "https://imdb-api.com/pt_BR/API/Top250Movies/";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(new URI(apiPath + this.apiKey))
                .GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();

        return json;
    }
}

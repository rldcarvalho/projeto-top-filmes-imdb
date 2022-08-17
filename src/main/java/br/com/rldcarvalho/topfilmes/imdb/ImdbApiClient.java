package br.com.rldcarvalho.topfilmes.imdb;

import br.com.rldcarvalho.topfilmes.ApiClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ImdbApiClient implements ApiClient {

    private String apiKey;

    public ImdbApiClient(String apiKey) {
        this.apiKey = apiKey;
    }
    @Override
    public String getBody() throws Exception {
        String apiPath = "https://imdb-api.com/pt_BR/API/Top250Movies/";
        HttpClient client = HttpClient.newHttpClient();
        String json = null;
        try {
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(new URI(apiPath + this.apiKey))
                    .GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            json = response.body();
        } catch (URISyntaxException e) {
            System.out.println("Insira uma API Key válida");
        }

        return json;
    }
}

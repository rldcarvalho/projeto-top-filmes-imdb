package br.com.rldcarvalho.topfilmes;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MarvelApiClient implements ApiClient{

    private String privateKey;
    private String publicKey;

    public MarvelApiClient(String privateKey, String publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    @Override
    public String getBody() throws Exception {
        String apiPath = "https://gateway.marvel.com:443/v1/public/series?ts=%s&hash=%s&apikey=%s";

        String ts = String.valueOf(System.currentTimeMillis());

        String hash = MD5.encryptToMD5(ts, this.privateKey, this.publicKey);

        String endpoint = String.format(apiPath, ts, hash, this.publicKey);
        System.out.println(endpoint);


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(new URI(endpoint))
                .GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();

        return json;
    }
}

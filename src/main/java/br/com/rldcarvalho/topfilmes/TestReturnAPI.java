package br.com.rldcarvalho.topfilmes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TestReturnAPI {
    static String apiKey = "enter with api key";
    static String apiPath = "https://imdb-api.com/pt_BR/API/Top250Movies/";
    static int sucessCode = 200;

    public static void main(String[] args) throws Exception {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(new URI(apiPath + apiKey))
                .GET().build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();



    }

    public static String getImdbTop250MovieString() throws Exception {
        //outro método de fazer o request da API


        try{
            URL url =  new URL(apiPath + apiKey);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if (connection.getResponseCode() != sucessCode)
                throw new RuntimeException("HTTP error code: " + connection.getResponseCode());

            BufferedReader apiAnswer = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            String jsonInString = Util.convertJsonInString(apiAnswer);
            return jsonInString;

        } catch (Exception e) {
            throw new Exception("ERRO: " + e);
        }
    }

}

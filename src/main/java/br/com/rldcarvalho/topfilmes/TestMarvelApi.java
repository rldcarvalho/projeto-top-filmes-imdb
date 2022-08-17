package br.com.rldcarvalho.topfilmes;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class TestMarvelApi {

    static String publicKey = "1b04efef4469c5b575752a19d241befb"; //"enter with public api key";
    static String privateKey = "618f5539c5fd52ae9fa5d6c34606bbf73771f03e";//"enter with private api key";

    public static void main(String[] args) throws Exception {

        String json = new MarvelApiClient(privateKey, publicKey).getBody();

        new MarvelSerieJsonParser(json).parse();

        List<Serie> series = new MarvelSerieJsonParser(json).parse();

        PrintWriter ps = new PrintWriter("indexMarvel.html", StandardCharsets.UTF_8);

        new HTMLGenerator(ps).generator(series);

        ps.close();

    }
}

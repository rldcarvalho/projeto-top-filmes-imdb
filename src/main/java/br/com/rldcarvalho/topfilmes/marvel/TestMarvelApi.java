package br.com.rldcarvalho.topfilmes.marvel;

import br.com.rldcarvalho.topfilmes.HTMLGenerator;
import br.com.rldcarvalho.topfilmes.Serie;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class TestMarvelApi {

    static String publicKey = ""; //"enter with public api key";
    static String privateKey = "";//"enter with private api key";

    public static void main(String[] args) throws Exception {

        String json = new MarvelApiClient(privateKey, publicKey).getBody();

        new MarvelSerieJsonParser(json).parse();

        List<Serie> series = new MarvelSerieJsonParser(json).parse();

        PrintWriter ps = new PrintWriter("indexMarvel.html", StandardCharsets.UTF_8);

        new HTMLGenerator(ps).generator(series);

        ps.close();

    }
}

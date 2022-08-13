package br.com.rldcarvalho.topfilmes;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class TestReturnAPI {
    static String apiKey = "k_4gidrkgy";

    public static void main(String[] args) throws Exception {

        String json = new ImdbApiClient(apiKey).getBody();

        List<Movie> movies = new ImdbMovieJsonParser(json).parse();

        PrintWriter ps = new PrintWriter("index.html", StandardCharsets.UTF_8);

        new HTMLGenerator(ps).generator(movies);

        ps.close();

    }

}

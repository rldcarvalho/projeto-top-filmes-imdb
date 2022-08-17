package br.com.rldcarvalho.topfilmes.imdb;

import br.com.rldcarvalho.topfilmes.HTMLGenerator;
import br.com.rldcarvalho.topfilmes.Movie;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestImdbAPI {
    static String apiKey = "";//"enter with api key";

    public static void main(String[] args) throws Exception {

        String json = new ImdbApiClient(apiKey).getBody();

        List<Movie> movies = new ImdbMovieJsonParser(json).parse();

        Collections.sort(movies, Comparator.reverseOrder());

        PrintWriter ps = new PrintWriter("index.html", StandardCharsets.UTF_8);

        new HTMLGenerator(ps).generator(movies);

        ps.close();

    }

}

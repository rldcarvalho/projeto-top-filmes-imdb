package br.com.rldcarvalho.topfilmes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
//        Requisitar e imprimir diretamente:
//        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
//                .thenApply(HttpResponse::body)
//                .thenAccept(System.out::println)
//                .join();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();

        String jsonSubstring = extractStringBetweenSquareBracket(json);

        String [] moviesArray = jsonSubstring.split("(?<=}),");

        System.out.println(moviesArray[0]);

        List<String> titles = parseTitle(moviesArray);

        List<String> urlImages = parseUrlImages(moviesArray);

        List<String> years = parseYears(moviesArray);

        List<String> imdbRatings = parseImdbRatings(moviesArray);

        titles.forEach(System.out::println);


    }


    public static List<String> parseTitle(String[] moviesArray){
        List<String> titles = new ArrayList<>();
        for (String movie: moviesArray) {
            int indexOfStartTitle = movie.indexOf("\"title\":") + 9;
            int indexOfEndTitle = movie.indexOf("\",\"fullTitle");
            titles.add(movie.substring(indexOfStartTitle, indexOfEndTitle));
        }
        return titles;
    }

    public static List<String> parseUrlImages(String[] moviesArray){
        List<String> urlImages = new ArrayList<>();
        for (String movie: moviesArray) {
            int indexOfStartUrl = movie.indexOf("\"image\":") + 9;
            int indexOfEndUrl = movie.indexOf("\",\"crew");
            urlImages.add(movie.substring(indexOfStartUrl, indexOfEndUrl));
        }
        return urlImages;
    }

    public static List<String> parseImdbRatings(String[] moviesArray){
        List<String> imdbRatings = new ArrayList<>();
        for (String movie: moviesArray) {
            int indexOfStartImdbRating = movie.indexOf("\"imDbRating\":") + 14;
            int indexOfEndImdbRating = movie.indexOf("\",\"imDbRatingCount");
            imdbRatings.add(movie.substring(indexOfStartImdbRating, indexOfEndImdbRating));
        }
        return imdbRatings;
    }

    public static List<String> parseYears(String[] moviesArray){
        List<String> years = new ArrayList<>();
        for (String movie: moviesArray) {
            int indexOfStartYear = movie.indexOf("\"year\":") + 8;
            int indexOfEndYear = movie.indexOf("\",\"image");
            years.add(movie.substring(indexOfStartYear, indexOfEndYear));
        }
        return years;
    }
    public static String extractStringBetweenSquareBracket(String json){
        int indexOfFirstSquareBracket= json.indexOf("[");
        int indexOfLastSquareBracket= json.indexOf("]");
        String jsonSubstring = json.substring(indexOfFirstSquareBracket + 1 , indexOfLastSquareBracket);
        return jsonSubstring;
    }
    public List<String> extract(String json, String regex){

        return null;
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

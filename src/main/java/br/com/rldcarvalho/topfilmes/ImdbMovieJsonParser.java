package br.com.rldcarvalho.topfilmes;

import java.util.ArrayList;
import java.util.List;

public class ImdbMovieJsonParser implements JsonParser{
    private String json;

    public ImdbMovieJsonParser(String json) {
        this.json = json;
    }

    @Override
    public List<Movie> parse(){
        String jsonSubstring = extractStringBetweenSquareBracket(this.json);

        String [] moviesArray = jsonSubstring.split("(?<=}),");

        List<String> titles = parseTitle(moviesArray);

        List<String> urlImages = parseUrlImages(moviesArray);

        List<String> years = parseYears(moviesArray);

        List<String> imdbRatings = parseImdbRatings(moviesArray);

        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < moviesArray.length; i++) {
            Movie movie = new Movie();
            movie.setTitle(titles.get(i));
            movie.setUrlImage(urlImages.get(i));
            movie.setYear(years.get(i));
            movie.setRating(imdbRatings.get(i));
            movies.add(movie);
        }

        return movies;
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
}

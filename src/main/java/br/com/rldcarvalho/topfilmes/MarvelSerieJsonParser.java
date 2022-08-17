package br.com.rldcarvalho.topfilmes;

import java.util.ArrayList;
import java.util.List;

public class MarvelSerieJsonParser implements JsonParser{
    private String json;
    public MarvelSerieJsonParser(String json) {
        this.json = json;
    }

    @Override
    public List<Serie> parse() {
        String jsonSubstring = extractStringBetweenSquareBracket(this.json);

        String [] seriesArray = jsonSubstring.split("\\},\\{\"id\"");

        System.out.println(seriesArray[0]);

        List<String> titles = parseTitle(seriesArray);

        List<String> urlImages = parseUrlImages(seriesArray);

        List<String> years = parseYears(seriesArray);

        List<String> ratings = parseRatings(seriesArray);

        List<Serie> series = new ArrayList<>();
        for (int i = 0; i < seriesArray.length; i++) {
            Serie serie = new Serie();
            serie.setTitle(titles.get(i));
            serie.setUrlImage(urlImages.get(i));
            serie.setYear(years.get(i));
            serie.setRating(ratings.get(i));
            series.add(serie);
        }
        return series;
    }

    private String extractStringBetweenSquareBracket(String json) {
        int indexOfFirstSquareBracket= json.indexOf("[");
        int indexOfLastSquareBracket= json.indexOf("}]}}");
        System.out.println(indexOfFirstSquareBracket);
        System.out.println(indexOfLastSquareBracket);
        String jsonSubstring = json.substring(indexOfFirstSquareBracket + 1 , indexOfLastSquareBracket + 1);
        return jsonSubstring;
    }

    public static List<String> parseTitle(String[] seriesArray){
        List<String> titles = new ArrayList<>();
        for (String serie: seriesArray) {
            int indexOfStartTitle = serie.indexOf("\"title\":") + 9;
            int indexOfEndTitle = serie.indexOf("\",\"desc");
            titles.add(serie.substring(indexOfStartTitle, indexOfEndTitle));
        }
        return titles;
    }

    public static List<String> parseUrlImages(String[] seriesArray){
        List<String> urlImages = new ArrayList<>();
        for (String serie: seriesArray) {
            int indexOfStartUrl = serie.indexOf("\"path\":") + 8;
            int indexOfEndUrl = serie.indexOf("\",\"ext");
            urlImages.add(serie.substring(indexOfStartUrl, indexOfEndUrl));
        }
        return urlImages;
    }

    public static List<String> parseRatings(String[] seriesArray){
        List<String> imdbRatings = new ArrayList<>();
        for (String serie: seriesArray) {
            int indexOfStartImdbRating = serie.indexOf("\"rating\":") + 10;
            int indexOfEndImdbRating = serie.indexOf("\",\"type");
            imdbRatings.add(serie.substring(indexOfStartImdbRating, indexOfEndImdbRating));
        }
        return imdbRatings;
    }

    public static List<String> parseYears(String[] seriesArray){
        List<String> years = new ArrayList<>();
        for (String serie: seriesArray) {
            int indexOfStartYear = serie.indexOf("\"startYear\":") + 12;
            int indexOfEndYear = serie.indexOf(",\"endYear");
            years.add(serie.substring(indexOfStartYear, indexOfEndYear));
        }
        return years;
    }
}

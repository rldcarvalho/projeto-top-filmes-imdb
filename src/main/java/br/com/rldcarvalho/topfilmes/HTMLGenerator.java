package br.com.rldcarvalho.topfilmes;

import java.io.PrintWriter;
import java.util.List;

public class HTMLGenerator {

    public void main(String[] args) {

    }

    public static void generator(List<Movie> moviesList, PrintWriter writer) {
        String head =
                """
                <head>
                    <meta charset=\"utf-8\">
                    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">
                    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\" 
                        + "integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">					
                </head>
                """;

        String divTemplate =
                """
                        <div class=\"card text-white bg-dark mb-3\" style=\"width: 18rem;\">
                            <h4 class=\"card-header\">%s</h4>
                            <div class=\"card-body\">
                                <img class=\"card-img\" src=\"%s\" alt=\"%s\">
                                <p class=\"card-text mt-2\">Nota: %s - Ano: %s</p>
                            </div>
                        </div>
                        """;

        writer.println(head);
        writer.println("<main class=\"card-board\" style= \"background-color: black; margin: auto; display: flex; flex-wrap: wrap; \">");
        for (Movie movie: moviesList) {
            writer.println(String.format(divTemplate, movie.getTitle(), movie.getImage(), movie.getTitle(), movie.getImDbRating(), movie.getYear()));
        }
        writer.println("</main>");

    }

}
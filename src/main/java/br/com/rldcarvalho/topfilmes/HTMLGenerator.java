package br.com.rldcarvalho.topfilmes;

import java.io.PrintWriter;
import java.util.List;

public class HTMLGenerator {

    private PrintWriter writer;

    public HTMLGenerator(PrintWriter writer) {
        this.writer = writer;
    }

    public void generator(List<? extends Content> contentList) {
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
                        <div class=\"card text-white bg-dark mb-3\" style=\"width: 12rem;\">
                            <h5 class=\"card-header\">%s</h4>
                            <div class=\"card-body\">
                                <img class=\"card-img\" src=\"%s\" alt=\"%s\">
                                <p class=\"card-text mt-2\">Nota: %s - Ano: %s</p>
                            </div>
                        </div>
                        """;

        this.writer.println(head);
        this.writer.println("<main class=\"card-board\" style= \"background-color: black; margin: auto; display: flex; flex-wrap: wrap; \">");
        for (Content content: contentList) {
            this.writer.println(String.format(divTemplate, content.getTitle(), content.getUrlImage(), content.getTitle(), content.getRating(), content.getYear()));
        }
        this.writer.println("</main>");

        System.out.println("Arquivo HTML gerado com sucesso!");
    }

}

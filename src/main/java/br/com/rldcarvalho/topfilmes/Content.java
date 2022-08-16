package br.com.rldcarvalho.topfilmes;

public interface Content {
    String getTitle();

    void setTitle(String title);

    String getUrlImage();

    void setUrlImage(String image);

    String getRating();

    void setRating(String imDbRating);

    String getYear();

    void setYear(String year);
}

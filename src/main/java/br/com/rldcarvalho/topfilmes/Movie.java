package br.com.rldcarvalho.topfilmes;

public class Movie {
    private String title;
    private String image;
    private double imDbRating;
    private int year;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getImDbRating() {
        return imDbRating;
    }

    public void setImDbRating(double imDbRating) {
        this.imDbRating = imDbRating;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

package br.com.rldcarvalho.topfilmes;

public class Movie implements Content{
    private String title;
    private String image;
    private String imDbRating;
    private String year;

    @Override
    public String getTitle() {
        return title;
    }
    @Override
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String getUrlImage() {
        return image;
    }
    @Override
    public void setUrlImage(String image) {
        this.image = image;
    }
    @Override
    public String getRating() {
        return imDbRating;
    }
    @Override
    public void setRating(String imDbRating) {
        this.imDbRating = imDbRating;
    }
    @Override
    public String getYear() {
        return year;
    }
    @Override
    public void setYear(String year) {
        this.year = year;
    }

}

package com.example.filmbulma;

public class Film {
    private String Title;
    private String Year;
    private String Rated;
    private String Released;
    private String Runtime;
    private String Genre;
    private String Director;
    private String Actors;
    private String Plot;
    private String Poster;

    public Film(String title, String year, String rated, String released, String runtime, String genre, String director, String actors, String plot, String poster) {
        Title = title;
        Year = year;
        Rated = rated;
        Released = released;
        Runtime = runtime;
        Genre = genre;
        Director = director;
        Actors = actors;
        Plot = plot;
        Poster = poster;
    }
    public String getTitle() {
        return Title;
    }
    public String getYear() {
        return Year;
    }
    public String getRated() {
        return Rated;
    }
    public String getReleased() {
        return Released;
    }
    public String getRuntime() {
        return Runtime;
    }
    public String getGenre() {
        return Genre;
    }
    public String getDirector() {
        return Director;
    }
    public String getActors() {
        return Actors;
    }
    public String getPlot() {
        return Plot;
    }
    public String getPoster() {
        return Poster;
    }

}

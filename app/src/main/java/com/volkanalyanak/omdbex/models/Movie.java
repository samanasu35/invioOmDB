package com.volkanalyanak.omdbex.models;

public class Movie {

    String Title,Year,Rated,Released,Runtime,Genre,Actors,Plot,Language,Poster,imdbID,imdbRating;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getRated() {
        return Rated;
    }

    public void setRated(String rated) {
        Rated = rated;
    }

    public String getReleased() {
        return Released;
    }

    public void setReleased(String released) {
        Released = released;
    }

    public String getRuntime() {
        return Runtime;
    }

    public void setRuntime(String runtime) {
        Runtime = runtime;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getActors() {
        return Actors;
    }

    public void setActors(String actors) {
        Actors = actors;
    }

    public String getPlot() {
        return Plot;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public Movie(String title, String year, String rated, String released, String runtime, String genre, String actors, String plot, String language, String poster, String imdbID, String imdbRating) {
        Title = title;
        Year = year;
        Rated = rated;
        Released = released;
        Runtime = runtime;
        Genre = genre;
        Actors = actors;
        Plot = plot;
        Language = language;
        Poster = poster;
        this.imdbID = imdbID;
        this.imdbRating = imdbRating;
    }

    public Movie() {
    }

    public Movie(Movie mMovie) {
        Title = mMovie.Title;
        Year = mMovie.Year;
        Rated = mMovie.Rated;
        Released = mMovie.Released;
        Runtime = mMovie.Runtime;
        Genre = mMovie.Genre;
        Actors = mMovie.Actors;
        Plot = mMovie.Plot;
        Language = mMovie.Language;
        Poster = mMovie.Poster;
        this.imdbID = mMovie.imdbID;
        this.imdbRating = mMovie.imdbRating;
    }

    @Override
    public String toString() {
        return "{" +
                "Title='" + Title.replace("'","`") + '\'' +
                ", Year='" + Year + '\'' +
                ", Rated='" + Rated + '\'' +
                ", Released='" + Released + '\'' +
                ", Runtime='" + Runtime + '\'' +
                ", Genre='" + Genre + '\'' +
                ", Actors='" + Actors.replace("'","`") + '\'' +
                ", Plot='" + Plot.replace("'","`") + '\'' +
                ", Language='" + Language + '\'' +
                ", Poster='" + Poster + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                '}';
    }
}

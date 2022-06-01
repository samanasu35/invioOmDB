package com.volkanalyanak.omdbex.models;

import java.util.List;

public class MovieList {

    List<Movie> Search;
    int totalResults;
    boolean Response;

    public List<Movie> getSearch() {
        return Search;
    }

    public void setSearch(List<Movie> search) {
        Search = search;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public boolean isResponse() {
        return Response;
    }

    public void setResponse(boolean response) {
        Response = response;
    }

    public MovieList(List<Movie> search, int totalResults, boolean response) {
        Search = search;
        this.totalResults = totalResults;
        Response = response;
    }

    public MovieList(MovieList mMovieList) {
        Search = mMovieList.Search;
        this.totalResults = mMovieList.totalResults;
        Response = mMovieList.Response;
    }

    public MovieList() {
    }
}

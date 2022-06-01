package com.volkanalyanak.omdbex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.volkanalyanak.omdbex.models.Movie;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Detail extends AppCompatActivity {

    @BindView(R.id.poster) ImageView img;
    @BindView(R.id.rating) TextView rating;
    @BindView(R.id.title) TextView title;
    @BindView(R.id.genre) TextView genre;
    @BindView(R.id.actors) TextView actors;
    @BindView(R.id.plot) TextView plot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detail);
        ButterKnife.bind(this);
        Bundle bnd = getIntent().getExtras();
        String jsonMovie = bnd.getString("movie");
        Log.d("jsonmovie",jsonMovie);
        Gson gson = new Gson();
        Movie movie = gson.fromJson(jsonMovie,Movie.class);

        Picasso.get().load(movie.getPoster())
                .placeholder(R.drawable.imagena)
                .error(R.drawable.imagena)
                .into(this.img);

        rating.setText(movie.getImdbRating());
        title.setText(movie.getTitle());
        actors.setText(movie.getActors());
        plot.setText(movie.getPlot());
        genre.setText(movie.getGenre());
    }
}
package com.volkanalyanak.omdbex.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.volkanalyanak.omdbex.Detail;
import com.volkanalyanak.omdbex.R;
import com.volkanalyanak.omdbex.models.Movie;
import com.volkanalyanak.omdbex.retrofit.RetrofitBase;
import com.volkanalyanak.omdbex.retrofit.RetrofitInterfaces;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {
    List<Movie> movies;
    LayoutInflater inflater;
    RetrofitInterfaces rif = null;
    View view;
    Context ctx;
    Activity act;

    public MovieListAdapter(Activity activity, Context context, List<Movie> movies) {
        ctx=context;
        act = activity;
        inflater = LayoutInflater.from(context);
        this.movies = movies;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.rw_movies, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        rif = RetrofitBase.getClient().create(RetrofitInterfaces.class);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie selectedMovie = movies.get(position);
        holder.setData(selectedMovie, position);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle,txtGenre,txtPlot;
        ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.title);
            txtGenre = (TextView) itemView.findViewById(R.id.genre);
            txtPlot = (TextView) itemView.findViewById(R.id.plot);
            img = (ImageView) itemView.findViewById(R.id.img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ctx, Detail.class);
                    intent.putExtra("movie",movies.get(getAdapterPosition()).toString());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ctx.startActivity(intent);
                }
            });
        }

        public void setData(Movie selectedMovie, int position) {

            Picasso.get().load(selectedMovie.getPoster())
                    .placeholder(R.drawable.imagena)
                    .error(R.drawable.imagena)
                    .into(this.img);
            txtTitle.setText(selectedMovie.getTitle().equals("N/A") ? "" : selectedMovie.getTitle());

            Call<Movie> getMovie = rif.getMovie(selectedMovie.getImdbID());
            getMovie.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    txtGenre.setText(response.body().getGenre().equals("N/A") ? "" : response.body().getGenre());
                    txtPlot.setText(response.body().getPlot().equals("N/A") ? "" : response.body().getPlot());
                    movies.get(position).setGenre(response.body().getGenre());
                    movies.get(position).setActors(response.body().getActors());
                    movies.get(position).setPlot(response.body().getPlot());
                    movies.get(position).setTitle(response.body().getTitle());
                    movies.get(position).setImdbRating(response.body().getImdbRating());
                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {

                }
            });
        }
    }
}

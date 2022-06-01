package com.volkanalyanak.omdbex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.volkanalyanak.omdbex.adapters.LoadDialog;
import com.volkanalyanak.omdbex.adapters.MovieListAdapter;
import com.volkanalyanak.omdbex.models.Movie;
import com.volkanalyanak.omdbex.models.MovieList;
import com.volkanalyanak.omdbex.retrofit.RetrofitBase;
import com.volkanalyanak.omdbex.retrofit.RetrofitInterfaces;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RetrofitInterfaces rif = null;
    MovieListAdapter mlAdapter;
    public static List<Movie> movies = new ArrayList<>();
    LoadDialog loadDialog;

    @BindView(R.id.rwMovies) RecyclerView rwMovies;
    @BindView(R.id.counter) TextView txt_counter;
    @BindView(R.id.etSearch) EditText etSearch;

    @OnClick(R.id.btnSearch) void btnSearchOnClick() {
        String search = etSearch.getText().toString();
        if (search.length()<3)
            txt_counter.setText("arama yapmak için en az 3 karakter giriniz");
        else
            getAllMovies(search,1);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        rif = RetrofitBase.getClient().create(RetrofitInterfaces.class);
        loadDialog = new LoadDialog(MainActivity.this);
    }

    void getAllMovies(String s,int page)
    {
        Call<MovieList> getMovieList = rif.getMovieList(s,page);
        getMovieList.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                int totalResult = response.body().getTotalResults();
                Log.d("callSuccess", "result count:"+totalResult);
                txt_counter.setText(totalResult > 0 ? ("toplam " + totalResult + " kayıt bulundu") : "Üzgünüz, kayıt bulunamadı");
                if (page == 1)
                {
                    rwMovies.setVisibility(View.INVISIBLE);
                    loadDialog.showDialog();
                }
                if (totalResult != 0) {
                    int lastPage = totalResult / 10;
                    if (totalResult % 10 > 0) lastPage++;
                    Log.d("callSuccess", "page:" + page + ",lastpage:" + lastPage);
                    if (page == 1) movies = response.body().getSearch();
                    else movies.addAll(response.body().getSearch());
                    loadDialog.setPbValue("" + page + " / " + lastPage);
                    if (lastPage == page) {
                        mlAdapter = new MovieListAdapter(MainActivity.this,MainActivity.this, movies);
                        rwMovies.setAdapter(mlAdapter);

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        rwMovies.setLayoutManager(linearLayoutManager);
                        rwMovies.setVisibility(View.VISIBLE);
                        loadDialog.dismissDialog();
                    } else {
                        getAllMovies(s, page + 1);
                    }
                }
                else
                {
                    loadDialog.dismissDialog();
                }
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Log.d("callError", "Bir hata oluştu");
            }
        });
    }


    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Çıkmak için ikinci kez basın", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
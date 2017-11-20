package burhan.com.popularmovielists.activity;

import android.content.Context;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import burhan.com.popularmovielists.R;
import burhan.com.popularmovielists.adapter.MoviesAdapter;
import burhan.com.popularmovielists.app.AppConstants;
import burhan.com.popularmovielists.model.Movie;
import burhan.com.popularmovielists.model.ServerResponse;
import burhan.com.popularmovielists.service.APIService;
import burhan.com.popularmovielists.service.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    String page = "1";
    ServerResponse serverResponse;
    List<Movie> movies;
    MoviesAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setGridView();

        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        final NetworkInfo activeNetwork = cm.getActiveNetworkInfo();


        if(activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting()) {

            getMovies();
        }
        else
            Toast.makeText(MainActivity.this, "Unable to connect to the internet", Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void setGridView() {

        movies = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.moviesRecyclerView);

        final RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    private void getMovies() {

        APIService apiService = RetrofitClient.getRetrofitClient(this);


        Call<ServerResponse> call = apiService.getPopularMovies(AppConstants.BASE_URL + AppConstants.END_URL + getPage());

        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                Log.d("Response", response.toString());
                serverResponse = response.body();

                if(!serverResponse.getResults().isEmpty()) {

                    if (movies.isEmpty()) {
                        movies = serverResponse.getResults();

                        initialiseAdapter();
                        recyclerView.setAdapter(adapter);

                    } else {
                        movies.addAll(serverResponse.getResults());
                        adapter.notifyDataSetChanged();
                    }
                }


            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Error in Connection.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initialiseAdapter() {

        adapter = new MoviesAdapter(getApplicationContext(), movies) {
            @Override
            public void loadMoreMovies() {


                page = String.valueOf(Integer.parseInt(page) + 1);

                if (Integer.parseInt(serverResponse.getTotalPages()) >= Integer.parseInt(page)) {
                    Log.d("Pagination", page);
                    getMovies();
                }

            }

            @Override
            public void onClickItem(Movie movie)
            {
                Log.d("Clicked Movie", movie.getTitle());

                Intent intent = new Intent(MainActivity.this, MovieDetails.class);
                intent.putExtra("movie", movie);

                startActivity(intent);


            }
        };
    }

    private String getPage() {

        return page;

    }


}

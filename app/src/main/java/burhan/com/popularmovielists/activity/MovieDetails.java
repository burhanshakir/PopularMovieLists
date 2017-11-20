package burhan.com.popularmovielists.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import burhan.com.popularmovielists.R;
import burhan.com.popularmovielists.model.Movie;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MovieDetails extends AppCompatActivity {

    Movie movie;

    ImageView poster, thumbnail;
    TextView name, popularity,date,overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        init();

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void init() {

        movie = (Movie) getIntent().getSerializableExtra("movie");
        poster = (ImageView) findViewById(R.id.ivImage);
        thumbnail = (ImageView) findViewById(R.id.ivThumbnail);
        name = (TextView) findViewById(R.id.tvName);
        popularity = (TextView) findViewById(R.id.tvPopularity);
        date = (TextView) findViewById(R.id.tvDate);
        overview = (TextView) findViewById(R.id.tvOverview);

        Glide.with(this).load(movie.getImagePath()).centerCrop().into(thumbnail);
        Glide.with(this).load(movie.getPosterPath()).centerCrop().into(poster);

        name.setText(movie.getTitle());
        popularity.setText(movie.getPopularity());
        date.setText(movie.getReleaseYear());
        overview.setText(movie.getOverview());
    }
}

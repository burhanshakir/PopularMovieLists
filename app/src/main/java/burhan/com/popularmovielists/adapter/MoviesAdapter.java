package burhan.com.popularmovielists.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import burhan.com.popularmovielists.R;
import burhan.com.popularmovielists.model.Movie;

/**
 * Created by burhanuddinshakir on 19/11/17.
 */

public abstract class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private Context mContext;
    private List<Movie> movies;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, popularity, date;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.tvTitle);
            popularity = view.findViewById(R.id.tvPopularity);
            date =  view.findViewById(R.id.tvReleaseDate);
            thumbnail = view.findViewById(R.id.ivImage);
        }
    }

    public MoviesAdapter(Context context, List<Movie> movies)
    {
        this.mContext = context;
        this.movies = movies;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Movie movie = movies.get(position);
        holder.title.setText(movie.getTitle());

        holder.popularity.setText(movie.getPopularity());
        holder.date.setText(movie.getReleaseYear());

        // loading album cover using Glide library
        Glide.with(mContext).load(movie.getImagePath()).centerCrop().into(holder.thumbnail);

        if(position == getItemCount() -1)
            loadMoreMovies();


    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public abstract void loadMoreMovies();



}

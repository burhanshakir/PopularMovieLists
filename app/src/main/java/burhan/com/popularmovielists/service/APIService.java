package burhan.com.popularmovielists.service;


import burhan.com.popularmovielists.model.ServerResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by burhanuddinshakir on 18/11/17.
 */

public interface APIService {

    @GET
    Call<ServerResponse> getPopularMovies(@Url String url);
}

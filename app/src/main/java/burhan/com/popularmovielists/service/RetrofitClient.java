package burhan.com.popularmovielists.service;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import burhan.com.popularmovielists.app.AppConstants;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by burhanuddinshakir on 18/11/17.
 */

public class RetrofitClient
{
    public static APIService getRetrofitClient(Context mConætext)
    {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(APIService.class);
    }
}

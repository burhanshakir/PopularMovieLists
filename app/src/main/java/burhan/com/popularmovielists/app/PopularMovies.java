package burhan.com.popularmovielists.app;

import android.app.Application;

import burhan.com.popularmovielists.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by burhanuddinshakir on 18/11/17.
 */

public class PopularMovies extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("Avenir-Book.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}

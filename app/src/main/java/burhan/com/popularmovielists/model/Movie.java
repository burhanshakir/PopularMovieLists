package burhan.com.popularmovielists.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import burhan.com.popularmovielists.app.AppConstants;

/**
 * Created by burhanuddinshakir on 18/11/17.
 */

public class Movie implements Serializable
{
    @SerializedName("id")
    String id;

    @SerializedName("title")
    String title;

    @SerializedName("poster_path")
    String imagePath;

    @SerializedName("popularity")
    String popularity;

    @SerializedName("overview")
    String overview;

    @SerializedName("release_date")
    String releaseYear;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagePath() {
        return AppConstants.THUMBNAIL_URL + imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseYear() {
        return releaseYear.substring(0,4);
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }
}

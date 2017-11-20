package burhan.com.popularmovielists.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by burhanuddinshakir on 18/11/17.
 */

public class ServerResponse implements Serializable {

    @SerializedName("page")
    String page;

    @SerializedName("total_results")
    String totalResults;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    @SerializedName("total_pages")

    String totalPages;

    @SerializedName("results")
    List<Movie> results;
}

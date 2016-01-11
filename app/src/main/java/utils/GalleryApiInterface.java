package utils;

import java.util.List;

import models.GalleryImage;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by pannam on 1/4/2016.
 */
public interface GalleryApiInterface {
    @GET("/gallery.json")

    void getStreams(Callback<List<GalleryImage>>callback);
}

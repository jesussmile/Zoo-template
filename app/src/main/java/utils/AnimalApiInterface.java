package utils;

import java.util.List;

import models.Animal;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by pannam on 1/2/2016.
 */
public interface AnimalApiInterface {
    @GET("/Exhibits.json")
    void getStreams(Callback<List<Animal>>callback);

}

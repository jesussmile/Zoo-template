package utils;

import java.util.List;

import models.Pin;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by pannam on 1/6/2016.
 */
public interface PinsApiInterface  {
    @GET("/pins.json")
    void getStreams(Callback<List<Pin>> callback);
}

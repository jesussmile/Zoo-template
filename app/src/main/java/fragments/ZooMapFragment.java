package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.pannam.zoo.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import models.Pin;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import utils.PinsApiInterface;

/**
 * Created by pannam on 12/31/2015.
 */
public class ZooMapFragment extends SupportMapFragment {
    public static ZooMapFragment getInstance() {

        ZooMapFragment fragment = new ZooMapFragment();
        return fragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CameraPosition position = CameraPosition.builder()
                .target(new LatLng(39.7500, -104.9500))
                .zoom(16f)
                .tilt(0.0f)
                .build();


        getMap().animateCamera(CameraUpdateFactory.newCameraPosition(position), null);
        getMap().setMapType(GoogleMap.MAP_TYPE_HYBRID);
        getMap().setTrafficEnabled(true);
        getMap().getUiSettings().setZoomControlsEnabled(true);

        MarkerOptions options = new MarkerOptions().position(new LatLng(39.7500, -104.9500));
        options.title("Zoo");
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        getMap().addMarker(options);


        getMap().setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();

                return true;
            }
        });

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(getString(R.string.pins_feed))
                .build();

        PinsApiInterface pinsApiInterface = adapter.create(PinsApiInterface.class);

        pinsApiInterface.getStreams(new Callback<List<Pin>>() {
            @Override
            public void success(List<Pin> pins, Response response) {
                if (!isAdded() || pins == null || pins.isEmpty())
                    return;

                for (Pin pin : pins) {
                    MarkerOptions options = new MarkerOptions().position(new LatLng(pin.getLatitude(), pin.getLongitude()));
                    options.title(pin.getName());
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                    getMap().addMarker(options);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Zoo", "Retrofit error" + error.getMessage());
            }
        });
    }


}

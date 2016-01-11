package fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.pannam.zoo.R;

import java.util.List;

import activities.GalleryDetailActivity;
import adapters.GalleryImageAdapter;
import models.GalleryImage;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import utils.GalleryApiInterface;

/**
 * Created by pannam on 12/31/2015.
 */
public class GalleryFragment extends Fragment implements AdapterView.OnItemClickListener {
    private GridView mGridView;
    private GalleryImageAdapter mAdapter;

    public static GalleryFragment getInstance() {
        GalleryFragment fragment = new GalleryFragment();
        return fragment;

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gallery_grid, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mGridView = (GridView) view.findViewById(R.id.grid);
        mGridView.setOnItemClickListener(this);
        mGridView.setDrawSelectorOnTop(true);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new GalleryImageAdapter(getActivity(), 0);

        mGridView.setAdapter(mAdapter);
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(getString(R.string.gallery_feed))
                .build();

        GalleryApiInterface galleryApiInterface = restAdapter.create(GalleryApiInterface.class);

        galleryApiInterface.getStreams(new Callback<List<GalleryImage>>() {
            @Override
            public void success(List<GalleryImage> galleryImages, Response response) {

                if (galleryImages == null || galleryImages.isEmpty())
                    return;

                for (GalleryImage image : galleryImages) {
                    Log.e("Zoo", image.getThumbnail());
                    mAdapter.add(image);
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GalleryImage image = (GalleryImage) parent.getItemAtPosition(position);
        Intent intent = new Intent(getActivity(), GalleryDetailActivity.class);
        intent.putExtra(GalleryDetailActivity.EXTRA_IMAGE,image.getImage());
        intent.putExtra(GalleryDetailActivity.EXTRA_CAPTION,image.getCaption());
        startActivity(intent);
    }
}

package fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.pannam.zoo.R;

import java.util.List;

import activities.ExhibitDetailActivity;
import adapters.ExhibitsAdapter;
import models.Animal;
import retrofit.Callback;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import utils.AnimalApiInterface;

/**
 * Created by pannam on 12/31/2015.
 */
public class ExhibitsListFragment extends ListFragment {
    private ExhibitsAdapter mAdapter;


    public static ExhibitsListFragment getInstance() {
        ExhibitsListFragment fragment = new ExhibitsListFragment();
        return fragment;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListShown(false);
        getListView().setPadding(16,16,16,16);
//        getListView().setDivider(new ColorDrawable(Color.BLUE));
        getListView().setDividerHeight(16);
        getListView().setScrollBarStyle(getView().SCROLLBARS_OUTSIDE_OVERLAY);
        getListView().setClipToPadding(true);
        mAdapter = new ExhibitsAdapter(getActivity(), 0);
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(getString(R.string.exhibits_feed))
                .build();
        AnimalApiInterface animalApiInterface = restAdapter.create(AnimalApiInterface.class);
        animalApiInterface.getStreams(new Callback<List<Animal>>() {
            @Override
            public void success(List<Animal> animals, Response response) {

                if (animals == null || animals.isEmpty())
                    return;

                for (Animal animal : animals) {
                    mAdapter.add(animal);
                }
                mAdapter.notifyDataSetChanged();
                setListAdapter(mAdapter);
                setListShown(true);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Zoo", "Retrofit error" + error.getMessage());
            }
        });
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(getActivity(),ExhibitDetailActivity.class);
        intent.putExtra(ExhibitDetailActivity.EXTRA_ANIMAL, mAdapter.getItem(position));
        startActivity(intent);
    }
}





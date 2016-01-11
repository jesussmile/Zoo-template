package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.pannam.zoo.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import models.Animal;


/**
 * Created by pannam on 1/2/2016.
 */
public class ExhibitsAdapter extends ArrayAdapter<Animal> {
    public ExhibitsAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_exhibits_list_item, parent, false);
            holder.progress = (ProgressBar) convertView.findViewById(R.id.progress);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.species = (TextView) convertView.findViewById(R.id.species);
            holder.thumbnail = (ImageView) convertView.findViewById(R.id.thumbnail);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();

        }
        holder.thumbnail.setVisibility(View.GONE);
        holder.progress.setVisibility(View.VISIBLE);

        holder.name.setText(getItem(position).getNames());
        holder.species.setText(getItem(position).getSpecies());

        final ViewHolder tmp = holder;
        Picasso.with(getContext()).load(getItem(position).getThumbnail()).into(holder.thumbnail
                , new Callback() {
                    @Override
                    public void onSuccess() {
                        tmp.progress.setVisibility(View.GONE);
                        tmp.thumbnail.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError() {
                    tmp.progress.setVisibility(View.GONE);
                    }
                });
        return convertView;

    }


    class ViewHolder {
        ImageView thumbnail;
        ProgressBar progress;
        TextView name;
        TextView species;
    }
}

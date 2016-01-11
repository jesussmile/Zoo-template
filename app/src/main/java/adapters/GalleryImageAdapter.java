package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.pannam.zoo.R;
import com.squareup.picasso.Picasso;

import models.GalleryImage;

/**
 * Created by pannam on 1/4/2016.
 */
public class GalleryImageAdapter extends ArrayAdapter<GalleryImage> {
    public GalleryImageAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder holder;

        if (convertView == null) {
            holder = new Viewholder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_gallery_thumbnail, parent, false);
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(holder);

        } else {
            holder = (Viewholder) convertView.getTag();

        }
        Picasso.with(getContext()).load(getItem(position).getThumbnail()).into(holder.image);


        return convertView;
    }

    private class Viewholder {
        ImageView image;
    }
}
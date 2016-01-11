package activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pannam.zoo.R;
import com.squareup.picasso.Picasso;

import models.Animal;

/**
 * Created by pannam on 1/3/2016.
 */
public class ExhibitDetailActivity extends AppCompatActivity {

   public static final String EXTRA_ANIMAL = "extra_animal";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibit_detail);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        Animal animal = getIntent().getExtras().getParcelable(EXTRA_ANIMAL);

        TextView species = (TextView)findViewById(R.id.species);
        TextView description = (TextView)findViewById(R.id.description);
        ImageView image = (ImageView)findViewById(R.id.image);


        species.setText(animal.getSpecies());
        description.setText(animal.getDescription());

        Picasso.with(this).load(animal.getImage()).into(image);



    }
}

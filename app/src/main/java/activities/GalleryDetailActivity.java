package activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pannam.zoo.R;
import com.squareup.picasso.Picasso;

/**
 * Created by pannam on 1/6/2016.
 */
public class GalleryDetailActivity extends AppCompatActivity {
    public static final String EXTRA_IMAGE = "extra_image";
    public static final String EXTRA_CAPTION = "extra_caption";

    private TextView mCaptionTextView;
    private ImageView mImageView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_detail);
        mCaptionTextView = (TextView)findViewById(R.id.caption);
        mImageView =(ImageView)findViewById(R.id.image);

        if(getIntent() !=null && getIntent().getExtras()!=null){
            if (getIntent().getExtras().containsKey(EXTRA_IMAGE)){
                Picasso.with(this).load(getIntent().getExtras().getString(EXTRA_IMAGE)).into(mImageView);
            }

            if(getIntent().getExtras().containsKey(EXTRA_CAPTION)){
                mCaptionTextView.setText(getIntent().getExtras().getString(EXTRA_CAPTION));
            }
        }

    }
}

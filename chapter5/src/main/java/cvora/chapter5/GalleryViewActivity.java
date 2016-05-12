package cvora.chapter5;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class GalleryViewActivity extends AppCompatActivity {

    Integer[] imageIDS = {
            R.mipmap.pic1,
            R.mipmap.pic2,
            R.mipmap.pic3,
            R.mipmap.pic4,
            R.mipmap.pic5,
            R.mipmap.pic6,
            R.mipmap.pic7,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_view);

        Gallery gallery = (Gallery)findViewById(R.id.gallery1);
        gallery.setAdapter(new ImageAdapter(this));
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(),"pic"+(position + 1) + "selected",Toast.LENGTH_SHORT).show();

                ImageView imageView = (ImageView)findViewById(R.id.image1);
                imageView.setImageResource(imageIDS[position]);
            }
        });
    }

    public class ImageAdapter extends BaseAdapter{

        Context context;
        int itemBackground;

        public ImageAdapter(Context c){
            context = c;
            TypedArray a = obtainStyledAttributes(R.styleable.Gallery1);
            itemBackground = a.getResourceId(R.styleable.Gallery1_android_galleryItemBackground,0);
            a.recycle();
        }

        @Override
        public int getCount() {
            return imageIDS.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if(convertView == null){
                imageView = new ImageView(context);
                imageView.setImageResource(imageIDS[position]);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setLayoutParams(new Gallery.LayoutParams(150,120));
            }else{
                imageView = (ImageView)convertView;
            }
            imageView.setBackgroundResource(itemBackground);
            return imageView;
        }
    }
}

package politechnika.meetyourtrainer.Ads;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import politechnika.meetyourtrainer.R;

public class MyAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<CardModel> models;

    public MyAdapter(Context c, ArrayList<CardModel> models) {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //this line inflate row
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ads_row, null);
        //this will return our view to holder class
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.title.setText(models.get(position).getTitle());
        holder.description.setText(models.get(position).getDesctiption());
        //holder.image.setImageDrawable(models.get().getImgURL());
        setBitmapFromURL(models.get(position).getImgURL(), holder.image);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //implement onClick
                System.out.println("You clicked card number " + position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public void setBitmapFromURL(String url, ImageView img){
        String drawableRes= url;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            URL url1 = new URL(drawableRes);
            img.setImageBitmap(BitmapFactory.decodeStream((InputStream)url1.getContent()));
        } catch (IOException e) {
            //Log.e(TAG, e.getMessage());
        }
    }
}

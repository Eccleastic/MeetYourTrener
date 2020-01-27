package politechnika.meetyourtrainer.Ads;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
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

import politechnika.meetyourtrainer.AdInfoActivity;
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

        String description;
        if(models.get(position).getDescription().length() > 60){
            description = models.get(position).getDescription().substring(0,60);
            description += "...";
        } else {
            description = models.get(position).getDescription();
        }
        holder.description.setText(description);

        String address = "\uD83D\uDCCD" + models.get(position).getAddress();
        holder.address.setText(address);

        String price =  "\uD83D\uDCB0" + models.get(position).getPrice() + "zł";
        holder.price.setText(price);

        String date = "\uD83D\uDCC5" + models.get(position).getDate();
        holder.date.setText(date);

        setBitmapFromURL(models.get(position).getImgURL(), holder.image);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(models.get(position).getAd_id() != null) {
                    Intent intent = new Intent(c, AdInfoActivity.class);
                    intent.putExtra("description", models.get(position).getDescription());
                    intent.putExtra("title", models.get(position).getTitle());
                    intent.putExtra("rate", "4.70");
                    intent.putExtra("email", models.get(position).getEmail());
                    intent.putExtra("phone", models.get(position).getPhone().trim());
                    intent.putExtra("price", models.get(position).getPrice());
                    intent.putExtra("address", models.get(position).getAddress());
                    intent.putExtra("name", models.get(position).getName());
                    intent.putExtra("photoURL", models.get(position).getImgURL());
                    intent.putExtra("date", models.get(position).getDate());
                    c.startActivity(intent);
                }
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

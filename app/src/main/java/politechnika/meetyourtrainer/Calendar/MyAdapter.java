package politechnika.meetyourtrainer.Calendar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

import politechnika.meetyourtrainer.MeetingInfoActivity;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_row, null);
        //this will return our view to holder class
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.title.setText(models.get(position).getTitle());
        holder.description.setText(models.get(position).getDesctiption());
        holder.image.setImageResource(models.get(position).getImg());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //implement onClick
                System.out.println("You clicked card number " + position);
                Intent intent = new Intent(c, MeetingInfoActivity.class);
                intent.putExtra("description", models.get(position).desctiption);
                intent.putExtra("id", models.get(position).title);
                intent.putExtra("address", models.get(position).title);
                c.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}

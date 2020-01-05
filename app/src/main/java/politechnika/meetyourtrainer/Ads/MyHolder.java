package politechnika.meetyourtrainer.Ads;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import politechnika.meetyourtrainer.R;

public class MyHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
    ImageView image;
    TextView title, description;
    CardView cardView;

    public MyHolder(@NonNull View itemView) {
        super(itemView);

        image = itemView.findViewById(R.id.image);
        title = itemView.findViewById(R.id.title);
        description = itemView.findViewById(R.id.description);
        cardView = itemView.findViewById(R.id.cardView);
    }
}
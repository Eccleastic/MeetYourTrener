package politechnika.meetyourtrainer.Calendar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import politechnika.meetyourtrainer.R;

public class MyHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
    ImageView image;
    TextView title, description;

    public MyHolder(@NonNull View itemView) {
        super(itemView);

        image = itemView.findViewById(R.id.image);
        title = itemView.findViewById(R.id.title);
        description = itemView.findViewById(R.id.description);
    }
}

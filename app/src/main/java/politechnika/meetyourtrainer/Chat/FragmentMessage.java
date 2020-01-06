package politechnika.meetyourtrainer.Chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import politechnika.meetyourtrainer.R;

public class FragmentMessage extends Fragment {
    TextView messagesText;
    String text = "Fragment Chatu. Ten komponent jeszcze nie działa.";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        messagesText = view.findViewById(R.id.messagesText);
        messagesText.setText(text);
    }
}
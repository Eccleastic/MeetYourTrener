package politechnika.meetyourtrainer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import politechnika.meetyourtrainer.Profile.ProfileActivity;
import politechnika.meetyourtrainer.Profile.ProfileProvider;

public class SearchListView extends Fragment {
    //SearchListViewModel slvm;
    private TextView name;
    private TextView description;
    private TextView name2;
    private TextView description2;

    private TextView user1profile;
    private TextView user2profile;


    public static SearchListView newInstance() {
        return new SearchListView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //slvm = ViewModelProviders.of(getActivity()).get(SearchListViewModel.class);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_view, container, false);

        name = view.findViewById(R.id.user1name);
        description = view.findViewById(R.id.user1description);
        name2 = view.findViewById(R.id.user2name);
        description2 = view.findViewById(R.id.user2description);

        user1profile = view.findViewById(R.id.user1profile);
        user2profile = view.findViewById(R.id.user2profile);

        name.setText("Adam Malysz");
        description.setText("Specjalizuje sie w skakaniu. W góre, w bok, na nartach, bez nart, z jedną nartą, na wszystkim i ze wszystkim. 60$/h.");
        name2.setText("Kasia Drąg");
        description2.setText("Trenerka yogi z 15-letnim doświadczeniem. Zapraszam do siebie wszystkie panie od pon do pt w godzinach 8-14. 30$/h");

        user1profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        user2profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                ProfileProvider profile = new ProfileProvider("testtrener");
                profile.getDataFromApi(getActivity());
                intent.putExtra("description", profile.getDescription());
                intent.putExtra("title", profile.getName()+ " " + profile.getLastname());
                intent.putExtra("rate", profile.getRating());
                intent.putExtra("email", profile.getEmail());
                intent.putExtra("phone", profile.getPhoneNumber());
                startActivity(intent);
            }
        });

        return view;
    }
}

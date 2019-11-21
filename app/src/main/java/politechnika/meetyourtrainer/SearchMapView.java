package politechnika.meetyourtrainer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SearchMapView extends Fragment implements OnMapReadyCallback {

    GoogleMap mGoogleMap;
    MapView mMapView;
    View mView;

    private final LatLng budynek_CTI = new LatLng(51.746956, 19.455958);
    private final LatLng budynek_centrum_sportu = new LatLng(51.746256, 19.451444);
    private final LatLng budynek_sukcesja = new LatLng(51.749201, 19.448128);
    private final LatLng budynek_WEEIA = new LatLng(51.752612, 19.453118);

    public static SearchMapView newInstance() {
        return new SearchMapView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_map_view, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView = (MapView) mView.findViewById(R.id.map);
        if(mMapView != null){
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());

        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        googleMap.addMarker(new MarkerOptions().position(budynek_CTI).title("Centrum Technologii Informatycznych"));
        googleMap.addMarker(new MarkerOptions().position(budynek_centrum_sportu).title("Centrum Sportu"));
        googleMap.addMarker(new MarkerOptions().position(budynek_sukcesja).title("Galeria Sukcesja"));
        googleMap.addMarker(new MarkerOptions().position(budynek_WEEIA).title("Wydzial EEIA"));

        CameraPosition Liberty = CameraPosition.builder().target(budynek_CTI).zoom(17).bearing(0).tilt(45).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(Liberty));

    }

}

package codepath.com.todoapp.map;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import codepath.com.todoapp.R;
import codepath.com.todoapp.address.AddressDisplayFragment;

public class MapScreen extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap googleMap;
    private Marker userClickedMarker;
    private AddressDisplayFragment addressDisplayFragment;
    private final String logTag = MapScreen.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_screen);

        // initialize google map ready call back
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.googleMapFragment);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        } else {
            Log.e(logTag, "mapFragment is null");
        }

        // show AddressDisplayFragment
        addressDisplayFragment = new AddressDisplayFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutForAddress,
                addressDisplayFragment).commit();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(logTag, "onMapReady()");
        this.googleMap = googleMap;
        this.googleMap.setOnMapClickListener(this);
    }


    @Override
    public void onMapClick(LatLng latLng) {
        Log.d(logTag, "onMapClick() latLng:" + latLng);
        addOrUpdateMarkerAtClickedPosition(latLng);
        if (addressDisplayFragment != null && addressDisplayFragment.isAdded()) {
            addressDisplayFragment.mapClickedAt(latLng);
        } else {
            Log.e(logTag, "addressDisplayFragment is null or not added");
        }
    }

    private void addOrUpdateMarkerAtClickedPosition(LatLng clickedLatLng) {
        if (googleMap != null) {
            if (userClickedMarker != null) {
                userClickedMarker.setPosition(clickedLatLng);
            } else {
                userClickedMarker = googleMap.addMarker(new MarkerOptions().position(clickedLatLng));
            }
        } else {
            Log.e(logTag, "googleMap is null, can't add marker");
        }
    }
}
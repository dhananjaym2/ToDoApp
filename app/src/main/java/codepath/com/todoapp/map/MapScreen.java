package codepath.com.todoapp.map;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import codepath.com.todoapp.R;
import codepath.com.todoapp.address.AddressDisplayFragment;

public class MapScreen extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private FrameLayout frameLayoutForAddress;
    private GoogleMap googleMap;
    private AddressDisplayFragment addressDisplayFragment;
    private final String logTag = MapScreen.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_screen);

        // initialize google map ready call back
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.googleMapFragment);
        mapFragment.getMapAsync(this);

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
        if (addressDisplayFragment != null) {
            addressDisplayFragment.mapClickedAt(latLng);
        }
    }
}
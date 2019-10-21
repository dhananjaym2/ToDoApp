package codepath.com.todoapp.address;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.model.LatLng;

import codepath.com.todoapp.R;

public class AddressDisplayFragment extends Fragment {

    private TextView addressToDisplay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_address_display, container, false);
        addressToDisplay = rootView.findViewById(R.id.addressToDisplay);
        return rootView;
    }

    public void mapClickedAt(LatLng latLng) {
        addressToDisplay.setText("" + latLng.latitude + ", " + latLng.longitude);
    }
}

package codepath.com.todoapp.address;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.model.LatLng;

import codepath.com.todoapp.R;
import codepath.com.todoapp.fetch_address_from_location.LocationAddress;
import codepath.com.todoapp.utils.InternetUtils;

public class AddressDisplayFragment extends Fragment {

    private TextView addressToDisplay;
    private final String logTag = AddressDisplayFragment.class.getSimpleName();
//    List<Place.Field> placeFields = Collections.singletonList(Place.Field.NAME);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_address_display, container, false);
        addressToDisplay = rootView.findViewById(R.id.addressToDisplay);
        return rootView;
    }

    public void mapClickedAt(LatLng clickedLatLng) {
        Log.v(logTag, "clickedLatLng:" + clickedLatLng.latitude + ", " + clickedLatLng.longitude);
        addressToDisplay.setText(getString(R.string.loading));
        fetchAddressFromLatLng(clickedLatLng);
    }

    private void fetchAddressFromLatLng(LatLng clickedLatLng) {
        if (InternetUtils.isConnectedToInternet(getActivity())) {
            LocationAddress locationAddress = new LocationAddress();
            locationAddress.getAddressFromLocation(clickedLatLng.latitude, clickedLatLng.longitude,
                    getActivity(), new GeocoderHandler());
//            locationAddress.getAddressFromLatLng(getActivity(), clickedLatLng.latitude, clickedLatLng.longitude);
        } else {
            addressToDisplay.setText(getString(R.string.no_internet_connection));
        }

    }

    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            addressToDisplay.setText(locationAddress);
        }
    }
}

package felixsam.github.com.foodordering.activities;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import felixsam.github.com.foodordering.R;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {
    private final String TAG = MapActivity.class.getSimpleName();
    private Location location;
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        boolean canGetLocation = false;
        boolean isNetworkEnabled = false;
        boolean isGPSEnabled = false;

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_map);
        mapFragment.getMapAsync(this);

        LocationManager mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // getting GPS status
        isGPSEnabled = mLocationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

        // getting network status
        isNetworkEnabled = mLocationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);


        //mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000,10,this);
    }

    @Override
    public void onMapReady(GoogleMap map){
        map.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Marker"));
    }

    @Override
    public void onLocationChanged(Location location){
        handleNewLocation(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras){
        Log.d(TAG,"Status changed : " + status);
    }

    @Override
    public void onProviderEnabled(String provider){
        Log.d(TAG,"Provider Enabled");
    }

    @Override
    public void onProviderDisabled(String provider){
        Log.d(TAG,"Provider Disabled");

    }
    private void handleNewLocation(Location location){
        Log.d(TAG,location.toString());
    }


}

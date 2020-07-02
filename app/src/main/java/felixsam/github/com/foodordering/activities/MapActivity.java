package felixsam.github.com.foodordering.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.List;
import java.util.Locale;

import felixsam.github.com.foodordering.R;

//https://github.com/googlemaps/android-samples/blob/776869140865253ecd516c475e048f4d19ac9f7c/tutorials/java/CurrentPlaceDetailsOnMap/app/src/main/java/com/example/currentplacedetailsonmap/MapsActivityCurrentPlace.java#L126-L128

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {
    private final String TAG = MapActivity.class.getSimpleName();
    private Location location;
    private double latitude;
    private double longitude;
    Marker currentLocationMarker;

    /****/

    private MapView mapView;
    private GoogleMap map;
    private CameraPosition cameraPosition;


    // The entry point to the Fused Location Provider.
    private FusedLocationProviderClient fusedLocationProviderClient;

    // A default location (Sydney, Australia) and default zoom to use when location permission is
    // not granted.
    private final LatLng defaultLocation = new LatLng(-33.8523341, 151.2106085);
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean locationPermissionGranted;

    // The geographical location where the device is currently located. That is, the last-known
    // location retrieved by the Fused Location Provider.
    private Location lastKnownLocation;

    // Keys for storing activity state.
    // [START maps_current_place_state_keys]
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";
    // [END maps_current_place_state_keys]

    // Used for selecting the current place.
    private static final int M_MAX_ENTRIES = 5;
    private String[] likelyPlaceNames;
    private String[] likelyPlaceAddresses;
    private List[] likelyPlaceAttributions;
    private LatLng[] likelyPlaceLatLngs;

    /****/

    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            lastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            cameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }

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

        //Fused Location Client
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

    }

    @Override
    public void onMapReady(GoogleMap map) {

        map.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Marker"));

        this.map = map;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        map.setMyLocationEnabled(true);

    }

    @Override
    public void onLocationChanged(Location location) {
        //handleNewLocation(location);
        lastKnownLocation = location;

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d(TAG, "Status changed : " + status);
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d(TAG, "Provider Enabled");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d(TAG, "Provider Disabled");

    }

    private void handleNewLocation(Location location) {
        Log.d(TAG, location.toString());
    }


    private void getLastKnownLocation() {
        Log.d(TAG, "getLastKnownLocation: called");

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        fusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if (task.isSuccessful()) {
                    Location location = task.getResult();
                    //GeoPoint geoPoint = new GeoPoint(location.getLatitude(), location.getLongitude());
                    //Log.d(TAG, "onComplete: latitude: " + geoPoint.getLatitude());
                    //Log.d(TAG, "onComplete: latitude: " + geoPoint.getLongitude());
                }
            }
        });
    }

    @Override
    public void onStop(){
        super.onStop();
    }
}

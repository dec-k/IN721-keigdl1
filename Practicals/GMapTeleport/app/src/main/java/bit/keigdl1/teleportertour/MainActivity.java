package bit.keigdl1.teleportertour;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    double lat;
    double lng;

    SupportMapFragment mapFragment;
    GoogleMap mMap;
    LatLng genLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ref and bind btn
        Button btnTele = (Button) findViewById(R.id.btnTele);
        btnTele.setOnClickListener(new teleportClickHandler());

        //Loc manager setup
        //Create a location manager instance
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //Create a criteria instance, criteria types hold criterium for determining the GPS method to use.
        Criteria cri = new Criteria();
        //Set provider info
        String providerName = LocationManager.NETWORK_PROVIDER;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //This is where I could code dialog windows asking the user for permissions.
            //Having this stub here w/o said code prevents a compiler error.
            return;
        }
        lm.requestLocationUpdates(providerName, 400, 1, new customLocListener());

        //Set a last known location
        Location loc = lm.getLastKnownLocation(providerName);

        inflateMap();
    }

    public void inflateMap(){
        //Inflate the fragment
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        //Default to dunedin
        double lat = -45.8624413;
        double lng = 170.5090949;

        //Otherwise, randomise.
        lat = genLocParameter(lat);
        lng = genLocParameter(lng);

        genLatLng = new LatLng(lat,lng);

        mapFragment.getMapAsync(new MapCallBackClass());
    }

    public class MapCallBackClass implements OnMapReadyCallback
    {
        @Override
        public void onMapReady(GoogleMap googleMap){
            mMap = googleMap;

            mMap.addMarker(new MarkerOptions().position(genLatLng).title("Go here!"));

            mMap.moveCamera(CameraUpdateFactory.newLatLng(genLatLng));
        }
    }

    private double genLocParameter(double param){
        Random r = new Random();
        double genVal = r.nextDouble();

        //(maxlat * 2) * genval will give a range of 0~180.
        //90 is then flatly substracted from this value.
        //This allows us to generate random numbers that include negatives.
        double latlngCalc = ((param * 2) * genVal) - param;

        return latlngCalc;
    }


    private class customLocListener implements LocationListener{
        @Override
        public void onLocationChanged(Location location){
            lat = location.getLatitude();
            lng = location.getLongitude();
        }

        //Other methods not used
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
        @Override
        public void onProviderEnabled(String provider) {

        }
        @Override
        public void onProviderDisabled(String provider) {

        }
    }

    public class teleportClickHandler implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            inflateMap();
        }
    }
}

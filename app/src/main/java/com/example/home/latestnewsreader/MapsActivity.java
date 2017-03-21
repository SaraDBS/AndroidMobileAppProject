package com.example.home.latestnewsreader;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener, LocationListener {

    private GoogleMap mMap;
    LocationManager location;
    String provider;


    @Override
    public void onMapLongClick(LatLng point) {

        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        String label = new Date().toString();

        try {
            List<Address> listAddresses = geocoder.getFromLocation(point.latitude, point.longitude, 1);

            if (listAddresses != null && listAddresses.size() > 0) {
                label = listAddresses.get(0).getAddressLine(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        mMap.addMarker(new MarkerOptions()
                .position(point)
                .title(label)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //ActionBar actionBar = getActionBar();
        // actionBar.setDisplayHomeAsUpEnabled(true);

       // location = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

       // provider = location.getBestProvider(new Criteria(), false);

        Intent c = getIntent();
        Log.i("locationinfo", Integer.toString(c.getIntExtra("locationinfo", -1)));


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        // LatLng sydney = new LatLng(-34, 151);

       if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        location = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        provider = location.getBestProvider(new Criteria(), false);

        //location.requestLocationUpdates(provider, 400, 1, this);
        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);



        LatLng dublin = new LatLng(53.348696, -6.260160);
        mMap.addMarker(new MarkerOptions().position(dublin).title("Eason BookShop"));
        LatLng windingStair = new LatLng(53.346838, -6.263647);
        mMap.addMarker(new MarkerOptions().position(windingStair).title("Winding Stair BookShop"));
        LatLng chapters = new LatLng(53.352148, -6.264081);
        mMap.addMarker(new MarkerOptions().position(chapters).title("Chapters BookShop"));
        LatLng gutter = new LatLng(53.345236, -6.269174);
        mMap.addMarker(new MarkerOptions().position(gutter).title("The Gutter BookShop"));
        LatLng dubray = new LatLng(53.340931, -6.260720);
        mMap.addMarker(new MarkerOptions().position(dubray).title("Dubray Books"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dublin,14f));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(windingStair));
        mMap.setOnMapLongClickListener(this);
    }

    @Override
    public void onLocationChanged(Location userlocation) {
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(userlocation.getLatitude(),userlocation.getLongitude()),10));
    }

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

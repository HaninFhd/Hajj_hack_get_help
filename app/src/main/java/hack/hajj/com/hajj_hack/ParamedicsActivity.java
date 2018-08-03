package hack.hajj.com.hajj_hack;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class ParamedicsActivity extends AppCompatActivity
        implements OnMapReadyCallback , NavigationView.OnNavigationItemSelectedListener {
    private GoogleMap mMap;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paramedics);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        context = getApplicationContext();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


       BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigations);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                    // Handle navigation view item clicks here.
                    int id = item.getItemId();

                    if (id == R.id.navigation_orders) {
                        // Handle the camera action
                        Toast.makeText(context, "navigation_orders clicked", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), ParamedicsOrdersActivity.class));
                    } else if (id == R.id.navigation_state) {
                        Toast.makeText(context, "navigation_state clicked", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), ParamedicsOrdersstateActivity.class));
                    } else if (id == R.id.navigation_get_help) {
                        Toast.makeText(context, "navigation_get_help clicked", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), ParamedicsNewOrderActivity.class));
                    }

                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);
                    return true;
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }


    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng makkah = new LatLng(21.422163, 39.825817);
        mMap.addMarker(new MarkerOptions().position(makkah));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(makkah));
        float zoomLevel = 16.0f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(makkah, zoomLevel));
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style_json));

            if (!success) {
                Log.e("", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("", "Can't find style. Error: ", e);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.paramedics, menu);
        MenuInflater inflator = getMenuInflater();
        inflator.inflate(R.menu.activity_paramedics_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.nav_edit) {
            Toast.makeText(context, "nav_edit clicked", Toast.LENGTH_SHORT).show();
            // startActivity(new Intent(getApplicationContext(), ParamedicsNewOrderActivity.class));
        }
        else if (id == R.id.nav_nav) {
            Toast.makeText(context, "nav_edit clicked", Toast.LENGTH_SHORT).show();
            // startActivity(new Intent(getApplicationContext(), ParamedicsNewOrderActivity.class));
        }
        else if (id == R.id.nav_signout) {
            Toast.makeText(context, "nav_edit clicked", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}

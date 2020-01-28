package com.example.mapsparttwo;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public class MainActivity extends AppCompatActivity {

    MapView mv;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // This line sets the user agent, a requirement to download OSM maps
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_main);

        mv = (MapView) findViewById(R.id.map1);


        mv.getController().setZoom(16.0);
        // southampton 50.9076, -1.4007
        // fenhurst 51.05, -0.72
        // home 50.9229, -1.3508
        mv.getController().setCenter(new GeoPoint(51.05, -0.72));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.choosemap) {
            Intent intent = new Intent(this, MapChooseActivity.class);
            startActivityForResult(intent, 0);
            // react to the menu item being selected...
            return true;
        }
        if (item.getItemId() == R.id.setcoordinates) {
            Intent intent = new Intent(this, SetCoordinatesActivity.class);
            startActivityForResult(intent, 1);
            // react to the menu item being selected...
            return true;
        }
        return false;
    }

    public void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        double lat = Double.parseDouble(prefs.getString("lat", "50.9"));
        double lon = Double.parseDouble(prefs.getString("lon", "-1.4"));
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                Bundle extras = intent.getExtras();
                boolean hikebikemap = extras.getBoolean("com.example.hikebikemap");
                if (hikebikemap == true) {
                    mv.setTileSource(TileSourceFactory.HIKEBIKEMAP);
                } else {
                    mv.setTileSource(TileSourceFactory.MAPNIK);
                }

            }
        } else if (requestCode == 1) {
            if (resultCode == RESULT_FIRST_USER) {
                Bundle extras2 = intent.getExtras();
                boolean go = extras2.getBoolean("com.example.go");
                double longitude = extras2.getDouble("com.example.longitude");
                double latitude = extras2.getDouble("com.example.latitude");
                if (go == true) {
                    mv = findViewById(R.id.map1);
                    mv.setBuiltInZoomControls(true);
                    mv.getController().setZoom(16);
                    // southampton 50.9076, -1.4007
                    // fenhurst 51.05, -0.72
                    // home 50.9229, -1.3508
                    mv.getController().setCenter(new GeoPoint(latitude, longitude));
                }
            }
        }
    }
}


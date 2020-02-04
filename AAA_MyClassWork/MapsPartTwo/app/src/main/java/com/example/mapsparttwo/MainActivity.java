package com.example.mapsparttwo;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
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
    private Double latitude = Constants.DEFAULT_LAT;
    private Double longitude = Constants.DEFAULT_LON;
    private Double zoom = Constants.DEFAULT_ZOOM;
    private String mapCode = null;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mapCode = savedInstanceState.getString ("com.example.mapcode");
        }
        if(mapCode==null) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            mapCode = prefs.getString("type", Constants.DEFAULT_MAP);
        }

        // This line sets the user agent, a requirement to download OSM maps
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_main);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

            try{
                latitude = Double.parseDouble((prefs.getString("lat", Constants.DEFAULT_LAT.toString())));
                longitude = Double.parseDouble(prefs.getString("lon", Constants.DEFAULT_LON.toString()));
                zoom = Double.parseDouble((prefs.getString("zoom", Constants.DEFAULT_ZOOM.toString())));
                mapCode = prefs.getString("type", Constants.NORMAL_MAP);
                mv = findViewById(R.id.map1);
                mv.getController().setZoom(zoom);
                mv.getController().setCenter(new GeoPoint(latitude, longitude));
                if (Constants.CYCLE_MAP.equals(mapCode)){
                    mv.setTileSource(TileSourceFactory.HIKEBIKEMAP);
                }
                else if (Constants.NORMAL_MAP.equals(mapCode)) {
                    mv.setTileSource(TileSourceFactory.MAPNIK);
                }

            } catch (Exception ex){
                 popupMessage("invalid default preferenced entry: "+ex.getMessage());
            }


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
        if (item.getItemId() == R.id.preferences) {
            Intent intent = new Intent(this, MyPrefsActivity.class );
            startActivityForResult(intent, 2);
        }
        if (item.getItemId() == R.id.lists){
            Intent intent = new Intent(this, ListsActivity.class);
            startActivityForResult(intent, 0);
        }
        return false;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            // result from choose map
            if (resultCode == RESULT_OK) {
                Bundle extras = intent.getExtras();
                mapCode = extras.getString("com.example.mapcode");
                if (Constants.NORMAL_MAP.equals(mapCode)) {
                    mv.setTileSource(TileSourceFactory.MAPNIK);
                } else {
                    mv.setTileSource(TileSourceFactory.HIKEBIKEMAP);
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
                    mv.getController().setZoom(16.0);
                    // southampton 50.9076, -1.4007
                    // fenhurst 51.05, -0.72
                    // home 50.9229, -1.3508
                    mv.getController().setCenter(new GeoPoint(latitude, longitude));
                }
            }
        } else if (requestCode == 2){
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            Double lat;
            Double lon;
            Double zoom;
            String map;

            try{
                lat = Double.parseDouble((prefs.getString("lat", Constants.DEFAULT_LAT.toString())));
                lon = Double.parseDouble(prefs.getString("lon", Constants.DEFAULT_LON.toString()));
                zoom = Double.parseDouble((prefs.getString("zoom", Constants.DEFAULT_ZOOM.toString())));
                mapCode = prefs.getString("type", Constants.DEFAULT_MAP);
                mv = findViewById(R.id.map1);
                mv.getController().setZoom(zoom);
                mv.getController().setCenter(new GeoPoint(lat, lon));
                if (Constants.CYCLE_MAP.equals(mapCode)){
                    mv.setTileSource(TileSourceFactory.HIKEBIKEMAP);
                }
                else if (Constants.NORMAL_MAP.equals(mapCode)) {
                    mv.setTileSource(TileSourceFactory.MAPNIK);
                }

            } catch (Exception ex){
                popupMessage("invalid default preferenced entry: "+ex.getMessage());
            }

        }

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy()  {
        super.onDestroy();
        // save the chosen map
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString ("com.example.mapcode", mapCode);
        editor.commit();
    }

    @Override
    public void onSaveInstanceState (Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("com.example.mapcode", mapCode);
    }

    private void popupMessage(String message){
        new AlertDialog.Builder(this).setPositiveButton("OK", null).setMessage(message).show();
    }


}


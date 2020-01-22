package com.example.cgallen.mapping1;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public class MainActivity extends AppCompatActivity implements OnClickListener {

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

        Button go = (Button)findViewById(R.id.btn1);
        go.setOnClickListener(this);

}

    @Override
    public void onClick(View view) {

        mv = (MapView) findViewById(R.id.map1);
        double longitude ;
        double latitude;
        EditText et = (EditText)findViewById(R.id.et1);
        EditText et2 = (EditText)findViewById(R.id.et2);
        switch (view.getId()) {
            case R.id.btn1:
                try {
                    mv.setBuiltInZoomControls(true);
                    mv.getController().setZoom(16);
                    // southampton 50.9076, -1.4007
                    // fenhurst 51.05, -0.72
                    // home 50.9229, -1.3508
                    longitude = Double.parseDouble(et2.getText().toString());
                    latitude = Double.parseDouble(et.getText().toString());
                    mv.getController().setCenter(new GeoPoint(latitude, longitude));
                }catch (Exception e){
                    Log.e("ERROR", "ERROR ensure numbers are filled in:" + e.toString());
                }


                break;

            default:

                break;
        }

    }
}
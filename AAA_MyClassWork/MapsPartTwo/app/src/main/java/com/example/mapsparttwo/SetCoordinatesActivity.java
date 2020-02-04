package com.example.mapsparttwo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;



public class SetCoordinatesActivity extends AppCompatActivity implements View.OnClickListener
{

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_setcoordinates);

        Button go = (Button)findViewById(R.id.btn2);
        go.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        Intent intent = new Intent();
        Bundle bundle=new Bundle();
        boolean go = false;
        double longitude = 0;
        double latitude = 0;
        if (v.getId()==R.id.btn2)
        {
            go=true;
            EditText et3 = findViewById(R.id.et3);
            EditText et4 = findViewById(R.id.et4);
            try {
                longitude = Double.parseDouble(et4.getText().toString());
                latitude = Double.parseDouble(et3.getText().toString());
            } catch (Exception e) {
                Log.e("ERROR", "ERROR ensure numbers are filled in:" + e.toString());
            }
        }
        bundle.putDouble("com.example.longitude", longitude);
        bundle.putDouble("com.example.latitude", latitude);
        bundle.putBoolean("com.example.go",go);
        intent.putExtras(bundle);
        setResult(RESULT_FIRST_USER,intent);
        finish();
    }
}
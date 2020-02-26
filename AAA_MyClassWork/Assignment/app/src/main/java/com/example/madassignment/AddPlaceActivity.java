package com.example.madassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.widget.Spinner;

public class AddPlaceActivity  extends AppCompatActivity implements View.OnClickListener{

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);

        Spinner spinner = (Spinner) findViewById(R.id.typeSpinner);
        String[] placeTypes = getResources().getStringArray(R.array.TypesOfPlace);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_layout, R.id.txtBlank ,placeTypes);
        spinner.setAdapter(adapter);
    }

    public void onClick(View v) {

    }
}

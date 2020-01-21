package com.example.reportproblems;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity implements OnClickListener {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button clear = (Button)findViewById(R.id.btn1);
        clear.setOnClickListener(this);
        Button ok = (Button)findViewById(R.id.btn2);
        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn1:
                // do your code
                break;

            case R.id.btn2:
                // do your code
                break;

            default:
                break;
        }

    }
}
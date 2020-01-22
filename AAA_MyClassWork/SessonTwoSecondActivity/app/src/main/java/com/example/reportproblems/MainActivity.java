package com.example.reportproblems;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.EditText;

import java.util.concurrent.atomic.AtomicInteger;


public class MainActivity extends AppCompatActivity implements OnClickListener {


    /** Called when the activity is first created. */
    private static AtomicInteger numberClicks = new AtomicInteger(0);

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

        EditText et = (EditText)findViewById(R.id.et1);
        et.setText("");
        switch (view.getId()) {
            case R.id.btn1:
                // clear
                et.setHint("@string/");
                break;

            case R.id.btn2:
                // ok
                String msg1 = getString(R.string.acknowlege);
                int i = numberClicks.addAndGet(1);
                msg1 = String.format(msg1, Integer.toString(i)); // Thank you for reporting your problem. Problem id:%s
                et.setText("");
                et.setHint(msg1);
                break;

            default:

                break;
        }

    }
}
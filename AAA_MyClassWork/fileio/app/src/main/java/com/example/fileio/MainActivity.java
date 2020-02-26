package com.example.fileio;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(this);
        Button ok = (Button) findViewById(R.id.btnLoad);
        ok.setOnClickListener(this);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 0);

    }

    @Override
    public void onClick(View view) {

        EditText et = (EditText) findViewById(R.id.etFile);
        et.setText("");
        switch (view.getId()) {
            case R.id.btnSave:
                try {
                    PrintWriter pw = new PrintWriter(new FileWriter((Environment.getExternalStorageDirectory().getAbsolutePath() + "/textedit.txt")));
                    pw.println(et.getText().toString());
                    pw.close(); // close the file to ensure data is flushed to file
                } catch (IOException e) {
                    new AlertDialog.Builder(this).setPositiveButton("OK", null).setMessage("ERROR: " + e).show();
                }


                break;

            case R.id.btnLoad:
                try {
                    FileReader fr = new FileReader(Environment.getExternalStorageDirectory().getAbsolutePath() + "/textedit.txt");
                    BufferedReader reader = new BufferedReader(fr);
                    String line = "";
                    while((line = reader.readLine()) != null)
                    {
                        et.setText(line);
                        new AlertDialog.Builder(this).setPositiveButton("OK", null).setMessage("ERROR: " + line).show();
                    }
                    reader.close();
                } catch (IOException e) {
                    new AlertDialog.Builder(this).setPositiveButton("OK", null).setMessage("ERROR: " + e).show();
                }
                break;

            default:

                break;
        }
    }
}

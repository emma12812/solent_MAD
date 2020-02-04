package com.example.mapsparttwo;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class ListsActivity extends ListActivity {

    String[] types, details;
    private String mapCode = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        types = new String[]{ "Normal Map", "Hike Bike Map"};
        details = new String[]{ "Typical map found", "Map showing bike routes"};
        MyAdapter adapter = new MyAdapter();
        setListAdapter(adapter);
    }

    public void onListItemClick(ListView lv, View view, int index, long id) {
        // handle list item selection
        Intent intent = new Intent();
        Bundle bundle=new Bundle();

        String[] mapcode = {Constants.DEFAULT_MAP, Constants.CYCLE_MAP };

        bundle.putString("com.example.mapcode",mapcode[index]);
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();
    }

    private void popupMessage(String message) {
        new AlertDialog.Builder(this).setPositiveButton("OK", null).setMessage(message).show();
    }

    public class MyAdapter extends ArrayAdapter<String> {
        public MyAdapter() {
            super(ListsActivity.this, android.R.layout.activity_list_item, types);
        }

        public View getView(int index, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.activity_lists, parent, false);
            }
            TextView title = (TextView) view.findViewById(R.id.type), detail =
                    (TextView) view.findViewById(R.id.details);
            title.setText(types[index]);
            detail.setText(details[index]);
            return view;
        }
    }
}
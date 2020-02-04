package com.example.mapsparttwo;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class ListsActivity extends ListActivity {

    String[] types, details;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        types = new String[]{"Hike Bike Map", "Normal Map"};
        details = new String[]{"Map showing bike routes , Useful for bikers", "Typical map found, Useful for normal use"};
        MyAdapter adapter = new MyAdapter();
        setListAdapter(adapter);
    }

    public void onListItemClick(ListView lv, View view, int index, long id) {
        // handle list item selection
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
package com.example.expensemanager;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by imrokraft on 5/5/15.
 */
public class newtwo extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new2);
        ArrayList<String> arr=new ArrayList<String>();
        arr.add("Add");
        arr.add("Total");
        arr.add("View/Edit");
        arr.add("Show by Month");
        arr.add("Show by Category");
        arr.add("Settings");
        final ListView lsv=(ListView)findViewById(R.id.listview2);
    }
}

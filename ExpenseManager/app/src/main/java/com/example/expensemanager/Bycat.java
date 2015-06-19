package com.example.expensemanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.parse.ParseUser;

import java.util.ArrayList;

public class Bycat extends ActionBarActivity {

    Dbhandler dbh;
    ArrayList<details> result;
    SharedPreferences share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bycat);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0F62A6"));
        ab.setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.expsmall);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        Button catbut = (Button) findViewById(R.id.catbut);
//        Spinner spcat=(Spinner)findViewById(R.id.spincat);
//        String [] items={"Select Category"};
//        CustomArrayAdapter<String> myadapter= new CustomArrayAdapter<String>(this,items);
//        spcat.setAdapter(myadapter);
        registerForContextMenu(catbut);

    }

    public void loadloginView() {
        Intent intent = new Intent(this, ActivityLogin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cloud:
                Intent inc1 = new Intent(getApplication(), CloudListView.class);
                startActivity(inc1);
                break;
            case R.id.action_logout:
                share = getSharedPreferences("UsernamePrefs", MODE_PRIVATE);
                int s = share.getInt("loginvalue", 0);
                if (s == 0) {
                    ParseUser.logOut();
                    ParseUser newUser = ParseUser.getCurrentUser();
                    loadloginView();
                } else if (s == 1) {
                    LoginManager.getInstance().logOut();
                    ParseUser.logOut();
                    loadloginView();
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.category, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.drinks:
                String d = "Drinks";
                dbh = new Dbhandler(getApplicationContext());
                result = dbh.category(d);
                if (result == null || result.isEmpty()) {
                    Toast.makeText(Bycat.this, "No Records Found!", Toast.LENGTH_LONG).show();
                } else {
                    ListView lv = (ListView) findViewById(R.id.listView21);
                    lv.setAdapter(new UserAdaptercat(getApplicationContext(), dbh.category(d)));
                }
                break;
            case R.id.dress:
                String c = "Dress";
                dbh = new Dbhandler(getApplicationContext());
                result = dbh.category(c);
                if (result == null || result.isEmpty()) {
                    Toast.makeText(Bycat.this, "No Records Found!", Toast.LENGTH_LONG).show();
                } else {
                    ListView lv = (ListView) findViewById(R.id.listView21);
                    lv.setAdapter(new UserAdaptercat(getApplicationContext(), dbh.category(c)));
                }
                break;
            case R.id.food:
                String f = "Food";
                dbh = new Dbhandler(getApplicationContext());
                result = dbh.category(f);
                if (result == null || result.isEmpty()) {
                    Toast.makeText(Bycat.this, "No Records Found!", Toast.LENGTH_LONG).show();
                } else {
                    ListView lv = (ListView) findViewById(R.id.listView21);
                    lv.setAdapter(new UserAdaptercat(getApplicationContext(), dbh.category(f)));
                }
                break;
            case R.id.others:
                String o = "Others";
                dbh = new Dbhandler(getApplicationContext());
                result = dbh.category(o);
                if (result == null || result.isEmpty()) {
                    Toast.makeText(Bycat.this, "No Records Found!", Toast.LENGTH_LONG).show();
                } else {
                    ListView lv = (ListView) findViewById(R.id.listView21);
                    lv.setAdapter(new UserAdaptercat(getApplicationContext(), dbh.category(o)));
                }
                break;
            case R.id.personal:
                String p = "Personal";
                dbh = new Dbhandler(getApplicationContext());
                result = dbh.category(p);
                if (result == null || result.isEmpty()) {
                    Toast.makeText(Bycat.this, "No Records Found!", Toast.LENGTH_LONG).show();
                } else {
                    ListView lv = (ListView) findViewById(R.id.listView21);
                    lv.setAdapter(new UserAdaptercat(getApplicationContext(), dbh.category(p)));
                }
                break;
            case R.id.utilities:
                String u = "Utilities";
                dbh = new Dbhandler(getApplicationContext());
                result = dbh.category(u);
                if (result == null || result.isEmpty()) {
                    Toast.makeText(Bycat.this, "No Records Found!", Toast.LENGTH_LONG).show();
                } else {
                    ListView lv = (ListView) findViewById(R.id.listView21);
                    lv.setAdapter(new UserAdaptercat(getApplicationContext(), dbh.category(u)));
                }
                break;
        }
        return super.onContextItemSelected(item);
    }
}

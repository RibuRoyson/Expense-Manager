package com.example.expensemanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.parse.ParseUser;

import java.util.ArrayList;

public class Monthwise extends ActionBarActivity {
    ListView lvv;
    Dbhandler dbh;
    int i = 0;
    int length;
    UserAdaptermon myuser;
    SharedPreferences share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monthwise);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0F62A6"));
        ab.setBackgroundDrawable(colorDrawable);
        Button monbut = (Button) findViewById(R.id.monbut);
        registerForContextMenu(monbut);
        lvv = (ListView) findViewById(R.id.list1);


    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // TODO Auto-generated method stub

        switch (item.getItemId()) {
            case R.id.janu: {
                i = 1;
                Dbhandler dbh = new Dbhandler(getApplicationContext());
                ArrayList<details> results = new ArrayList<details>();
                results = dbh.monthWiseData(i);
                length = results.size();
                Log.e("arraylist length", "" + length);
                if (length == 0) {
                    Toast.makeText(Monthwise.this, "No Records Found!", Toast.LENGTH_LONG).show();
                } else {
                    myuser = new UserAdaptermon(getApplicationContext(), dbh.monthWiseData(1));
                    lvv.setAdapter(myuser);
                }
                break;
            }
            case R.id.feb: {
                i = 2;
                Dbhandler dbh = new Dbhandler(getApplicationContext());
                ArrayList<details> result = new ArrayList<details>();
                result = dbh.monthWiseData(i);
                if (result.isEmpty() == true) {
                    Toast.makeText(Monthwise.this, "No Records Found!", Toast.LENGTH_LONG).show();
                } else {
                    myuser = new UserAdaptermon(getApplicationContext(), dbh.monthWiseData(2));
                    lvv.setAdapter(myuser);
                }
                break;
            }
            case R.id.mar: {
                i = 3;
                Dbhandler dbh = new Dbhandler(getApplicationContext());
                ArrayList<details> result = new ArrayList<details>();
                result = dbh.monthWiseData(i);
                if (result.isEmpty() == true) {
                    Toast.makeText(Monthwise.this, "No Records Found!", Toast.LENGTH_LONG).show();
                } else {
                    myuser = new UserAdaptermon(getApplicationContext(), dbh.monthWiseData(3));
                    lvv.setAdapter(myuser);
                }
                break;
            }
            case R.id.apr: {
                i = 4;
                Dbhandler dbh = new Dbhandler(getApplicationContext());
                ArrayList<details> result = new ArrayList<details>();
                result = dbh.monthWiseData(i);
                if (result.isEmpty() == true) {
                    Toast.makeText(Monthwise.this, "No Records Found!", Toast.LENGTH_LONG).show();
                } else {
                    myuser = new UserAdaptermon(getApplicationContext(), dbh.monthWiseData(4));
                    lvv.setAdapter(myuser);
                }
                break;
            }
            case R.id.may: {
                i = 5;
                Dbhandler dbh = new Dbhandler(getApplicationContext());
                ArrayList<details> result = new ArrayList<details>();
                result = dbh.monthWiseData(i);
                if (result.isEmpty() == true) {
                    Toast.makeText(Monthwise.this, "No Records Found!", Toast.LENGTH_LONG).show();
                } else {
                    myuser = new UserAdaptermon(getApplicationContext(), dbh.monthWiseData(5));
                    lvv.setAdapter(myuser);
                }
                break;
            }
            case R.id.jun: {
                i = 6;
                Dbhandler dbh = new Dbhandler(getApplicationContext());
                ArrayList<details> result = new ArrayList<details>();
                result = dbh.monthWiseData(i);
                if (result.isEmpty() == true) {
                    Toast.makeText(Monthwise.this, "No Records Found!", Toast.LENGTH_LONG).show();
                } else {
                    myuser = new UserAdaptermon(getApplicationContext(), dbh.monthWiseData(6));
                    lvv.setAdapter(myuser);
                }
                break;
            }
            case R.id.jul: {
                i = 7;
                Dbhandler dbh = new Dbhandler(getApplicationContext());
                ArrayList<details> result = new ArrayList<details>();
                result = dbh.monthWiseData(i);
                if (result.isEmpty() == true) {
                    Toast.makeText(Monthwise.this, "No Records Found!", Toast.LENGTH_LONG).show();
                } else {
                    myuser = new UserAdaptermon(getApplicationContext(), dbh.monthWiseData(7));
                    lvv.setAdapter(myuser);
                }
                break;
            }
            case R.id.aug: {
                i = 8;
                Dbhandler dbh = new Dbhandler(getApplicationContext());
                ArrayList<details> result = new ArrayList<details>();
                result = dbh.monthWiseData(i);
                if (result.isEmpty() == true) {
                    Toast.makeText(Monthwise.this, "No Records Found!", Toast.LENGTH_LONG).show();
                } else {
                    myuser = new UserAdaptermon(getApplicationContext(), dbh.monthWiseData(8));
                    lvv.setAdapter(myuser);
                }
                break;
            }
            case R.id.sep: {
                i = 9;
                Dbhandler dbh = new Dbhandler(getApplicationContext());
                ArrayList<details> result = new ArrayList<details>();
                result = dbh.monthWiseData(i);
                if (result.isEmpty() == true) {
                    Toast.makeText(Monthwise.this, "No Records Found!", Toast.LENGTH_LONG).show();
                } else {
                    myuser = new UserAdaptermon(getApplicationContext(), dbh.monthWiseData(9));
                    lvv.setAdapter(myuser);
                }
                break;
            }
            case R.id.oct: {
                i = 10;
                Dbhandler dbh = new Dbhandler(getApplicationContext());
                ArrayList<details> result = new ArrayList<details>();
                result = dbh.monthWiseData(i);
                if (result.isEmpty() == true) {
                    Toast.makeText(Monthwise.this, "No Records Found!", Toast.LENGTH_LONG).show();
                } else {
                    myuser = new UserAdaptermon(getApplicationContext(), dbh.monthWiseData(10));
                    lvv.setAdapter(myuser);
                }
                break;
            }
            case R.id.nov: {
                i = 11;
                Dbhandler dbh = new Dbhandler(getApplicationContext());
                ArrayList<details> result = new ArrayList<details>();
                result = dbh.monthWiseData(i);
                if (result.isEmpty() == true) {
                    Toast.makeText(Monthwise.this, "No Records Found!", Toast.LENGTH_LONG).show();
                } else {
                    myuser = new UserAdaptermon(getApplicationContext(), dbh.monthWiseData(11));
                    lvv.setAdapter(myuser);
                }
                break;
            }
            case R.id.dec: {
                i = 12;
                Dbhandler dbh = new Dbhandler(getApplicationContext());
                ArrayList<details> result = new ArrayList<details>();
                result = dbh.monthWiseData(i);
                if (result.isEmpty() == true) {
                    Toast.makeText(Monthwise.this, "No Records Found!", Toast.LENGTH_LONG).show();
                } else {
                    myuser = new UserAdaptermon(getApplicationContext(), dbh.monthWiseData(12));
                    lvv.setAdapter(myuser);
                }
                break;
            }
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.month, menu);
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

    public void loadloginView() {
        Intent intent = new Intent(this, ActivityLogin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}

package com.example.expensemanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.parse.ParseUser;

public class Showresult extends ActionBarActivity {
    TextView tv, tv1, mnth, rs, rs1, rs2;
    int i = 0;
    String length;
    String results;
    Dbhandler dbh;
    SharedPreferences share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showresult);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.expsmall);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0F62A6"));
        ab.setBackgroundDrawable(colorDrawable);
        Button b1 = (Button) findViewById(R.id.selectmnth);
        registerForContextMenu(b1);
        tv = (TextView) findViewById(R.id.monthtotal);
        rs2 = (TextView) findViewById(R.id.rupees3);
    }

    public void totalclick(View v) {
        tv1 = (TextView) findViewById(R.id.totv);
        rs = (TextView) findViewById(R.id.rupees);
        dbh = new Dbhandler(Showresult.this);
        int x = dbh.total();
        tv1.setText("" + x);
        rs.setText("\u20B9" + ".");
    }

    public void monthclick(View v) {
        mnth = (TextView) findViewById(R.id.monthv);
        rs1 = (TextView) findViewById(R.id.rupees2);
        dbh = new Dbhandler(Showresult.this);
        int y = dbh.totalmnth();
        mnth.setText("" + y);
        rs1.setText("\u20B9" + ".");
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.month, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.janu: {
                i = 1;
                dbh = new Dbhandler(getApplicationContext());
                results = String.valueOf(dbh.monthtotal(i));
                Log.e("arraylist length", "" + length);
                if (results == null || results.isEmpty()) {
                    Toast.makeText(Showresult.this, "Database Empty", Toast.LENGTH_LONG).show();
                } else {
                    tv.setText(results);
                    rs2.setText("\u20B9" + ".");
                }
                break;
            }
            case R.id.feb: {
                i = 2;
                dbh = new Dbhandler(getApplicationContext());
                results = String.valueOf(dbh.monthtotal(i));

                Log.e("arraylist length", "" + length);
                if (length == "") {
                    Toast.makeText(Showresult.this, "Database Empty", Toast.LENGTH_LONG).show();
                } else {
                    tv.setText(results);
                    rs2.setText("\u20B9" + ".");
                }
                break;
            }
            case R.id.mar: {
                i = 3;
                dbh = new Dbhandler(getApplicationContext());
                results = String.valueOf(dbh.monthtotal(i));
                tv.setText(results);
                rs2.setText("\u20B9" + ".");

                break;
            }
            case R.id.apr: {
                i = 4;
                Dbhandler dbh = new Dbhandler(getApplicationContext());
                results = String.valueOf(dbh.monthtotal(i));
                tv.setText(results);
                rs2.setText("\u20B9" + ".");

                break;
            }
            case R.id.may: {
                i = 5;
                dbh = new Dbhandler(getApplicationContext());
                results = String.valueOf(dbh.monthtotal(i));
                tv.setText(results);
                rs2.setText("\u20B9" + ".");

                break;
            }
            case R.id.jun: {
                i = 6;
                dbh = new Dbhandler(getApplicationContext());
                results = String.valueOf(dbh.monthtotal(i));
                tv.setText(results);
                rs2.setText("\u20B9" + ".");

                break;
            }
            case R.id.jul: {
                i = 7;
                dbh = new Dbhandler(getApplicationContext());
                results = String.valueOf(dbh.monthtotal(i));
                tv.setText(results);
                rs2.setText("\u20B9" + ".");

                break;
            }
            case R.id.aug: {
                i = 8;
                dbh = new Dbhandler(getApplicationContext());
                results = String.valueOf(dbh.monthtotal(i));
                tv.setText(results);
                rs2.setText("\u20B9" + ".");

                break;
            }
            case R.id.sep: {
                i = 9;
                dbh = new Dbhandler(getApplicationContext());
                results = String.valueOf(dbh.monthtotal(i));
                tv.setText(results);
                rs2.setText("\u20B9" + ".");

                break;
            }
            case R.id.oct: {
                i = 10;
                dbh = new Dbhandler(getApplicationContext());
                results = String.valueOf(dbh.monthtotal(i));
                tv.setText(results);
                rs2.setText("\u20B9" + ".");

                break;
            }
            case R.id.nov: {
                i = 11;
                dbh = new Dbhandler(getApplicationContext());
                results = String.valueOf(dbh.monthtotal(i));
                tv.setText(results);
                rs2.setText("\u20B9" + ".");

                break;
            }
            case R.id.dec: {
                i = 12;
                dbh = new Dbhandler(getApplicationContext());
                results = String.valueOf(dbh.monthtotal(i));
                try {
                    if (results != null) {
                        tv.setText(results);
                        rs2.setText("\u20B9" + ".");
                        break;
                    } else {
                        Toast.makeText(getApplicationContext(), "No Entry", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        return super.onContextItemSelected(item);
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

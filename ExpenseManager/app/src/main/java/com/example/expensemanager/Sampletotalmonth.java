package com.example.expensemanager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.echo.holographlibrary.Bar;
import com.facebook.login.LoginManager;
import com.parse.ParseUser;

import java.util.ArrayList;

/**
 * Created by imrokraft on 13/5/15.
 */
public class Sampletotalmonth extends ActionBarActivity {
    Dbhandler dbh;
    ProgressDialog pb;
    SharedPreferences share;
    ArrayList<Integer> total;
    float a, b, c, e, f, g, h, i, j, k, l, m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sampletotalmonth);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0F62A6"));
        ab.setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.expsmall);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        dbh = new Dbhandler(getApplicationContext());
        total = dbh.monthwise();
        Task obj = new Task();
        if (total == null || total.isEmpty()) {
            Toast.makeText(getApplicationContext(), "No records Found!", Toast.LENGTH_SHORT).show();
        } else {
            obj.execute();
        }
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

    public class Task extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb = ProgressDialog.show(Sampletotalmonth.this, "Expense Manager", "Loading...");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            pb.dismiss();
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(String... params) {
            dbh = new Dbhandler(getApplicationContext());
            a = dbh.monthtotal(1);
            b = dbh.monthtotal(2);
            c = dbh.monthtotal(3);
            e = dbh.monthtotal(4);
            f = dbh.monthtotal(5);
            g = dbh.monthtotal(6);
            h = dbh.monthtotal(7);
            i = dbh.monthtotal(8);
            j = dbh.monthtotal(9);
            k = dbh.monthtotal(10);
            l = dbh.monthtotal(11);
            m = dbh.monthtotal(12);
            ArrayList<Bar> points = new ArrayList<Bar>();
            Bar d = new Bar();
            d.setColor(Color.parseColor("#99CC00"));
            d.setName("January");
            try {
                d.setValue(a);
            } catch (Exception se) {
                d.setValue(0);
            }
            Bar d1 = new Bar();
            d1.setColor(Color.parseColor("#FFBB33"));
            d1.setName("February");
            try {
                d1.setValue(b);
            } catch (Exception se) {
                d1.setValue(0);
            }

            Bar d3 = new Bar();
            d3.setColor(Color.parseColor("#f15916"));
            d3.setName("March");
            try {
                d3.setValue(c);
            } catch (Exception e) {
                d3.setValue(0);
            }
            Bar d4 = new Bar();
            d4.setColor(Color.parseColor("#68f116"));
            d4.setName("April");
            try {
                d4.setValue(e);
            } catch (Exception e) {
                d4.setValue(0);
            }
            Bar d5 = new Bar();
            d5.setColor(Color.parseColor("#16f1a1"));
            d5.setName("May");
            try {
                d5.setValue(f);
            } catch (Exception se) {
                d5.setValue(0);
            }
            Bar d6 = new Bar();
            d6.setColor(Color.parseColor("#16b6f1"));
            d6.setName("June");
            try {
                d6.setValue(g);
            } catch (Exception se) {
                d6.setValue(0);
            }
            Bar d7 = new Bar();
            d7.setColor(Color.parseColor("#163af1"));
            d7.setName("July");
            try {
                d7.setValue(h);
            } catch (Exception se) {
                d7.setValue(0);
            }
            Bar d8 = new Bar();
            d8.setColor(Color.parseColor("#a116f1"));
            d8.setName("August");
            try {
                d8.setValue(i);
            } catch (Exception se) {
                d8.setValue(0);
            }
            Bar d9 = new Bar();
            d9.setColor(Color.parseColor("#f116c5"));
            d9.setName("September");
            try {
                d9.setValue(j);
            } catch (Exception se) {
                d9.setValue(0);
            }
            Bar d10 = new Bar();
            d10.setColor(Color.parseColor("#f1161b"));
            d10.setName("October");
            try {
                d10.setValue(k);
            } catch (Exception se) {
                d10.setValue(0);
            }
            Bar d11 = new Bar();
            d11.setColor(Color.parseColor("#5c5050"));
            d11.setName("November");
            try {
                d11.setValue(l);
            } catch (Exception se) {
                d11.setValue(0);
            }
            Bar d12 = new Bar();
            d12.setColor(Color.parseColor("#f9e85a"));
            d12.setName("December");
            try {
                d12.setValue(m);
            } catch (Exception se) {
                d12.setValue(0);
            }
            points.add(d);
            points.add(d1);
            points.add(d3);
            points.add(d4);
            points.add(d5);
            points.add(d6);
            points.add(d7);
            points.add(d8);
            points.add(d9);
            points.add(d10);
            points.add(d11);
            points.add(d12);
            com.echo.holographlibrary.BarGraph g = (com.echo.holographlibrary.BarGraph) findViewById(R.id.graph);
            g.setBars(points);

            return null;
        }
    }
}

package com.example.expensemanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.echo.holographlibrary.Line;
import com.echo.holographlibrary.LineGraph;
import com.echo.holographlibrary.LinePoint;
import com.facebook.login.LoginManager;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by imrokraft on 14/5/15.
 */
public class TotalGraph extends ActionBarActivity {
    Dbhandler dbh;
    ArrayList<Integer> result;
    SharedPreferences share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.totalgraph);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0F62A6"));
        ab.setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.expsmall);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        dbh = new Dbhandler(getApplicationContext());
        result = dbh.totaldaywise();
        if (result == null || result.isEmpty()) {
            System.out.println("Empty");
        } else {
            int x = result.size();
            Line l = new Line();
            for (int j = 0; j < x; j++) {
                LinePoint p = new LinePoint();
                p.setX(j);
                p.setY(Float.parseFloat(String.valueOf(result.get(j))));
                l.addPoint(p);
            }
            l.setColor(Color.parseColor("#FFBB33"));
            LineGraph li = (LineGraph) findViewById(R.id.graph);
            li.addLine(l);
            li.setLineToFill(0);

            li.setOnPointClickedListener(new LineGraph.OnPointClickedListener() {
                @Override
                public void onClick(int lineIndex, int pointIndex) {
                    int s = result.get(pointIndex);
                    String s1 = String.valueOf(s);
                    Toast.makeText(getApplicationContext(), "\u20B9" + "." + s1, Toast.LENGTH_LONG).show();
                }
            });

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

}

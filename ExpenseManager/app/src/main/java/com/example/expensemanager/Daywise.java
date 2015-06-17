package com.example.expensemanager;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.echo.holographlibrary.Line;
import com.echo.holographlibrary.LineGraph;
import com.echo.holographlibrary.LinePoint;

import java.util.ArrayList;

/**
 * Created by imrokraft on 14/5/15.
 */
public class Daywise extends ActionBarActivity {
    Dbhandler dbh;
    ArrayList<Integer> total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daywise);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0F62A6"));
        ab.setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.expsmall);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        dbh = new Dbhandler(getApplicationContext());
        total=dbh.daywise();

        if (total==null||total.isEmpty()) {
            Toast.makeText(Daywise.this, "No Records Found!", Toast.LENGTH_LONG).show();
        }
        else
        {
            final int x=total.size();
            Line l = new Line();
            for (int j = 0; j < x; j++) {
                LinePoint p = new LinePoint();
                p.setX(j);
                p.setY(Float.parseFloat(String.valueOf(total.get(j))));
                l.addPoint(p);

            }
            l.setColor(Color.parseColor("#FFBB33"));
            LineGraph li = (LineGraph) findViewById(R.id.daywisegraph);
            li.addLine(l);
            li.setMinY(0);
            li.setOnPointClickedListener(new LineGraph.OnPointClickedListener() {
                @Override
                public void onClick(int lineIndex, int pointIndex) {

                       int s = total.get(pointIndex);
                        String s1=String.valueOf(s);
                        Toast.makeText(getApplicationContext(),"\u20B9"+"."+s1,Toast.LENGTH_LONG).show();

                }
            });
            li.setLineToFill(0);
        }
    }

}

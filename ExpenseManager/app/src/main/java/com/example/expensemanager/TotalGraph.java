package com.example.expensemanager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.echo.holographlibrary.Line;
import com.echo.holographlibrary.LineGraph;
import com.echo.holographlibrary.LinePoint;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by imrokraft on 14/5/15.
 */
public class TotalGraph extends ActionBarActivity {
    String mYear,mMonth,mDay,mDate;
    Dbhandler dbh;
    ArrayList<Integer> result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.totalgraph);
        final Calendar c = Calendar.getInstance();
        dbh = new Dbhandler(getApplicationContext());
        result = dbh.totaldaywise();
        if (result==null||result.isEmpty()) {
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
//        li.setRangeY(0, 700);
            li.setLineToFill(0);

            li.setOnPointClickedListener(new LineGraph.OnPointClickedListener() {
                @Override
                public void onClick(int lineIndex, int pointIndex) {
                    int s = result.get(pointIndex);
                    String s1=String.valueOf(s);
                    Toast.makeText(getApplicationContext(),"\u20B9"+"."+s1,Toast.LENGTH_LONG).show();
                }
            });

        }
    }

}

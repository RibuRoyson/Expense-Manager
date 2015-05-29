package com.example.expensemanager;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.echo.holographlibrary.PieGraph;
import com.echo.holographlibrary.PieSlice;

import java.util.ArrayList;

/**
 * Created by imrokraft on 14/5/15.
 */
public class CategoryTotal extends ActionBarActivity {
ProgressDialog pd;
    Dbhandler dbh;
    ArrayList<Integer> data;
    String[] actions = new String[] {
            "Total",
            "Current Month",
            "Today"
    };
    TextView drnks,drs,fod,othrs,personl,utili,totl;
    int a,b,c,d,e,f;
    float g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categorytotal);
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, actions);
        drnks=(TextView)findViewById(R.id.drinkstxt);
        drs=(TextView)findViewById(R.id.dresstxt);
        fod=(TextView)findViewById(R.id.foodtxt);
        othrs=(TextView)findViewById(R.id.otherstxt);
        personl=(TextView)findViewById(R.id.personaltxt);
        utili=(TextView)findViewById(R.id.utilitiestxt);
        totl=(TextView)findViewById(R.id.totaltxt);

        ActionBar.OnNavigationListener navigationListener=new ActionBar.OnNavigationListener() {
            @Override
            public boolean onNavigationItemSelected(int itemPosition, long itemId) {
                if(actions[itemPosition]=="Total")
                {
                    GrapgAsync obj=new GrapgAsync();
                    obj.execute();
                    System.out.println("hello");
                }
                else if (actions[itemPosition]=="Current Month")
                {
                    CurrentGraph1 obj2=new CurrentGraph1();
                    obj2.execute();

                }
                else if (actions[itemPosition]=="Today") {
                    TodayCategory obj3=new TodayCategory();
                    obj3.execute();
//                    Toast.makeText(getBaseContext(), "You selected : " + actions[itemPosition], Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        };
        getSupportActionBar().setListNavigationCallbacks(adapter, navigationListener);
    }

//    public void categorytotal(View v) {
//        ArrayList<Integer> data=new ArrayList<Integer>();
//        String[] colr={"#ff4830","#ffe200","#00a3ea","#ff7600","#1fff4e","#8e7b75"};
//        data = (new Dbhandler(this)).cattotal();
//        ListView listv = (ListView) findViewById(R.id.listcategory);
//        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, data);
//        listv.setAdapter(adapter);
//        dbh = new Dbhandler(getApplicationContext());
//        data = dbh.daywise();
//        int y=colr.length;
//        int x = data.size();
//        if (data == null) {
//            System.out.print("Null");
//        }
//        PieGraph pg = (PieGraph) findViewById(R.id.categorygraph);
//        pg.removeSlices();
//        PieSlice slice;
//        for (int j = 0; j < x; j++) {
//            slice= new PieSlice();
//            slice.setColor(Color.parseColor(colr[j]));
//            slice.setValue(data.get(j));
//            pg.addSlice(slice);
//        }

//    }
    public class TodayCategory extends AsyncTask<String,Void,Void>
{
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = ProgressDialog.show(CategoryTotal.this, "Expense Manager", "Loading...");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        pd.dismiss();
        drnks.setText(String.valueOf("\u20B9" + "." + a));
        drs.setText(String.valueOf("\u20B9"+"."+b));
        fod.setText(String.valueOf("\u20B9"+"."+c));
        othrs.setText(String.valueOf("\u20B9"+"."+d));
        personl.setText(String.valueOf("\u20B9"+"."+e));
        utili.setText(String.valueOf("\u20B9" + "." + f));
        totl.setText(String.valueOf("\u20B9" + " " +g));
        super.onPostExecute(aVoid);
    }

    @Override
    protected Void doInBackground(String... params) {
        dbh=new Dbhandler(CategoryTotal.this);
        a=dbh.todaycategory("Drinks");
        b=dbh.todaycategory("Dress");
        c=dbh.todaycategory("Food");
        d=dbh.todaycategory("Others");
        e=dbh.todaycategory("Personal");
        f=dbh.todaycategory("Utilities");
        g=(a+b+c+d+e+f);
        PieGraph pg = (PieGraph) findViewById(R.id.categorygraph);
        pg.removeSlices();
        PieSlice slice=new PieSlice();
        slice.setColor(Color.parseColor("#ff4830"));
        slice.setValue(a);
        pg.addSlice(slice);

        PieSlice slice1=new PieSlice();
        slice1.setColor(Color.parseColor("#ffe200"));
        slice1.setValue(b);
        pg.addSlice(slice1);

        PieSlice slice2=new PieSlice();
        slice2.setColor(Color.parseColor("#00a3ea"));
        slice2.setValue(c);
        pg.addSlice(slice2);

        PieSlice slice3=new PieSlice();
        slice3.setColor(Color.parseColor("#ff7600"));
        slice3.setValue(d);
        pg.addSlice(slice3);

        PieSlice slice4=new PieSlice();
        slice4.setColor(Color.parseColor("#1fff4e"));
        slice4.setValue(e);
        pg.addSlice(slice4);

        PieSlice slice5=new PieSlice();
        slice5.setColor(Color.parseColor("#8e7b75"));
        slice5.setValue(f);
        pg.addSlice(slice5);
        return null;
    }
}
    public class CurrentGraph1 extends AsyncTask<String,Void,Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = ProgressDialog.show(CategoryTotal.this, "Expense Manager", "Loading...");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            pd.dismiss();
            drnks.setText(String.valueOf("\u20B9" + "." + a));
            drs.setText(String.valueOf("\u20B9"+"."+b));
            fod.setText(String.valueOf("\u20B9"+"."+c));
            othrs.setText(String.valueOf("\u20B9"+"."+d));
            personl.setText(String.valueOf("\u20B9"+"."+e));
            utili.setText(String.valueOf("\u20B9" + "." + f));
            totl.setText(String.valueOf("\u20B9" + " " +g));
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(String... params) {

            dbh=new Dbhandler(CategoryTotal.this);
            a=dbh.currentcategory("Drinks");
            b=dbh.currentcategory("Dress");
            c=dbh.currentcategory("Food");
            d=dbh.currentcategory("Others");
            e=dbh.currentcategory("Personal");
            f=dbh.currentcategory("Utilities");
            g=(a+b+c+d+e+f);

//            data = (new Dbhandler(getApplicationContext())).cattotal();
//            int x = data.size();
//            dbh = new Dbhandler(getApplicationContext());
//            if (data == null) {
//                System.out.print("Null");
//            }
            PieGraph pg = (PieGraph) findViewById(R.id.categorygraph);
            pg.removeSlices();
            PieSlice slice=new PieSlice();
            slice.setColor(Color.parseColor("#ff4830"));
            slice.setValue(a);
            pg.addSlice(slice);

            PieSlice slice1=new PieSlice();
            slice1.setColor(Color.parseColor("#ffe200"));
            slice1.setValue(b);
            pg.addSlice(slice1);

            PieSlice slice2=new PieSlice();
            slice2.setColor(Color.parseColor("#00a3ea"));
            slice2.setValue(c);
            pg.addSlice(slice2);

            PieSlice slice3=new PieSlice();
            slice3.setColor(Color.parseColor("#ff7600"));
            slice3.setValue(d);
            pg.addSlice(slice3);

            PieSlice slice4=new PieSlice();
            slice4.setColor(Color.parseColor("#1fff4e"));
            slice4.setValue(e);
            pg.addSlice(slice4);

            PieSlice slice5=new PieSlice();
            slice5.setColor(Color.parseColor("#8e7b75"));
            slice5.setValue(f);
            pg.addSlice(slice5);
            return null;
        }
    }

    public class GrapgAsync extends AsyncTask<String,Void,Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = ProgressDialog.show(CategoryTotal.this, "Expense Manager", "Loading...");
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            pd.dismiss();
            drnks.setText(String.valueOf("\u20B9" + "." + a));
            drs.setText(String.valueOf("\u20B9"+"."+b));
            fod.setText(String.valueOf("\u20B9"+"."+c));
            othrs.setText(String.valueOf("\u20B9"+"."+d));
            personl.setText(String.valueOf("\u20B9"+"."+e));
            utili.setText(String.valueOf("\u20B9" + "." + f));
            totl.setText(String.valueOf("\u20B9" + " " +g));
            super.onPostExecute(aVoid);
        }
        @Override
        protected Void doInBackground(String... params) {
            dbh=new Dbhandler(getApplicationContext());
            a=dbh.categorytotal("Drinks");
            b=dbh.categorytotal("Dress");
            c=dbh.categorytotal("Food");
            d=dbh.categorytotal("Others");
            e=dbh.categorytotal("Personal");
            f=dbh.categorytotal("Utilities");
            g=(a+b+c+d+e+f);
            data = (new Dbhandler(getApplicationContext())).cattotal();
            int x = data.size();
            dbh = new Dbhandler(getApplicationContext());
            if (data == null) {
                System.out.print("Null");
            }
            PieGraph pg = (PieGraph) findViewById(R.id.categorygraph);
            pg.removeSlices();
            PieSlice slice=new PieSlice();
            slice.setColor(Color.parseColor("#ff4830"));
            slice.setValue(a);
            pg.addSlice(slice);

            PieSlice slice1=new PieSlice();
            slice1.setColor(Color.parseColor("#ffe200"));
            slice1.setValue(b);
            pg.addSlice(slice1);

            PieSlice slice2=new PieSlice();
            slice2.setColor(Color.parseColor("#00a3ea"));
            slice2.setValue(c);
            pg.addSlice(slice2);

            PieSlice slice3=new PieSlice();
            slice3.setColor(Color.parseColor("#ff7600"));
            slice3.setValue(d);
            pg.addSlice(slice3);

            PieSlice slice4=new PieSlice();
            slice4.setColor(Color.parseColor("#1fff4e"));
            slice4.setValue(e);
            pg.addSlice(slice4);

            PieSlice slice5=new PieSlice();
            slice5.setColor(Color.parseColor("#8e7b75"));
            slice5.setValue(f);
            pg.addSlice(slice5);
//            for (int j = 0; j < x; j++) {
//                slice= new PieSlice();
//                slice.setColor(Color.parseColor(colr[j]));
//                slice.setValue(data.get(j));
//                pg.addSlice(slice);
//            }
            return null;
        }
    }
}

package com.example.expensemanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by imrokraft on 29/4/15.
 */
public class EditData extends ActionBarActivity {
    EditText exp, cat, desc, tim, dat;
    String id,  cat1, desc1, tim1, dat1,exp1;
    details d;
    int a, b, c;
    Dbhandler dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new1);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0F62A6"));
        ab.setBackgroundDrawable(colorDrawable);
        exp = (EditText) findViewById(R.id.expet);
        cat = (EditText) findViewById(R.id.catet);
        desc = (EditText) findViewById(R.id.deset);
        tim = (EditText) findViewById(R.id.timeet);
        dat = (EditText) findViewById(R.id.dateet);

        Intent i = getIntent();
        id = i.getExtras().getString("id");
        exp1 = i.getExtras().getString("expense");
        cat1 = i.getExtras().getString("cat");
        desc1 = i.getExtras().getString("description");
        tim1 = i.getExtras().getString("time");
        dat1 = i.getExtras().getString("date");
        exp.setText(exp1);
        cat.setText(cat1);
        desc.setText(desc1);
        tim.setText(tim1);
        dat.setText(dat1);

        d = new details(id, exp1, cat1, desc1, tim1, dat1, a, b, c);
    }

    public void updateclick(View v) {


        d.setExpense(exp.getText().toString());
        d.setCat(cat.getText().toString());
        d.setDescription(desc.getText().toString());
        d.setTime1(tim.getText().toString());
        d.setDate1(dat.getText().toString());
        dbh = new Dbhandler(EditData.this);
        dbh.updateDetails(d, id + "");
        exp.setText("");
        cat.setText("");
        desc.setText("");
        tim.setText("");
        dat.setText("");
        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();

    }

    public void deleteclick(View v) {

        new AlertDialog.Builder(this)
                .setTitle("Expense Manager")
                .setMessage("Are you sure?")
                .setPositiveButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Yes",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbh = new Dbhandler(EditData.this);
                        String nm = String.valueOf(Integer.parseInt(String.valueOf(id)));
                        dbh.deletedetails(nm);
                        Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                        exp.setText("");
                        cat.setText("");
                        desc.setText("");
                        tim.setText("");
                        dat.setText("");
                    }
                })
                .show();



    }

    public void viewdb(View v) {
        Intent in = new Intent(getApplicationContext(), Viewdb.class);
        startActivity(in);
        EditData.this.finish();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}

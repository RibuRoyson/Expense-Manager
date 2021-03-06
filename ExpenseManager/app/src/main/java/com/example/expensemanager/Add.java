package com.example.expensemanager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Calendar;

public class Add extends ActionBarActivity implements android.view.View.OnClickListener {
    final ParseObject expensemanager = new ParseObject("expense");
    Spinner sp;
    Button tim, dat, save, show;
    EditText expen, descpt;
    String expense, cat, des, date1, time1, uname, email, emailfb;
    Dbhandler dbh;
    int dy, mn, yr;
    SharedPreferences share;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnew);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0F62A6"));
        ab.setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.expsmall);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        share = getSharedPreferences("UsernamePrefs", MODE_PRIVATE);
        uname = share.getString("username", null);
        email = share.getString("email", null);
        sp = (Spinner) findViewById(R.id.spinner1);
        tim = (Button) findViewById(R.id.timebtn);
        dat = (Button) findViewById(R.id.datebtn);
        save = (Button) findViewById(R.id.save);
        show = (Button) findViewById(R.id.show);
        expen = (EditText) findViewById(R.id.exp);
        descpt = (EditText) findViewById(R.id.desc);
        tim.setOnClickListener(this);
        dat.setOnClickListener(this);
        save.setOnClickListener(this);
        show.setOnClickListener(this);
        String[] items = {"Drinks", "Dress", "Food", "Others", "Personal", "Utilities"};
        CustomArrayAdapter<String> myadapter = new CustomArrayAdapter<String>(this, items);
        sp.setAdapter(myadapter);
        sp.setPrompt("Select an Item");
        sp.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                cat = sp.getItemAtPosition(arg2).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v == dat) {

            // Process to get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            // Launch Date Picker Dialog
            DatePickerDialog dpd = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            // Display Selected date in textbox
                            dat.setText(dayOfMonth + "-"
                                    + (monthOfYear + 1) + "-" + year);
                            dy = dayOfMonth;
                            mn = monthOfYear + 1;
                            yr = year;
                        }
                    }, mYear, mMonth, mDay);
            dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
            dpd.show();
        }
        if (v == tim) {

            // Process to get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog tpd = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            // Display Selected time in textbox
                            tim.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);

            tpd.show();

        }
        if (v == save) {

            expense = expen.getText().toString();
            des = descpt.getText().toString();
            time1 = tim.getText().toString();
            date1 = dat.getText().toString();
            if (expense.equals("")) {
                Toast.makeText(getApplicationContext(), "Enter a value as expense", Toast.LENGTH_SHORT).show();
            } else if (des.equals("")) {
                Toast.makeText(getApplicationContext(), "Enter a description", Toast.LENGTH_SHORT).show();
            } else if (time1.matches(".*\\d+.*")==false)
            {
                Toast.makeText(getApplicationContext(), "Enter a time", Toast.LENGTH_SHORT).show();
            }else if (date1.matches(".*\\d+.*")==false)
            {
                Toast.makeText(getApplicationContext(), "Enter a date", Toast.LENGTH_SHORT).show();

            }else
            {
                int id = -1;
                details d = new details(id + "", expense, cat, des, time1, date1, dy, mn, yr);
                d.setExpense(expense);
                d.setCat(cat);
                d.setDescription(des);
                d.setTime1(time1);
                d.setDate1(date1);
                dbh = new Dbhandler(getApplicationContext());
                long g = dbh.insertdata(d);
                if (g > 0) {
                    Toast.makeText(Add.this, "Saved", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Add.this, "Failed", Toast.LENGTH_LONG).show();
                }


                String b1 = dbh.resultexp();
                String c1 = dbh.resultcat();
                String d1 = dbh.resultdesc();
                String e1 = dbh.resulttime();
                String f1 = dbh.resultdate();
                share = getSharedPreferences("UsernamePrefs", MODE_PRIVATE);
                emailfb = share.getString("email", null);
                uname = share.getString("username", null);
                email = share.getString("email", null);
                int abc = dbh.resultid1();

                expensemanager.put("ID", abc);

                expensemanager.put("Expense", b1);
                expensemanager.put("Category", c1);
                expensemanager.put("Description", d1);
                expensemanager.put("Time", e1);
                expensemanager.put("Date", f1);
                expensemanager.put("UsernName", uname);
                int ab = share.getInt("loginfb", 0);
                if (ab == 1) {
                    expensemanager.put("Email", emailfb);
                } else {
                    expensemanager.put("Email", email);
                }
                if (isNetworkAvailable(getApplicationContext()) == true) {
                    expensemanager.saveInBackground();
                    System.out.println("Stored In Cloud.............");
                } else {
                    expensemanager.pinInBackground();
                    System.out.println("Stored In Local.............");
                }
                expensemanager.saveEventually(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(getApplicationContext(), "Done!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Not Done!!\n" + e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                });
                Intent inc = new Intent(Add.this, Add.class);
                startActivity(inc);

                expen.setText("");
                descpt.setText("");
            }

        }
        if (v == show) {
            Intent inc = new Intent(Add.this, Viewdb.class);
            startActivity(inc);
            Add.this.finish();
        }
    }

    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    Log.w("INTERNET:", String.valueOf(i));
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        Log.w("INTERNET:", "connected!");
                        return true;
                    }
                }
            }
        }
        return false;
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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent inab = new Intent(getApplicationContext(), Activitymain.class);
        startActivity(inab);
    }
}

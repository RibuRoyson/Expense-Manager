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
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.parse.ParseUser;

import java.util.ArrayList;

public class Viewdb extends ActionBarActivity {
    ArrayList<details> userslist;
    ListView lv;
    UserAdapter myuser;
    SharedPreferences share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0F62A6"));
        ab.setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.expsmall);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        lv = (ListView) findViewById(R.id.listView1);
        Dbhandler dbh = new Dbhandler(getApplicationContext());
        userslist = dbh.resultdata();
        myuser = new UserAdapter(getApplicationContext(), userslist);
        if (myuser.isEmpty() == true) {
            Toast.makeText(Viewdb.this, "Sorry No Entry!!", Toast.LENGTH_LONG).show();
        } else {
            lv.setAdapter(myuser);
        }
        dbh.close();
    }

    @Override
    protected void onResume() {
        super.onResume();

        myuser.notifyDataSetChanged();
        myuser.notifyDataSetInvalidated();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.editdel, menu);
        Toast.makeText(getApplicationContext(), "Menu Created", Toast.LENGTH_SHORT).show();
        System.out.println("\n\n----------------------\n" + "menu created" + "\n\n--------------------------------");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        System.out.println("\n\n----------------------\n" + "menu selected" + "\n\n--------------------------------");

        switch (item.getItemId()) {

            case R.id.edit1:
                Toast.makeText(getApplicationContext(), "Menu edited", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), EditData.class);
                i.putExtra("id", userslist.get(lv.getSelectedItemPosition()).getId());
                i.putExtra("expense", userslist.get(lv.getSelectedItemPosition()).getExpense());
                i.putExtra("cat", userslist.get(lv.getSelectedItemPosition()).getCat());
                i.putExtra("description", userslist.get(lv.getSelectedItemPosition()).getDescription());
                i.putExtra("time", userslist.get(lv.getSelectedItemPosition()).getTime1());
                i.putExtra("date", userslist.get(lv.getSelectedItemPosition()).getDate1());
                startActivity(i);

                break;
            case R.id.dele2:
                Toast.makeText(getApplicationContext(), "Menu deleted", Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
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
        finish();
    }
}



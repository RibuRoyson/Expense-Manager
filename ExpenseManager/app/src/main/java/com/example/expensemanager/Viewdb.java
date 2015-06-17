package com.example.expensemanager;

import android.content.Intent;
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

import java.util.ArrayList;

public class Viewdb extends ActionBarActivity {
    ArrayList<details> userslist;
    ListView lv;
    UserAdapter myuser;


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
        userslist=dbh.resultdata();
        myuser = new UserAdapter(getApplicationContext(), userslist);
        if (myuser.isEmpty() == true) {
            Toast.makeText(Viewdb.this, "Sorry No Entry!!", Toast.LENGTH_LONG).show();
        }else {
            lv.setAdapter(myuser);
        }
//        registerForContextMenu(lv);
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
//      super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.editdel,menu);
        Toast.makeText(getApplicationContext(),"Menu Created",Toast.LENGTH_SHORT).show();
        System.out.println("\n\n----------------------\n" + "menu created" + "\n\n--------------------------------");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        System.out.println("\n\n----------------------\n" + "menu selected" + "\n\n--------------------------------");

        switch(item.getItemId())
        {

            case R.id.edit1:
                Toast.makeText(getApplicationContext(),"Menu edited",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),EditData.class);
                i.putExtra("id",userslist.get(lv.getSelectedItemPosition()).getId());
                i.putExtra("expense", userslist.get(lv.getSelectedItemPosition()).getExpense());
                i.putExtra("cat", userslist.get(lv.getSelectedItemPosition()).getCat());
                i.putExtra("description", userslist.get(lv.getSelectedItemPosition()).getDescription());
                i.putExtra("time",userslist.get(lv.getSelectedItemPosition()).getTime1());
                i.putExtra("date",userslist.get(lv.getSelectedItemPosition()).getDate1());
                startActivity(i);

                break;
            case R.id.dele2:
//                myuser.notifyDataSetChanged();
//                myuser.notifyDataSetInvalidated();
                Toast.makeText(getApplicationContext(),"Menu deleted",Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       finish();
    }
}



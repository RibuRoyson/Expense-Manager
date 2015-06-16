package com.example.expensemanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.facebook.FacebookSdk;
import com.parse.ParseUser;

public class Activitymain extends ActionBarActivity {

    SharedPreferences share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activitymain);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0F62A6"));
        ab.setBackgroundDrawable(colorDrawable);
//        ab.setIcon(R.drawable.expense);

    }

    public void adddatas(View v) {
        Intent aa = new Intent(getApplicationContext(), Add.class);
        startActivity(aa);

    }

    public void editdatas(View v) {
        Intent ac = new Intent(getApplicationContext(),Showresult.class);
        startActivity(ac);
    }

    public void viewdatas(View v) {
        Intent in = new Intent(getApplicationContext(), Viewdb.class);
        startActivity(in);
    }


    public void showmonth(View v)
    {
        Intent incc = new Intent(getApplicationContext(), Monthwise.class);
        startActivity(incc);
    }
    public void showcat(View v)
    {
        Intent inc = new Intent(getApplicationContext(), Bycat.class);
        startActivity(inc);
    }
    public void graphs(View v)
    {
        Intent incn = new Intent(getApplicationContext(), GraphMenu.class);
        startActivity(incn);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.cloud:
                Intent inc1=new Intent(getApplication(),CloudListView.class);
                startActivity(inc1);
                break;
            case R.id.action_logout:
                share=getSharedPreferences("UsernamePrefs", MODE_PRIVATE);
                int s=share.getInt("loginvalue", 0);
                if (s==0)
                {
                    ParseUser.logOut();
                    ParseUser newUser = ParseUser.getCurrentUser();
                    loadloginView();
                }
                else if (s==1){
                    Intent a=new Intent(getApplicationContext(),FaceBookLogin.class);
                    startActivity(a);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void loadloginView()
    {
        Intent intent=new Intent(this,ActivityLogin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}


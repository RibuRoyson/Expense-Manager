package com.example.expensemanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.parse.ParseUser;

/**
 * Created by imrokraft on 16/6/15.
 */
public class Sandwitch extends ActionBarActivity {
    ParseUser newUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newUser = ParseUser.getCurrentUser();
        if (newUser!=null)
        {
            Intent intentabc=new Intent(this,Activitymain.class);
            SharedPreferences sharenew = getSharedPreferences("UsernamePrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editornew = sharenew.edit();
            editornew.putInt("loginvalue",0);
            editornew.commit();
            startActivity(intentabc);
        }
        else {
            Intent intentabcd=new Intent(this,ActivityLogin.class);
            Sandwitch.this.finish();
            startActivity(intentabcd);
        }
    }
}

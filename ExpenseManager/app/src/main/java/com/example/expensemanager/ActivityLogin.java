package com.example.expensemanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by imrokraft on 16/6/15.
 */
public class ActivityLogin extends ActionBarActivity {
    protected EditText usernameEditText;
    protected EditText passwordEditText;
    protected Button loginButton;

    protected TextView signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.signin);
        signUpButton = (Button)findViewById(R.id.signUpButton);
        usernameEditText = (EditText)findViewById(R.id.usernameField);
        passwordEditText = (EditText)findViewById(R.id.passwordField);
        loginButton = (Button)findViewById(R.id.loginButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLogin.this, ActivitySignup.class);
                startActivity(intent);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                username = username.trim();
                password = password.trim();

                if (username.isEmpty() || password.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ActivityLogin.this);
                    builder.setMessage(R.string.login_error_message)
                            .setTitle(R.string.login_error_title)
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {

                    //                    setProgressBarIndeterminateVisibility(true);
                    final String finalUsername = username;
                    ParseUser.logInInBackground(username, password, new LogInCallback() {
                        @Override
                        public void done(ParseUser newUser, ParseException e) {
                            if (e == null) {
                                Toast.makeText(getApplicationContext(), "Logging in...", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ActivityLogin.this, Sandwitch.class);
                                SharedPreferences sharenew = getSharedPreferences("UsernamePrefs", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editornew = sharenew.edit();
                                editornew.putString("username", finalUsername);
                                editornew.putInt("loginfb", 0);
                                editornew.commit();
//                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Something went Wrong...", Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityLogin.this);
                                builder.setMessage(e.getMessage())
                                        .setTitle(R.string.login_error_title)
                                        .setPositiveButton(android.R.string.ok, null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }
                    });
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.facebook,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.facebookitem:
                Intent inccc = new Intent(getApplication(),FaceBookLogin.class);
                startActivity(inccc);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    }




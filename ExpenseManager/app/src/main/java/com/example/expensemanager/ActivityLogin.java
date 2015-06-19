package com.example.expensemanager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by imrokraft on 16/6/15.
 */
public class ActivityLogin extends ActionBarActivity {
    protected EditText usernameEditText;
    protected EditText passwordEditText;
    protected Button loginButton;
    protected TextView signUpButton;
    ProgressDialog pd;
    private CallbackManager callbackmanager;
    private LoginButton login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.signin);
        callbackmanager = CallbackManager.Factory.create();
        android.support.v7.app.ActionBar abc = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0F62A6"));
        abc.setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.expsmall);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        signUpButton = (Button) findViewById(R.id.signUpButton);
        usernameEditText = (EditText) findViewById(R.id.usernameField);
        passwordEditText = (EditText) findViewById(R.id.passwordField);
        loginButton = (Button) findViewById(R.id.loginButton);

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
                    final String finalPassword = password;
                    ParseUser.logInInBackground(username, password, new LogInCallback() {
                        @Override
                        public void done(ParseUser newUser, ParseException e) {
                            if (e == null) {
                                Toast.makeText(getApplicationContext(), "Log in as :" + finalUsername, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ActivityLogin.this, Sandwitch.class);
                                SharedPreferences sharenew = getSharedPreferences("UsernamePrefs", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editornew = sharenew.edit();
                                editornew.putString("username", finalUsername);
                                editornew.putString("email", newUser.getEmail());
                                editornew.putInt("loginfb", 0);
                                editornew.commit();
//
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
        login = (LoginButton) findViewById(R.id.login_button1);
        login.setReadPermissions(Arrays.asList("public_profile", "email"));

        login.registerCallback(callbackmanager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
                        if (graphResponse.getError() != null) {
                            System.out.println(graphResponse.getError().toString());
//
                        } else {
                            String emailid = jsonObject.optString("email");
                            String id = jsonObject.optString("id");
                            String fname = jsonObject.optString("first_name");
                            String lname = jsonObject.optString("last_name");
                            final StringBuffer sb = new StringBuffer();
                            sb.append(fname).append(lname);
                            SharedPreferences share = getSharedPreferences("UsernamePrefs", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = share.edit();
                            editor.putString("username", String.valueOf(sb));
                            editor.putString("email", emailid);
                            editor.commit();
                            ParseUser newUser = new ParseUser();
                            newUser.setUsername(String.valueOf(sb));
                            newUser.setPassword(emailid);
                            newUser.setEmail(emailid);
                            newUser.signUpInBackground(new SignUpCallback() {
                                @Override
                                public void done(ParseException e) {
                                    pd = ProgressDialog.show(ActivityLogin.this, "Expense manager", "Loading..");
                                    pd.show();
                                    Intent intent = new Intent(ActivityLogin.this, Activitymain.class);
                                    Toast.makeText(getApplicationContext(), "Logging as :" + sb, Toast.LENGTH_SHORT).show();
                                    SharedPreferences sharefb = getSharedPreferences("UsernamePrefs", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editorfb = sharefb.edit();
                                    editorfb.putInt("loginvalue", 1);
                                    editorfb.putInt("loginfb", 1);
                                    editorfb.commit();
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                }).executeAsync();


            }


            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Login Attempt Cancel!!", Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onError(FacebookException e) {
                Toast.makeText(getApplicationContext(), "Login Attempt Failed!!", Toast.LENGTH_SHORT).show();
            }


        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackmanager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityLogin.this.finish();

    }

}




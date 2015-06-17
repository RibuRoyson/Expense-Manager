package com.example.expensemanager;

import android.app.AlertDialog;
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
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by imrokraft on 16/6/15.
 */
public class ActivityLogin extends ActionBarActivity {
    protected EditText usernameEditText;
    protected EditText passwordEditText;
    protected Button loginButton;
    private CallbackManager callbackmanager;
    protected TextView signUpButton;
    private LoginButton login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.signin);
        callbackmanager= CallbackManager.Factory.create();
        android.support.v7.app.ActionBar abc = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0F62A6"));
        abc.setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.expsmall);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
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
        login=(LoginButton)findViewById(R.id.login_button1);
        login.setReadPermissions(Arrays.asList("public_profile", "email"));

        login.registerCallback(callbackmanager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
                        if (graphResponse.getError() != null) {
                            System.out.println(graphResponse.getError().toString());
//                            Intent in=new Intent(getApplicationContext(),ActivityLogin.class);
//                            startActivity(in);
                        } else {
                            String emailid = jsonObject.optString("email");
                            String id = jsonObject.optString("id");
                            String fname = jsonObject.optString("first_name");
                            String lname = jsonObject.optString("last_name");
                            StringBuffer sb = new StringBuffer();
                            sb.append(fname).append(lname);
                            System.out.println(sb);
                            System.out.println("Email:" + emailid);
                            System.out.println("ID:" + id);
                            SharedPreferences share = getSharedPreferences("UsernamePrefs", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = share.edit();
                            editor.putString("username", String.valueOf(sb));
                            editor.putString("emailfb", emailid);
                            editor.commit();
                            ParseUser newUser = new ParseUser();
                            newUser.setUsername(String.valueOf(sb));
                            newUser.setPassword(id);
                            newUser.setEmail(emailid);
                            newUser.signUpInBackground(new SignUpCallback() {
                                @Override
                                public void done(ParseException e) {
                                    Intent intent = new Intent(ActivityLogin.this, Activitymain.class);
                                    SharedPreferences sharefb = getSharedPreferences("UsernamePrefs", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editorfb = sharefb.edit();
                                    editorfb.putInt("loginvalue", 1);
                                    editorfb.putInt("loginfb", 1);
                                    editorfb.commit();
                                    Toast.makeText(getApplicationContext(), "Signup Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                }).executeAsync();

////                Toast.makeText(getApplicationContext(),"User ID:"+loginResult.getAccessToken().getUserId(),Toast.LENGTH_LONG).show();
//                Toast.makeText(getApplicationContext(), "Auth Token:" + loginResult.getAccessToken().getToken(), Toast.LENGTH_LONG).show();
////                    new async().execute("");
                System.out.println("UserId:" + loginResult.getAccessToken().getUserId());
            }

//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
//                    @Override
//                    public void onCompleted(JSONObject object, GraphResponse response) {
//                       if (response.getError()!=null)
//                       {
//                           System.out.println(response.getError().toString());
//                       }else {
//                           Toast.makeText(getApplicationContext(),"Signup Successful",Toast.LENGTH_SHORT).show();
//
//                       }
//                    }
//                }).executeAsync();
//                Toast.makeText(getApplicationContext(), "Auth Token:" + loginResult.getAccessToken().getToken(), Toast.LENGTH_LONG).show();
////                    new async().execute("");
//                System.out.println("UserId:" + loginResult.getAccessToken().getUserId());
//            }

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
    }




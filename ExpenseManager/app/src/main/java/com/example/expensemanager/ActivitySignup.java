package com.example.expensemanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by imrokraft on 16/6/15.
 */
public class ActivitySignup extends ActionBarActivity {
    protected EditText usernameEditText;
    protected EditText passwordEditText;
    protected EditText emailEditText;
    protected Button signUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        setContentView(R.layout.signup);
        android.support.v7.app.ActionBar abc = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0F62A6"));
        abc.setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.expsmall);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        usernameEditText = (EditText)findViewById(R.id.usernameField);
        passwordEditText = (EditText)findViewById(R.id.passwordField);
        emailEditText = (EditText)findViewById(R.id.emailField);
        signUpButton = (Button)findViewById(R.id.signupButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String email = emailEditText.getText().toString();

                username = username.trim();
                password = password.trim();
                email = email.trim();
                if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ActivitySignup.this);
                    builder.setMessage(R.string.signup_error_message)
                            .setTitle(R.string.signup_error_title)
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
//                    setProgressBarIndeterminateVisibility(true);
                    SharedPreferences share = getSharedPreferences("UsernamePrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = share.edit();
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.putString("email", email);
                    editor.putInt("loginfb", 0);
                    editor.commit();
//                        Intent inc = new Intent(getApplicationContext(), Sandwitch.class);
//                        inc.putExtra("username", username);
//                        inc.putExtra("password", password);
//                        startActivity(inc);

                    ParseUser newUser = new ParseUser();
                    newUser.setUsername(username);
                    newUser.setPassword(password);
                    newUser.setEmail(email);
                    newUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
//                            setProgressBarIndeterminateVisibility(false);
                            if (e == null) {
                                Intent intent = new Intent(ActivitySignup.this, ActivityLogin.class);
                                Toast.makeText(getApplicationContext(), "Signup Successful", Toast.LENGTH_SHORT).show();
//                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Signup Unsuccessful", Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder builder = new AlertDialog.Builder(ActivitySignup.this);
                                builder.setMessage(e.getMessage())
                                        .setTitle(R.string.signup_error_title)
                                        .setPositiveButton(android.R.string.ok, null);
                                AlertDialog dialog = builder.create();
//                                if(!((Activity) context).isFinishing())
//                                {
                                //show dialog
                                dialog.show();
//                                }

                            }
                        }
                    });
                }
            }
        });
    }
}

package com.example.expensemanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by imrokraft on 16/6/15.
 */
public class FaceBookLogin extends ActionBarActivity {
    private static String APP_ID ="494541540710837";
    private LoginButton login;
    private CallbackManager callbackmanager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.facebook);
        callbackmanager=CallbackManager.Factory.create();
        login=(LoginButton)findViewById(R.id.login_button);
        login.setReadPermissions(Arrays.asList("public_profile", "email"));
        login.registerCallback(callbackmanager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
                        if (graphResponse.getError() != null) {
                            System.out.println(graphResponse.getError().toString());
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
                                    Intent intent = new Intent(FaceBookLogin.this, Activitymain.class);
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
//                System.out.println("UserId:" + loginResult.getAccessToken().getUserId());
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

package com.example.expensemanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by imrokraft on 16/6/15.
 */
public class CloudListView extends ActionBarActivity {
    String email,emailfb;
    SharedPreferences share;
    UserAdapter2 userlist2;
    Dbhandler dbh;
    final Context context = this;
    final ArrayList<String> notesid=new ArrayList<String>();
    final ArrayList<String> object=new ArrayList<String>();
    final ArrayList<String> notesexp=new ArrayList<String>();
    final ArrayList<String> notescat=new ArrayList<String>();
    final ArrayList<String> notesdes=new ArrayList<String>();
    final ArrayList<String> notesdat=new ArrayList<String>();
    final ArrayList<String> notestim=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cloudlistview);
        android.support.v7.app.ActionBar abc = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0F62A6"));
        abc.setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.expsmall);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        final ListView lv=(ListView)findViewById(R.id.list);
        dbh =new Dbhandler(getApplicationContext());
        final ArrayList<String> IDDB=dbh.resultiddb();
        share=getSharedPreferences("UsernamePrefs", Context.MODE_PRIVATE);
        int ab=share.getInt("loginfb", 0);
        if (ab==1)
        {
            emailfb=share.getString("email",null);
            ParseQuery<ParseObject> query1 = ParseQuery.getQuery("expense");
            query1.whereEqualTo("Email",emailfb);
            query1.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    if (e==null)
                    {
                        for (ParseObject post:list)
                        {
                            notesid.add(String.valueOf(post.getInt("ID")));
                            notesexp.add(post.getString("Expense"));
                            notescat.add(post.getString("Category"));
                            notesdes.add(post.getString("Description"));
                            notestim.add(post.getString("Time"));
                            notesdat.add(post.getString("Date"));
                            System.out.println(notesid);
                            System.out.println(notesexp);
                            System.out.println(notescat);
                            System.out.println(notesdes);
                            System.out.println(notestim);
                            System.out.println(notesdat);
                            object.add(post.getObjectId());
                        }
                        userlist2=new UserAdapter2(getApplicationContext(),notesid,notesexp,notescat,notesdes,notestim,notesdat);
                        if (isNetworkAvailable(context)) {
                            lv.setAdapter(userlist2);
                        }else
                        {
                            Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();
                        }
                        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                            @Override
                            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                              String sd=notesid.get(position).toString();
                                System.out.println(IDDB);
                                if (IDDB.contains(sd))
                                {
                                    Toast.makeText(getApplication(), "Already In DB", Toast.LENGTH_LONG).show();

                                }
                                else {
                                    int idint=Integer.parseInt(notesid.get(position).toString());
                                    String ids=notesid.get(position).toString();
                                    String exps=notesexp.get(position).toString();
                                    String cats=notescat.get(position).toString();
                                    String dess=notesdes.get(position).toString();
                                    String tims=notestim.get(position).toString();
                                    String dats=notesdat.get(position).toString();
                                    details d1=new details(ids,exps,cats,dess,tims,dats,0,0,0);
                                    dbh.insertdata(d1);
                                    Toast.makeText(getApplication(), "Values Inserted to DB", Toast.LENGTH_LONG).show();
                                }
                                return false;
                            }
                        });
                    }
                    else {
                        System.out.println(e.getMessage());
                    }
                }
            });
        }
        else {
            email = share.getString("email", null);
            ParseQuery<ParseObject> query1 = ParseQuery.getQuery("expense");
            query1.whereEqualTo("Email", email);
            query1.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    if (e==null)
                    {
                        for (ParseObject post:list)
                        {
                            notesid.add(String.valueOf(post.getInt("ID")));
                            notesexp.add(post.getString("Expense"));
                            notescat.add(post.getString("Category"));
                            notesdes.add(post.getString("Description"));
                            notestim.add(post.getString("Time"));
                            notesdat.add(post.getString("Date"));
                            System.out.println(notesid);
                            System.out.println(notesexp);
                            System.out.println(notescat);
                            System.out.println(notesdes);
                            System.out.println(notestim);
                            System.out.println(notesdat);
                            object.add(post.getObjectId());
                        }
                        userlist2=new UserAdapter2(getApplicationContext(),notesid,notesexp,notescat,notesdes,notestim,notesdat);
                        if (isNetworkAvailable(context)) {
                            lv.setAdapter(userlist2);
                        }else
                        {
                            Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();
                        }
                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                String sd=notesid.get(position).toString();
                                System.out.println(IDDB);
                                if (IDDB.contains(sd))
                                {
                                    Toast.makeText(getApplication(), "Already In Database", Toast.LENGTH_LONG).show();

                                }else {

                                    Toast.makeText(getApplication(), "Can Import to your Database", Toast.LENGTH_LONG).show();
                                }

                            }
                        });
                        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                            @Override
                            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                                String sd=notesid.get(position).toString();
                                System.out.println(IDDB);
                                if (IDDB.contains(sd))
                                {
                                    Toast.makeText(getApplication(), "No Need for Adding", Toast.LENGTH_LONG).show();

                                }
                                else {

                                    String ids=notesid.get(position).toString();
                                    String exps=notesexp.get(position).toString();
                                    String cats=notescat.get(position).toString();
                                    String dess=notesdes.get(position).toString();
                                    String tims=notestim.get(position).toString();
                                    String dats=notesdat.get(position).toString();
                                    details d1=new details(ids,exps,cats,dess,tims,dats,0,0,0);
                                    dbh.insertdata(d1);
                                    Toast.makeText(getApplication(), "Values Inserted to DB", Toast.LENGTH_LONG).show();
                                }
                                return false;
                            }
                        });
                    }
                    else {
                        System.out.println(e.getMessage());
                    }
                }
            });

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cloud, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.cloud:
                alertDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void alertDialog()
    {
        LayoutInflater li=LayoutInflater.from(context);
        View promptsView=li.inflate(R.layout.alertdialog, null);
        AlertDialog.Builder alertdialog=new AlertDialog.Builder(context);
        alertdialog.setView(promptsView);
        final EditText usernameet=(EditText)promptsView.findViewById(R.id.user);
        final EditText emailet=(EditText)promptsView.findViewById(R.id.email);
        alertdialog.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name=usernameet.getText().toString();
                        String eml=emailet.getText().toString();
                        quer(name,eml);
                        Toast.makeText(getApplicationContext(),name,Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert=alertdialog.create();
        alert.show();
    }
    public void quer(String ab,String bc)
    {
        final ListView lv=(ListView)findViewById(R.id.list);
        ParseQuery<ParseObject> var1 = ParseQuery.getQuery("expense");
        var1.whereEqualTo("UsernName", ab);
        ParseQuery<ParseObject> var2=ParseQuery.getQuery("expense");
        var2.whereEqualTo("Email", bc);
       List<ParseQuery<ParseObject>> queries=new ArrayList<ParseQuery<ParseObject>>();
        queries.add(var1);
        queries.add(var2);
        final ArrayList<String> IDDB=dbh.resultiddb();
        ParseQuery mainQuery=ParseQuery.or(queries);
        mainQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    for (ParseObject post : list) {
                        notesid.add(String.valueOf(post.getInt("ID")));
                        notesexp.add(post.getString("Expense"));
                        notescat.add(post.getString("Category"));
                        notesdes.add(post.getString("Description"));
                        notestim.add(post.getString("Time"));
                        notesdat.add(post.getString("Date"));
                        System.out.println(notesid);
                        System.out.println(notesexp);
                        System.out.println(notescat);
                        System.out.println(notesdes);
                        System.out.println(notestim);
                        System.out.println(notesdat);
                        object.add(post.getObjectId());
                    }
                    userlist2 = new UserAdapter2(getApplicationContext(), notesid, notesexp, notescat, notesdes, notestim, notesdat);
                    if (isNetworkAvailable(context)) {
                        lv.setAdapter(userlist2);
                    }else
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();
                    }
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String sd = notesid.get(position).toString();
                            System.out.println(IDDB);
                            if (IDDB.contains(sd)) {
                                Toast.makeText(getApplication(), "Already In Database", Toast.LENGTH_LONG).show();

                            } else {

                                Toast.makeText(getApplication(), "Can Import to your Database", Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                    lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                            String sd = notesid.get(position).toString();
                            System.out.println(IDDB);
                            if (IDDB.contains(sd)) {
                                Toast.makeText(getApplication(), "No Need for Adding", Toast.LENGTH_LONG).show();

                            } else {

                                String ids = notesid.get(position).toString();
                                String exps = notesexp.get(position).toString();
                                String cats = notescat.get(position).toString();
                                String dess = notesdes.get(position).toString();
                                String tims = notestim.get(position).toString();
                                String dats = notesdat.get(position).toString();
                                details d1 = new details(ids, exps, cats, dess, tims, dats, 0, 0, 0);
                                dbh.insertdata(d1);
                                Toast.makeText(getApplication(), "Values Inserted to DB", Toast.LENGTH_LONG).show();
                            }
                            return false;
                        }
                    });


                }
            }
        });
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
}



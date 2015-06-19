package com.example.expensemanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by imrokraft on 14/4/15.
 */
public class UserAdapter extends ArrayAdapter<details> {
     ArrayList<String> notes = new ArrayList<String>();
     ArrayList<String> object = new ArrayList<String>();
     ArrayList<String> notes2 = new ArrayList<String>();
    ArrayList<details> userslist;
    Context myContext;
    String s1, email;
    SharedPreferences share;


    public UserAdapter(Context context, ArrayList<details> userlist) {
        super(context, R.layout.list_item, userlist);
        this.userslist = userlist;
        this.myContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(final int position, View convertView, final ViewGroup parent) {
        share = myContext.getSharedPreferences("UsernamePrefs", Context.MODE_PRIVATE);
        email = share.getString("email", null);
        ParseQuery<ParseObject> query1 = ParseQuery.getQuery("expense");
        query1.whereEqualTo("Email", email);
        query1.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    for (ParseObject post : list) {
                        notes.add(post.getString("Expense"));
                        object.add(post.getObjectId());
                        System.out.println(object);
                    }
                    for (int i = 0; i < notes.size(); i++) {
                        String ss = notes.get(i);
                        notes2.add(ss);
                    }
                }
            }
        });

        if (convertView == null) {
            LayoutInflater mlayoutInflater = LayoutInflater.from(myContext);
            convertView = mlayoutInflater.inflate(R.layout.list_item, parent, false);
        }
        TextView id = (TextView) convertView.findViewById(R.id.textid1);
        TextView expen = (TextView) convertView.findViewById(R.id.textexp2);
        TextView cat = (TextView) convertView.findViewById(R.id.textcat3);
        TextView desc = (TextView) convertView.findViewById(R.id.textdes4);
        TextView tim = (TextView) convertView.findViewById(R.id.texttime5);
        TextView dat = (TextView) convertView.findViewById(R.id.textdate6);
        expen.setText(userslist.get(position).getExpense());
        cat.setText(userslist.get(position).getCat());
        desc.setText(userslist.get(position).getDescription());
        tim.setText(userslist.get(position).getTime1());
        dat.setText(userslist.get(position).getDate1());
        id.setText(userslist.get(position).getId());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(getContext(), EditData.class);

                i.putExtra("id", userslist.get(position).getId());
                i.putExtra("expense", userslist.get(position).getExpense());
                i.putExtra("cat", userslist.get(position).getCat());
                i.putExtra("description", userslist.get(position).getDescription());
                i.putExtra("time", userslist.get(position).getTime1());
                i.putExtra("date", userslist.get(position).getDate1());
                String dd = object.get(position).toString();
                String ss = dd.toString().trim();
                if (ss == null) {
                    System.out.println("null");
                } else {
                    i.putExtra("objectid", ss);
                }
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                myContext.startActivity(i);


            }
        });
        return convertView;

    }


}


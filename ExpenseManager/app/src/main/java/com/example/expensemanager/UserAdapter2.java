package com.example.expensemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by imrokraft on 16/6/15.
 */
public class UserAdapter2 extends ArrayAdapter<String> {

    Context myContext;
    ArrayList<String> notesid=new ArrayList<String>();

    ArrayList<String> notesexp=new ArrayList<String>();
    ArrayList<String> notescat=new ArrayList<String>();
    ArrayList<String> notesdes=new ArrayList<String>();
    ArrayList<String> notesdat=new ArrayList<String>();
    ArrayList<String> notestim=new ArrayList<String>();
    ArrayList<String> notes3=new ArrayList<String>();
    ArrayList<String> notes2=new ArrayList<String>();
    public UserAdapter2(Context context, ArrayList<String> notesid,ArrayList<String> notesexp,ArrayList<String> notescat,ArrayList<String> notesdes,ArrayList<String> notestim,ArrayList<String> notesdat) {
        super(context, R.layout.list_item, notesid);
        this.notesid = notesid;
        this.notesexp = notesexp;
        this.notescat=notescat;
        this.notesdes=notesdes;
        this.notestim=notestim;
        this.notesdat=notesdat;
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
    private View getCustomView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mlayoutInflater = LayoutInflater.from(myContext);
            convertView = mlayoutInflater.inflate(R.layout.list_item, parent, false);
        }
        final TextView id1 = (TextView) convertView.findViewById(R.id.textid1);
        final TextView exp = (TextView) convertView.findViewById(R.id.textexp2);
        final TextView cat=(TextView)convertView.findViewById(R.id.textcat3);
        final TextView des = (TextView) convertView.findViewById(R.id.textdes4);
        final TextView tim = (TextView) convertView.findViewById(R.id.texttime5);
        final TextView dat = (TextView) convertView.findViewById(R.id.textdate6);
        id1.setText(notesid.get(position));
        exp.setText(notesexp.get(position));
        cat.setText(notescat.get(position));
        des.setText(notesdes.get(position));
        tim.setText(notestim.get(position));
        dat.setText(notesdat.get(position));
        return convertView;

    }

}

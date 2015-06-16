package com.example.expensemanager;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by imrokraft on 3/6/15.
 */
public class ExpenseManagerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this,"HwKXPcDR63aQtYAvChk9nHC3W9pp2DsMl3F9imk8","OpKvw7ex1Z4rCWozoeqcB7NsqNNfcD7Or3OBZopM");
    }
}

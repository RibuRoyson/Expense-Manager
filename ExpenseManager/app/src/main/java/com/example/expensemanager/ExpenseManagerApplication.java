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
        Parse.initialize(this,"aZAsmlCrryBGiMFMKm5FCatLqEzGjbRT68ohSVVP","YXouNh45NcGRFk3ZSbEOd7r5fYdEFmJAZSNewN7U");
    }
}

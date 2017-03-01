package com.example.aaronpries.herds_social_app;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by aaronpries on 2/28/17.
 */

public class HerdsSocialApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

    }
}

package com.example.aaronpries.herds_social_app;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    CallbackManager callbackManager;

//    public MainActivity(){
//        startProgress();
//    }

    //Defining Variables
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private static final String TAG = "MainActivity";
    private FirebaseDatabase database;
    private RecyclerView mBlogList;
    private DatabaseReference myRef;
    private DatabaseReference muhRef;
    public boolean once = false;

    public boolean isOnce() {
        return once;
    }

    public void setOnce(boolean once) {
        this.once = once;
    }

    public void setTrue(boolean once) {
        setOnce(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AppEventsLogger.activateApp(getApplication());

        Intent intent = getIntent();
        String Lname = intent.getStringExtra("Lname");

        Bundle bundle=new Bundle();
        bundle.putString("Lname", "From Activity");
        //set Fragmentclass Arguments
        LoggedInFragment fragobj=new LoggedInFragment();
        fragobj.setArguments(bundle);



        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.aaronpries.herds_social_app",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }


        SharedPreferences settings = getSharedPreferences("prefs", 0);
        boolean firstRun = settings.getBoolean("firstRun", false);
        if (firstRun == false)//if running for first time
        //Splash will load for first time
        {
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("firstRun", true);
            editor.commit();
            Intent i = new Intent(MainActivity.this, SplashScreen.class);
            startActivity(i);
            finish();
        } else {

            HomePage();


            // Initializing Toolbar and setting it as the actionbar
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            //Initializing NavigationView
            navigationView = (NavigationView) findViewById(R.id.navigation_view);

            //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

                // This method will trigger on item Click of navigation menu
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {


                    //Checking if the item is in checked state or not, if not make it in checked state
                    if (menuItem.isChecked()) menuItem.setChecked(false);
                    else menuItem.setChecked(true);

                    //Closing drawer on item click
                    drawerLayout.closeDrawers();

                    //Check to see which item was being clicked and perform appropriate action
                    switch (menuItem.getItemId()) {


                        //Replacing the main content with ContentFragment Which is our Inbox View;
                        case R.id.events:
                            EventsFragment Efragment = new EventsFragment();
                            android.support.v4.app.FragmentTransaction EfragmentTransaction = getSupportFragmentManager().beginTransaction();
                            EfragmentTransaction.replace(R.id.frame, Efragment);
                            EfragmentTransaction.commit();
                            return true;

                        // For rest of the options we just show a toast on click

                        case R.id.groups:
                            GroupsFragment Gfragment = new GroupsFragment();
                            android.support.v4.app.FragmentTransaction GfragmentTransaction = getSupportFragmentManager().beginTransaction();
                            GfragmentTransaction.replace(R.id.frame, Gfragment);
                            GfragmentTransaction.commit();
                            return true;


                        case R.id.categories:
                            CategoriesFragment Cfragment = new CategoriesFragment();
                            android.support.v4.app.FragmentTransaction CfragmentTransaction = getSupportFragmentManager().beginTransaction();
                            CfragmentTransaction.replace(R.id.frame, Cfragment);
                            CfragmentTransaction.commit();
                            return true;

                        case R.id.profile:
                            ProfileFragment Pfragment = new ProfileFragment();
                            android.support.v4.app.FragmentTransaction PfragmentTransaction = getSupportFragmentManager().beginTransaction();
                            PfragmentTransaction.replace(R.id.frame, Pfragment);
                            PfragmentTransaction.addToBackStack("");
                            PfragmentTransaction.commit();

                            return true;

                        default:
                            Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                            return true;

                    }
                }
            });

            // Initializing Drawer Layout and ActionBarToggle
            drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
            ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

                @Override
                public void onDrawerClosed(View drawerView) {
                    // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                    super.onDrawerClosed(drawerView);
                }

                @Override
                public void onDrawerOpened(View drawerView) {
                    // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                    super.onDrawerOpened(drawerView);
                }
            };

            //Setting the actionbarToggle to drawer layout
            drawerLayout.addDrawerListener(actionBarDrawerToggle);

            //calling sync state is necessay or else your hamburger icon wont show up
            actionBarDrawerToggle.syncState();


        }
    }


    private void startProgress() {

        Intent intent = new Intent((MainActivity.this), SplashScreen.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            AddEventsFragment fragment = new AddEventsFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.addToBackStack("");
            fragmentTransaction.commit();
        }

            if (id == R.id.action_addGroup) {
                AddGroupFragment fragmentGroup = new AddGroupFragment();
                android.support.v4.app.FragmentTransaction fragmentTransactionGroup = getSupportFragmentManager().beginTransaction();
                fragmentTransactionGroup.replace(R.id.frame, fragmentGroup);
                fragmentTransactionGroup.addToBackStack("");
                fragmentTransactionGroup.commit();
        }

        return super.onOptionsItemSelected(item);
    }

    public void HomePage(){
        EventsFragment fragment = new EventsFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        firebaseRecyclerAdapter.cleanup();
//    }

}
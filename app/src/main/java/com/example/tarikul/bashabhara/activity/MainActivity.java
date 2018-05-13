package com.example.tarikul.bashabhara.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.example.tarikul.bashabhara.R;
import com.example.tarikul.bashabhara.adapter.ButtonAdapter;
import com.example.tarikul.bashabhara.model.ResidentialModel;
import com.github.clans.fab.FloatingActionMenu;
import com.github.clans.fab.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,ButtonAdapter.GridViewButtonInterface {
    FloatingActionMenu fabPost;
    private FloatingActionButton fabResidentialAd, fabIndustrialAd, fabCommercialAd;
    DatabaseReference databaseReference;

    boolean btnChecked =true;
    GridView btnGridView;
    public String[] filesnames = {
            "Family House",
            "Bachelor Mess",
            "Family Mess",
            "Sublet",
            "Office",
            "Warehouse",
            "Shop",
            "Garage",
            "Others",
            "Factory"
    };
    public Drawable[] drawables = new Drawable[10];

    ResidentialModel residentialModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initializeUI();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




//        fabPost.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(btnChecked){
//                    ResidentialAdAct myf = new ResidentialAdAct();
//                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                    transaction.add(R.id.frameLay , myf);
//                    transaction.commit();
//                    btnChecked=false;
//
//                }else {
//                    Intent intent = new Intent(MainActivity.this,MainActivity.class);
//                    startActivity(intent);
//                    ActivityCompat.finishAffinity(MainActivity.this);
//                }
//
//
//            }
//        });


    }

    public void initializeUI(){

        residentialModel = new ResidentialModel();
        fabPost = (FloatingActionMenu) findViewById(R.id.fab_post);
        fabResidentialAd =(FloatingActionButton) findViewById(R.id.fab_residential_ad);
        fabIndustrialAd =(FloatingActionButton) findViewById(R.id.fab_industrial_ad);
        fabCommercialAd = (FloatingActionButton) findViewById(R.id.fab_commercial_ad);


        btnGridView = (GridView)findViewById(R.id.btn_gridview);

        drawables[0] = this.getResources().getDrawable(R.drawable.ic_home);
        drawables[1] = this.getResources().getDrawable(R.drawable.ic_home);
        drawables[2] = this.getResources().getDrawable(R.drawable.ic_home);
        drawables[3] = this.getResources().getDrawable(R.drawable.ic_home);
        drawables[4] = this.getResources().getDrawable(R.drawable.ic_home);
        drawables[5] = this.getResources().getDrawable(R.drawable.ic_home);
        drawables[6] = this.getResources().getDrawable(R.drawable.ic_home);
        drawables[7] = this.getResources().getDrawable(R.drawable.ic_home);
        drawables[8] = this.getResources().getDrawable(R.drawable.ic_home);
        drawables[9] = this.getResources().getDrawable(R.drawable.ic_home);

        btnGridView.setAdapter(new ButtonAdapter(MainActivity.this,filesnames,this,drawables));

//        fabPost.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
//            @Override
//            public void onMenuToggle(boolean opened) {
//                if (opened) {
//                    showToast("Menu is opened");
//                } else {
//                    showToast("Menu is closed");
//                }
//            }
//        });
//handling each floating action button clicked
        fabResidentialAd.setOnClickListener(onButtonClick());
        fabIndustrialAd.setOnClickListener(onButtonClick());
        fabCommercialAd.setOnClickListener(onButtonClick());

        fabPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fabPost.isOpened()) {
                    fabPost.close(true);
                }

            }
        });


        databaseReference = FirebaseDatabase.getInstance().getReference("basha");
        databaseReference.addValueEventListener(new ValueEventListener() {
            ArrayList<ResidentialModel> values = new ArrayList<>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    ResidentialModel c = snapshot.getValue(ResidentialModel.class);
                    //Log.d("Categories: ", c.name + " " + c.food_items);
                    values.add(c);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_logout) {
            // Handle the camera action
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            ActivityCompat.finishAffinity(MainActivity.this);


        } else if (id == R.id.nav_insert) {

//            ResidentialAdAct myf = new ResidentialAdAct();
//
//            FragmentTransaction transaction = getFragmentManager().beginTransaction();
//            transaction.replace(R.id.frameLay , myf);
//            transaction.commit();

        } else if (id == R.id.nav_check) {
//            BlankFragment2 myf1 = new BlankFragment2();
//
//            FragmentTransaction transaction = getFragmentManager().beginTransaction();
//            transaction.replace(R.id.frameLay , myf1);
//            transaction.commit();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void getGridButtonPosition(int position) {

    }

    private View.OnClickListener onButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == fabResidentialAd) {
//                    btnGridView.setVisibility(View.GONE);
//                    ResidentialAdAct myf = new ResidentialAdAct();
//                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                    transaction.add(R.id.frameLay , myf);
//                    transaction.commit();

                    Intent intent = new Intent(MainActivity.this,  ResidentialAdAct.class);
                    startActivity(intent);
                } else if (view == fabIndustrialAd) {
                    showToast("Button Industrial clicked");
                } else {
                    showToast("Button Commercial clicked");
                }
                fabPost.close(true);
            }
        };
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

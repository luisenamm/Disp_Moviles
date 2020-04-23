package com.itesm.navdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        drawer=findViewById(R.id.drawerLayout);

        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(this,drawer,R.string.navigation_drawer_open);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmene_container,new FragmentEmail()).commit();

            navigationView.setCheckedItem(R.id.email);
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.email:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmene_container,new FragmentEmail()).commit();
                break;
            case R.id.chat:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmene_container,new FragmentChat()).commit();
                break;
            case R.id.iphone:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmene_container,new FragmentPhone()).commit();
                break;
            case R.id.mood:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmene_container,new FragmentMood()).commit();
                break;

        }
        drawer.closeDrawer(GravityCompat.START);


        return true;
    }

    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }
}

package com.example.exchangeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {

    private RecyclerView favRecView;
    private FavoritesRecViewAdapter adapter;
    NavigationView navigationView;
    private RelativeLayout parent;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        parent = findViewById(R.id.parent);
        drawerLayout = findViewById(R.id.my_drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new FavoritesRecViewAdapter();
        favRecView = findViewById(R.id.favRecView);
        favRecView.setAdapter(adapter);
        favRecView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<DailyPrice> dailyPrices = new ArrayList<>();
        dailyPrices.add(new DailyPrice("EURPLN","30-01-2023",4.7,4.71,4.69,4.705));
        dailyPrices.add(new DailyPrice("USDPLN","30-01-2023",4.32,4.34,4.29,4.31));
        dailyPrices.add(new DailyPrice("GBPPLN","30-01-2023",5.32,5.35,5.25,5.33));
        dailyPrices.add(new DailyPrice("JPYPLN","30-01-2023",0.033,0.034,0.032,0.033));
        adapter.setDailyPrices(dailyPrices);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){
                int id = menuItem.getItemId();

                if(id == R.id.nav_convert){
                    Intent intent = new Intent(FavoritesActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                return true;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);


    }
}
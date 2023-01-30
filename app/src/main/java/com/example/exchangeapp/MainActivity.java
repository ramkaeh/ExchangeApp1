package com.example.exchangeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;


import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;




public class MainActivity extends AppCompatActivity {

    Button btnConvert;
    Spinner fromCurrency;

    Spinner toCurrency;
    TextView textView;
    EditText edtInputValue;


    private RelativeLayout parent;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fromCurrency = findViewById(R.id.planets_spinner1);
        toCurrency = findViewById(R.id.planets_spinner);
        edtInputValue = findViewById(R.id.editText4);
        btnConvert = findViewById(R.id.button);
        textView = findViewById(R.id.textView7);
        parent = findViewById(R.id.parent);
        drawerLayout = findViewById(R.id.my_drawer_layout);
        navigationView = findViewById(R.id.nav_view);



        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*
        ArrayList<String> currencySymbol = new ArrayList<>();
        currencySymbol.add("EUR");
        currencySymbol.add("PLN");


        ArrayAdapter<String> currencyAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                currencySymbol
        );
        fromCurrency.setAdapter(currencyAdapter);
        toCurrency.setAdapter(currencyAdapter);
        */

        fromCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Selected " + fromCurrency.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        /*try {
            loadConvTypes();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edtInputValue.getText().toString().isEmpty())
                {
                    showSnackbar();
                    String toCurr = toCurrency.getSelectedItem().toString();
                    String fromCurr = fromCurrency.getSelectedItem().toString();
                    double fromCurrValue = Double.valueOf(edtInputValue.getText().toString());

                    Toast.makeText(MainActivity.this, "Please Wait..", Toast.LENGTH_SHORT).show();
                    /*try {
                        */convertCurrency(toCurr, fromCurr, fromCurrValue);/*
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }*/
                    //TODO: skonczyc
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Please Enter a Value to Convert..", Toast.LENGTH_SHORT).show();
                }

            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){
                int id = menuItem.getItemId();

                if(id == R.id.nav_fav){
                    Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
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

    private void showSnackbar(){


        Snackbar.make(parent,"Converting...",Snackbar.LENGTH_INDEFINITE)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    private void convertCurrency(final String fromCurr, final String toCurr, final Double fromCurrValue){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String inputLine;
                String result;

                boolean successful = false;
                while(!successful){
                    try{
                        String url = "https://www.alphavantage.co/query?function=FX_DAILY&from_symbol="+fromCurr+"&to_symbol="+toCurr+"&apikey=UEDG3N5D3ZMIKI2H";

                        System.out.println(url);

                        URL api = new URL(url);

                        HttpURLConnection connection =(HttpURLConnection) api.openConnection();

                        connection.connect();


                        if(connection.getResponseCode()==200)
                        {

                            InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
                            BufferedReader reader = new BufferedReader(streamReader);
                            StringBuilder stringBuilder = new StringBuilder();

                            while((inputLine = reader.readLine()) != null){
                                stringBuilder.append(inputLine);
                            }

                            reader.close();
                            streamReader.close();

                            result = stringBuilder.toString();

                            JSONObject jsonObject = new JSONObject(result);

                            String value = jsonObject.getJSONObject("Time Series FX (Daily)").getString("2023-01-30");
                            JSONObject jsonObject1 = new JSONObject(value);
                            String value1 = jsonObject1.getString("4. close");
                            System.out.println(value1);

                            double value2 = Double.parseDouble(value1);
                            double convertedValue = value2 * fromCurrValue;
                            String convertedValue1 = Double.toString(convertedValue);

                            System.out.println(convertedValue1);
                            textView.setText(convertedValue1);

                            break;

                        }
                        connection.disconnect();


                    } catch (Exception ex){
                        ex.printStackTrace();
                    }
                }

            }
        });
        thread.start();
        try{
            thread.join();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
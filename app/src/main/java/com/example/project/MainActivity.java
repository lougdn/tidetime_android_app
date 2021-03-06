package com.example.project;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.Manifest;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button Authenticate;
    Button Register;
    LocationManager locationManager;
    String provider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //we register a first user: admin
        MyCredential admin = new MyCredential("admin", RegistrationActivity.getMd5("admin"));
        if(!RegistrationActivity.Users.contains(admin)){
            RegistrationActivity.Users.add(admin);
        }

        super.onCreate(savedInstanceState);

        checkLocationPermission();
        setContentView(R.layout.activity_main);

        TextView login = (TextView)findViewById(R.id.login);
        TextView password = (TextView)findViewById(R.id.password);

        Authenticate = (Button)findViewById(R.id.authenticate);
        Authenticate.setOnClickListener(this);

        Register = (Button)findViewById(R.id.register);
        Register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.authenticate:
                TextView Login = (TextView) findViewById(R.id.login);
                String lgn = Login.getText().toString();
                TextView Password = (TextView) findViewById(R.id.password);
                String pwd = Password.getText().toString();
                String pwd_md5 = RegistrationActivity.getMd5(pwd);

                ArrayList<MyCredential> Users = RegistrationActivity.Users;
                for(int i = 0; i < RegistrationActivity.Users.size(); i++){
                    if(lgn.equals(Users.get(i).getLogin())){
                        if(pwd_md5.equals(Users.get(i).getPassword())){
                            Toast toast2 = Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT);
                            toast2.show();
                            Intent intent1 = new Intent(getApplicationContext(), TideActivity.class);
                            startActivity(intent1);
                            break;
                        }
                        else {
                            Toast toast3 = Toast.makeText(getApplicationContext(), "Wrong password", Toast.LENGTH_SHORT);
                            toast3.show();
                        }
                    }
                    else if(!lgn.equals(Users.get(i).getLogin()) &&  i == RegistrationActivity.Users.size() - 1) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Not registered", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
                break;

            case R.id.register:
                Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(intent);
                break;
        }
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Location permission")
                        .setMessage("Give us access to your location")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                        provider = locationManager.getBestProvider(new Criteria(), false);
                        locationManager.requestLocationUpdates(provider, 400, 1, (LocationListener) this);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

        }
    }
}

/*private class Authenticate(){

}*/


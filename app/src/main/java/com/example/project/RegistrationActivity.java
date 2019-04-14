package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.*;


public class RegistrationActivity  extends AppCompatActivity implements View.OnClickListener{

    Button registerReg;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        TextView login = (TextView)findViewById(R.id.login);
        TextView password = (TextView)findViewById(R.id.password);


        cancel = (Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
        registerReg = (Button)findViewById(R.id.registerreg);
        registerReg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerreg:
                Register("UserInfo", this);
                Intent register = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(register);
            case R.id.cancel:
                Intent cancel = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(cancel);
        }
    }
    public void Register(String file, Context context){

        //FileOutputStream outputStream;
        TextView login = (TextView)findViewById(R.id.loginreg);
        TextView password = (TextView)findViewById(R.id.passwordreg);
        MyCredential User = new MyCredential(login.toString(), password.toString());
        try {
            User.HashPassword();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

}

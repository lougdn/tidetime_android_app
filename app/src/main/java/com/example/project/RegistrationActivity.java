package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonWriter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.security.*;
import java.util.List;


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

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        TextView login = (TextView)findViewById(R.id.loginreg);
        TextView password = (TextView)findViewById(R.id.passwordreg);
        MyCredential User = new MyCredential(login.toString(), password.toString());

        try {
            User.HashPassword();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        Message msg = Message.obtain();
        msg.obj = User.getLogin() + User.getPassword();
        List<Message> messages = null;
        messages.add(msg);
        try {
            writeJsonStream(out, messages);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeJsonStream(OutputStream out, List<Message> messages) throws IOException {
        JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
        writer.setIndent("  ");
        writeMessagesArray(writer, messages);
        writer.close();
    }

    public void writeMessagesArray(JsonWriter writer, List<Message> messages) throws IOException {
        writer.beginArray();
        for (Message message : messages) {
            //writeMessage(writer, message);
        }
        writer.endArray();
    }

    /*public void writeMessage(JsonWriter writer, Message message) throws IOException {
        writer.beginObject();
        writer.name("login").value(message.);
        writer.name("password").value(message.arg2);
        writer.endObject();
    }*/

}

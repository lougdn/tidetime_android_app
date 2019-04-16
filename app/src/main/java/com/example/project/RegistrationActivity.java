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
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.security.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrationActivity  extends AppCompatActivity implements View.OnClickListener{

    Button registerReg;
    Button cancel;
    public static ArrayList<MyCredential> Users = new ArrayList<MyCredential>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        cancel = (Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
        registerReg = (Button)findViewById(R.id.registerreg);
        registerReg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerreg:
                //function called is used to write information about user registered
                //Register("UserInfo.txt", this);

                //we add the new user to the list of users
                TextView login = (TextView)findViewById(R.id.loginreg);
                String log = login.getText().toString();

                TextView password = (TextView)findViewById(R.id.passwordreg);
                String pwd = password.getText().toString();
                String pwd_md5 = getMd5(pwd);
                MyCredential user = new MyCredential(log, pwd_md5);

                try {
                    Users.add(user);
                    Toast toast = Toast.makeText(getApplicationContext(), "You are registered", Toast.LENGTH_SHORT);
                    toast.show();
                }
                catch(Exception e) {
                    e.printStackTrace();
                }


                Intent register = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(register);
                break;

            case R.id.cancel:
                //we return to the login page
                Intent cancel = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(cancel);
                break;
        }
    }

    public static String getMd5(String input)
    {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }



    //functions to write information about registered user into json file

    /*
    public void Register(String file, Context context){

        FileOutputStream fOut = null;
        try {
            fOut = openFileOutput(file, MODE_PRIVATE);
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
        msg.obj = login.toString() + password.toString();
        List<Message> messages = new ArrayList<Message>();
        messages.add(msg);
        try {
            writeJsonStream(fOut, messages);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeJsonStream(FileOutputStream out, List<Message> messages) throws IOException {
        OutputStreamWriter osw = new OutputStreamWriter(out);
        JsonWriter writer = new JsonWriter(osw);
        writer.setIndent("  ");
        writeMessagesArray(writer, messages);
        writer.close();
    }

    public void writeMessagesArray(JsonWriter writer, List<Message> messages) throws IOException {
        writer.beginArray();
        for (Message message : messages) {
            writeMessage(writer, message);
        }
        writer.endArray();
    }

    public void writeMessage(JsonWriter writer, Message message) throws IOException {
        writer.beginObject();
        writer.name("login").value(message.toString());
        //writer.name("password").value(message.arg2);
        writer.endObject();
    }
    */
}

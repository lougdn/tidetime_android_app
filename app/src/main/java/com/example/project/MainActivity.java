package com.example.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button Authenticate;
    Button Register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

                MyCredential mycred = new MyCredential(lgn, pwd);
                //new Authenticate().execute(mycred);
        }
    }
}

/*private class Authenticate(){

}*/

package com.example.rathana.sharpreference;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rathana.sharpreference.data.Database;

public class LoginActivity extends AppCompatActivity {

    private EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);

    }

    public void onLogin(View view) {

        User userLogin=new User();
        userLogin.setName(username.getText().toString());
        userLogin.setPassword(password.getText().toString());

        //authenticate

        User u=Database.getUserRepository().authenticate(userLogin);
        if(u==null)
            Toast.makeText(this, "You login fail.Please try again!", Toast.LENGTH_SHORT).show();
        else {
            u.setLogin(true);
            //save user object to User Preference file -> user Session
            UserPreference.save(this,u);
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }

    }


    public void onGoSignup(View view) {
        startActivity(new Intent(this,RegisterActivity.class));
        finish();
    }
}

package com.example.rathana.sharpreference;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.rathana.sharpreference.data.Database;

public class RegisterActivity extends AppCompatActivity {

    private EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
    }

    public void onSignup(View view) {
        User userSignup=new User();
        userSignup.setName(username.getText().toString());
        userSignup.setPassword(password.getText().toString());

        //save new user
        Database.getUserRepository().saveUser(userSignup);
        startActivity(new Intent(this,LoginActivity.class));
        finish();

    }
}

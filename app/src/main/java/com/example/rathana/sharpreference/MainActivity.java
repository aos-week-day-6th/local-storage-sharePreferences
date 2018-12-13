package com.example.rathana.sharpreference;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText text;
    private TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text=findViewById(R.id.text);
        tvResult=findViewById(R.id.tvResult);

        //check if user session null or login
        if(!UserPreference.isLogin(this)){
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }

    }

    public void onSave(View view) {
        saveToPreference(text.getText().toString());

    }

    public void onRead(View view) {
        tvResult.setText(readPreference());
    }
    private void saveToPreference(String text){
        SharedPreferences preferences=getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("data",text);
        editor.apply();
        Toast.makeText(this, "Save success", Toast.LENGTH_SHORT).show();

    }

    private String readPreference(){
        SharedPreferences preferences =getPreferences(Context.MODE_PRIVATE);
       return preferences.getString("data","");

    }


}

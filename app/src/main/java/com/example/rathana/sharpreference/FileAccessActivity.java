package com.example.rathana.sharpreference;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileAccessActivity extends AppCompatActivity {


    EditText text;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_access);

        text=findViewById(R.id.text);
        result=findViewById(R.id.result);
    }

    public void onSaveInternal(View view) {
        writeInternal(text.getText().toString());
    }

    public void onReadInternal(View view) {
        result.setText(readInternal("myFile.txt"));
    }

    public void onDeleteInternal(View view) {
        deleteInternal("myFile.txt");
    }

    ////create method to access files

    private void writeInternal(String text){
        String filename="myFile.txt";
        File file=new File(getFilesDir(),filename);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //write file to internal storage
        FileOutputStream ous=null;
        try{
            ous=openFileOutput(filename,Context.MODE_PRIVATE);
            ous.write(text.getBytes());
            Toast.makeText(this, "write success", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                ous.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private String readInternal(String filename){
        File file =new File(getFilesDir(),filename);
        if(file.exists()){
            FileInputStream ins=null;
            String data="";
            try{
                ins=openFileInput(filename);
                int ch;
                while((ch= ins.read())!=-1){
                    data=data+(char) ch;
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    ins.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return data;
        }else
            Toast.makeText(this, "File doesn't exits!", Toast.LENGTH_SHORT).show();
        return "";
    }

    private  void deleteInternal(String filename){
        File file=new File(getFilesDir(),filename);
        if(file.exists()){
            file.delete();
            Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(this, "File doesn't exit", Toast.LENGTH_SHORT).show();
    }
}

package com.example.rathana.sharpreference;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class FileAccessActivity extends AppCompatActivity {


    EditText text;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_access);

        text=findViewById(R.id.text);
        result=findViewById(R.id.result);

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }

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

    //button event
    public void onSaveCacheFile(View view) {
            createCacheFile();

    }
    public void onReadCacheFile(View view){
        result.setText(readCacheFile());
    }
    public void onDeleteCacheFile(View view){
        File file=  new File(getCacheDir(),
                "cache1");
        if(file.exists())
            file.delete();
        Toast.makeText(this, "delete success", Toast.LENGTH_SHORT).show();
    }

    public void createCacheFile(){
        String fileName="cache1";
        FileOutputStream ous=null;
        try {

            /*File file= File.createTempFile(
                    fileName,
                    "_test",
                    getCacheDir());*/

            File file=new File(getCacheDir(),"cache1");
            ous=new FileOutputStream(file);
            ous.write(text.getText().toString().getBytes());
            Toast.makeText(this, "save success", Toast.LENGTH_SHORT).show();
        }catch ( Exception e){
            e.printStackTrace();
        }finally {
            try {
                ous.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private  String readCacheFile(){
        File file=  new File(getCacheDir(),
                "cache1");

        String data="";
        try {
            BufferedReader reader=new BufferedReader(
                    new InputStreamReader(new FileInputStream(file))

            );

            data=reader.readLine();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }


    public void onSaveExternalPrivateFile(View view) {
        createFile(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
                "text.txt");
    }

    public void onPublicFile(View view) {
    createFile(Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_DOCUMENTS
    ), "publicFile.txt");

    }

    //create file and store in External
    private void  createFile(File f,String filename){
        File file= new File(f,filename);

        OutputStream ous=null;
        if(!file.exists()) {
            try {
                file.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            ous=new FileOutputStream(file);
            ous.write(text.getText().toString().getBytes());
            Toast.makeText(this, "save file success", Toast.LENGTH_SHORT).show();
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

}

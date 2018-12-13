package com.example.rathana.sharpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class UserPreference {

    public static  final  String ID="id";
    public static  final  String NAME="name";
    public static  final  String PASSWORD="password";
    public static  final  String IS_LOGIN="is_login";
    public static  final  String USER_PREFS="user_prefs.xml";

    public static void save(Context context ,User user){
        SharedPreferences.Editor editor=getPreferences(context).edit();

        if(user!=null){
            editor.putInt(ID,user.getId());
            editor.putString(NAME,user.getName());
            editor.putString(PASSWORD,user.getPassword());
            editor.putBoolean(IS_LOGIN,true);

        }else{
            editor.putInt(ID,0);
            editor.putString(NAME,null);
            editor.putString(PASSWORD,null);
            editor.putBoolean(IS_LOGIN,false);
        }
        editor.apply();
        Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
    }

    //logout
    public static  void  remove(Context context){
        save(context,null);
    }

    public static  User get(Context context){
        SharedPreferences preferences=getPreferences(context);
        return  new User(
                preferences.getInt(ID,0),
                preferences.getString(NAME,null),
                preferences.getString(PASSWORD,null),
                preferences.getBoolean(IS_LOGIN,false)
                );
    }

    public  static Boolean isLogin(Context context){
        return get(context).isLogin();
    }

    private static SharedPreferences getPreferences(Context context){
        return  context.getSharedPreferences(USER_PREFS,Context.MODE_PRIVATE);
    }
}

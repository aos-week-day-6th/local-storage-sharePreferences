package com.example.rathana.sharpreference.data;

public class Database {

    private  static UserRepository userRepository;

    private Database(){}

    public static UserRepository getUserRepository(){
        if(userRepository==null)
            userRepository=new UserRepository();
        return userRepository;
    }
}

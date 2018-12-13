package com.example.rathana.sharpreference.data;

import com.example.rathana.sharpreference.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository
{

    List<User> users=new ArrayList<>();

    public void saveUser(User user){
        users.add(user);
    }

    public List<User> getUsers(){
        return this.users;
    }

    public User authenticate(User user){
        for(User u : users){
            if(u.getName().equals(user.getName()) &&
                u.getPassword().equals(user.getPassword())){
                return u;
            }
        }

        return null;
    }
}

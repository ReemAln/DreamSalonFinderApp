package com.example.dreamsalonfinderapp;


import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DAOUser {

    private DatabaseReference databaseReference;

    public DAOUser() {
        FirebaseDatabase DB = FirebaseDatabase.getInstance();
        databaseReference = DB.getReference(User.class.getSimpleName());
    }

    public Task<Void> add(User user) {
        return databaseReference.push().setValue(user);
    }

    public Task<Void> update(String key, HashMap<String, Object> hashMap) {
        return databaseReference.child(key).updateChildren(hashMap);
    }

    public Task<Void> remove(String key) {
        return databaseReference.child(key).removeValue();
    }

    //    public Task<DataSnapshot> getAll() {
//        return databaseReference.get();
//    }
}
package com.example.whatsaapcloneapp.Repository;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.example.whatsaapcloneapp.views.GroupsActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Repository {

    public void firebaseAnonymousAuth(Context context){
        FirebaseAuth.getInstance().signInAnonymously()
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent i = new Intent(context, GroupsActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(i);
                        }
                    }
                });
    }

    //get current user ID
    public String getCurrentUserId(){
        return FirebaseAuth.getInstance().getUid();
    }

    //SignOut Functionality
    public void signOUT(){
        FirebaseAuth.getInstance().signOut();
    }
}

package com.nghia.shopsneaker.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import com.nghia.shopsneaker.Config.PreferanceManager;
import com.nghia.shopsneaker.R;

public class MenuActivity  extends AppCompatActivity
 implements View.OnClickListener {
    private PreferanceManager preferanceManager;
    private Button btnstore,btnuser;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnstore=findViewById(R.id.btnstore);
        btnuser=findViewById(R.id.btnuser);
        preferanceManager=new PreferanceManager(this);
        btnuser.setOnClickListener(this);
        btnstore.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnstore:
                preferanceManager.PutType("STORE");break;
            case R.id.btnuser:preferanceManager.PutType("USERS");
               break;

        }
        startActivity(new Intent(this,LoginActivity.class));


    }
}

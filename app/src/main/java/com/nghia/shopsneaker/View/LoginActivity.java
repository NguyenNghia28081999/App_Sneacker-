package com.nghia.shopsneaker.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.nghia.shopsneaker.Config.PreferanceManager;
import com.nghia.shopsneaker.Controller.UserController;
import com.nghia.shopsneaker.R;

public class LoginActivity  extends AppCompatActivity
 implements View.OnClickListener {
    private Button btnlogin;
    private EditText editemail,editpass;
    private TextView txtdangky;
    private UserController userController;
    private PreferanceManager preferanceManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InitWidget();
        Init();
        HandleEvents();
    }

    private void HandleEvents() {
        txtdangky.setOnClickListener(this);
        btnlogin.setOnClickListener(this);
    }

    private void Init() {
        preferanceManager=new PreferanceManager(this);
        userController=new UserController(this);
    }

    private void InitWidget() {
        btnlogin=findViewById(R.id.btnlogin);
        txtdangky=findViewById(R.id.txtsignup);
        editemail=findViewById(R.id.editemail);
        editpass=findViewById(R.id.editpass);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtsignup: startActivity(new Intent(this,SignUpActivity.class));break;
            case R.id.btnlogin:
                String email=editemail.getText().toString().trim();
                String pass=editpass.getText().toString().trim();
                if(preferanceManager.getType().equals("USERS")){
                    userController.HandleLoginUser(email,pass);
                }else{
                    FirebaseFirestore db =FirebaseFirestore.getInstance();
                    db.collection("ADMIN")
                            .whereEqualTo("admin",email)
                            .whereEqualTo("matkhau",pass)
                            .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                       if(task.isSuccessful()){
                           startActivity(new Intent(LoginActivity.this,QuanLyActivity.class));
                       }
                        }
                    });
                }

        }
    }
}

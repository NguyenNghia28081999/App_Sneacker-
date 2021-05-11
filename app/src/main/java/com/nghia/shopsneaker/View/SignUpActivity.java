package com.nghia.shopsneaker.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.nghia.shopsneaker.Config.PreferanceManager;
import com.nghia.shopsneaker.Controller.UserController;
import com.nghia.shopsneaker.R;

public class SignUpActivity extends AppCompatActivity
 implements View.OnClickListener {
    private Button btndangky;
    private EditText editemail,editpass,editpass1,edithoten,
    editsdt;
    private PreferanceManager preferanceManager;
    private int checked=0;
    private String Email_Vali="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private UserController userController;
    Toolbar toolbar;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        InitWidget();
        Init();
        btndangky.setOnClickListener(this);
    }

    private void Init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        userController=new UserController(this);
            preferanceManager=new PreferanceManager(this);
            if(preferanceManager.getType().equalsIgnoreCase("USERS")){
            }else{
                checked=1;
            }
    }

    private void InitWidget() {
        toolbar=findViewById(R.id.toolbar);
        btndangky=findViewById(R.id.btnlogin);
       edithoten=findViewById(R.id.editten);

        editsdt=findViewById(R.id.editsdt);
        editemail=findViewById(R.id.editemail);
        editpass=findViewById(R.id.editpass);
        editpass1=findViewById(R.id.editpass1);
    }

    @Override
    public void onClick(View v) {
           switch (v.getId()){
               case R.id.btnlogin:
                   String email=editemail.getText().toString().trim();
                   String ten=edithoten.getText().toString().trim();
                   String sdt=editsdt.getText().toString().trim();
                   String pass=editpass.getText().toString().trim();
                   String pass1=editpass1.getText().toString().trim();
                   if(email.length()>0 && !email.matches(Email_Vali)){
                       Toast.makeText(this,"Email không hợp lệ",Toast.LENGTH_LONG).show();
                   }
                   if(ten.length()<10){
                       Toast.makeText(this, "Tối thiểu 10 ký tự", Toast.LENGTH_SHORT).show();
                   }
                   if(pass.length()<6){
                       Toast.makeText(this, "Mật khẩu tối thiểu 6 ký tự", Toast.LENGTH_SHORT).show();
                   }
                   if(!pass.equals(pass1)){
                       Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                   }
                   if(sdt.length()<9 || sdt.length()>11){
                       Toast.makeText(this, "SDT >9 & SDT <12", Toast.LENGTH_SHORT).show();
                   }
                   userController.HandleDangKyUser(email,pass,ten,sdt,preferanceManager.getType());
                    break;

                   
           }
    }
}

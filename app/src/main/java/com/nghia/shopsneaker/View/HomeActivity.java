package com.nghia.shopsneaker.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.nghia.shopsneaker.Config.PreferanceManager;
import com.nghia.shopsneaker.R;
import com.nghia.shopsneaker.View.FragMent.FragMent_Home;
import com.nghia.shopsneaker.View.FragMent.FragMent_ProFile;
import com.nghia.shopsneaker.View.Store.CreateNewsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity  extends AppCompatActivity
 implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomNavigationView;
    private Fragment fm;
    private PreferanceManager preferanceManager;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        InitWidget();
        Init();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }



    private void Init() {
        preferanceManager=new PreferanceManager(this);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        fm=new FragMent_Home();
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,fm).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    private void InitWidget() {
       bottomNavigationView=findViewById(R.id.bottomnavigation);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.trangchu: fm=new FragMent_Home();break;
            case R.id.giohang:
                if(firebaseUser!=null && firebaseUser.isEmailVerified()){

                          startActivity(new Intent(this,GioHangActivity.class));
                }else{

                    startActivity(new Intent(this,MenuActivity.class));

                }

                break;
            case R.id.thongtin:
                fm=new FragMent_ProFile();break;
        }
        if(fm!=null){
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,fm).commit();
        }
        return true;
    }


}

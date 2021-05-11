package com.nghia.shopsneaker.View;

import android.Manifest;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.nghia.shopsneaker.Adapter.HoaDonAdapter;
import com.nghia.shopsneaker.Config.PreferanceManager;
import com.nghia.shopsneaker.Controller.HoaDonController;
import com.nghia.shopsneaker.Controller.HoaDonView;
import com.nghia.shopsneaker.Controller.UserController;
import com.nghia.shopsneaker.Model.HoaDonModel;
import com.nghia.shopsneaker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class HomeStoreActivity  extends AppCompatActivity
 implements View.OnClickListener , HoaDonView {

    private Toolbar toolbar;
    private GridView gv;
    private HoaDonController hoaDonController;
    private ArrayList<HoaDonModel> hoaDonModels;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_store);
        InitWidget();
        Init();


    }

    private void InitWidget() {
        toolbar=findViewById(R.id.toolbar);
        gv=findViewById(R.id.gv);
    }
    private  void Init(){
hoaDonModels=new ArrayList<>();
       setSupportActionBar(toolbar);
       getSupportActionBar().setTitle("Hóa Đơn Khách Hàng");
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       toolbar.setNavigationOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               startActivity(new Intent(HomeStoreActivity.this,HomeActivity.class));
               finish();
           }
       });
       hoaDonController=new HoaDonController(this);
       hoaDonController.HandleGetDataHoaDon();

    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void getDataHoaDon(String ten, String diachi, String sdt, long tongtien, String uid, String id,String ngay,String mahoadon) {
       hoaDonModels.add(new HoaDonModel(ten,diachi,sdt,tongtien,uid,id,ngay,mahoadon));
        HoaDonAdapter hoaDonAdapter=new HoaDonAdapter(this,hoaDonModels);
        gv.setAdapter(hoaDonAdapter);
    }
}

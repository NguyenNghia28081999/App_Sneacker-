package com.nghia.shopsneaker.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nghia.shopsneaker.Controller.INewsController;
import com.nghia.shopsneaker.Model.NewsModel;
import com.nghia.shopsneaker.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ChitietSanPhamActivity  extends AppCompatActivity
{
    private Toolbar toolbar;
    private ImageView hinhanh;
    private TextView txtten,txtmota,txttype,txtngay,txtgiatien;
    private Intent intent;
    private NewsModel newsModel;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private Button btndathang;
    private INewsController newsController;
    private AppCompatSpinner spinerSize;
    private String Size;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_sp);
        InitWidget();
        INit();
    }

    private void INit() {
        newsController=new INewsController(this);
        intent=getIntent();
        newsModel= (NewsModel) intent.getSerializableExtra("NEWS");
        txtngay.setText(newsModel.getNgay());
        txtten.setText(newsModel.getTen());
        txtmota.setText("Mô tả : "+newsModel.getMota());
        txttype.setText("Loại : "+ newsModel.getType());
        txtgiatien.setText("Giá tiền : "+newsModel.getGiatien()+"đ");
        firebaseStorage=FirebaseStorage.getInstance();
        storageReference=firebaseStorage.getReference();
        storageReference.child("Store")
                .child(newsModel.getHinhanh()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(ChitietSanPhamActivity.this).load(uri).into(hinhanh);
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Back ");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btndathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsController.HandleInsertGiohang(newsModel.getTen(),newsModel.getHinhanh(),1,
                        newsModel.getGiatien(),1*newsModel.getGiatien(),Size);
                Toast.makeText(ChitietSanPhamActivity.this,"Đã thêm vào giỏ hàng",Toast.LENGTH_LONG).show();

            }
        });
        String[] arr=newsModel.getSize().split(",");
        final List<String> SizeS=new ArrayList();
        for(String a : arr){
            SizeS.add(a);
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,SizeS);
        spinerSize.setAdapter(arrayAdapter);
        spinerSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView)view).setTextColor(ContextCompat.getColor(ChitietSanPhamActivity.this,
                        R.color.white));
                Size=SizeS.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void InitWidget() {
        spinerSize=findViewById(R.id.spinerSize);
        btndathang=findViewById(R.id.btnthemgiohang);
        toolbar=findViewById(R.id.toolbar);
        hinhanh=findViewById(R.id.hinhanh);
        txtngay=findViewById(R.id.txtngay);
        txttype=findViewById(R.id.txttype);
        txtten=findViewById(R.id.txtten);
        txtgiatien=findViewById(R.id.txtgiatien);
        hinhanh=findViewById(R.id.hinhanh);
        txtmota=findViewById(R.id.txtmota);

    }
}

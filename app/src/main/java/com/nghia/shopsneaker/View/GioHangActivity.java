package com.nghia.shopsneaker.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.nghia.shopsneaker.Adapter.GiohangAdapter;
import com.nghia.shopsneaker.Controller.INewsController;
import com.nghia.shopsneaker.Controller.NewView;
import com.nghia.shopsneaker.Model.NewsModel;
import com.nghia.shopsneaker.R;

import java.util.ArrayList;

public class GioHangActivity  extends AppCompatActivity
 implements NewView {
    private GridView gridView;
    private Toolbar toolbar;
    private ArrayList<NewsModel> arrayList;
    private INewsController newsController;
    private GiohangAdapter giohangAdapter;
    private Button btndathang;
    public static TextView txttongtien;
    public static CountDownTimer countDownTimer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
        toolbar=findViewById(R.id.toolbar);
        txttongtien=findViewById(R.id.txttongtien);
        btndathang=findViewById(R.id.btndathang);
        gridView=findViewById(R.id.gv);
        arrayList=new ArrayList<>();
        newsController=new INewsController(this,this);
        newsController.HandlegetDataGioHang(arrayList);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Giỏ hàng");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        countDownTimer=new CountDownTimer(1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                if(arrayList.size()>0){
                    arrayList.clear();
                }

                newsController.HandlegetDataGioHang(arrayList);


            }
        };



    }

    @Override
    public void getDataGiohang(String id, String ten, String hinhanh, int soluong, long giatien, long tongtien) {
        arrayList.add(new NewsModel(id,ten,hinhanh,soluong,giatien,tongtien));
        giohangAdapter=new GiohangAdapter(this,arrayList);
        gridView.setAdapter(giohangAdapter);

        long tongtiens=0;

        for(NewsModel n : arrayList){
            tongtiens+=n.getSoluong()*n.getGiatien();
        }
        txttongtien.setText(tongtiens+" đ");
        final long tien=tongtiens;
        btndathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(GioHangActivity.this,ThanhToanActivity.class);
                intent.putExtra("TONGTIEN",tien);
                startActivity(intent);
            }
        });
    }

    @Override
    public void getDataSanPham(String id, String ten, String diachi, String ngay, String type, String hinhanh, String mota, int soluong, long giatien) {

    }
}

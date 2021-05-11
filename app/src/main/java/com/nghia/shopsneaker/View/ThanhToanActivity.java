package com.nghia.shopsneaker.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.nghia.shopsneaker.Controller.HoaDonController;
import com.nghia.shopsneaker.Controller.HoaDonView;
import com.nghia.shopsneaker.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ThanhToanActivity  extends AppCompatActivity
 implements HoaDonView {
    private Button btncapnhat;
    private EditText editsdt,editdiachi,editten;
    private TextView txttongtien;
    private Toolbar toolbar;
    private Intent intent;
    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat;
    private HoaDonController hoaDonController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanhtoan);
        InitWidget();
        Init();
        hoaDonController=new HoaDonController(this);
        calendar=Calendar.getInstance();
        simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");

        intent=getIntent();
        final long tongtien=intent.getLongExtra("TONGTIEN",0);
        txttongtien.setText("Tổng Tiền : "+tongtien);
        btncapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sdt=editsdt.getText().toString().trim();
                String diachi=editdiachi.getText().toString().trim();
                String ten=editten.getText().toString().trim();
                if(sdt.length()<9 && sdt.length()>12){
                    Toast.makeText(ThanhToanActivity.this, "số điện thoại >=10 && <=11", Toast.LENGTH_SHORT).show();
                }
                if(sdt.length()>9 && sdt.length()<12 && diachi.length()>0&& ten.length()>0) {
                    hoaDonController.HandleSendHoaDon(ten,diachi,sdt,tongtien, FirebaseAuth.getInstance().getCurrentUser().getUid(),
                            simpleDateFormat.format(calendar.getTime()),"MAHD"+System.currentTimeMillis()+"");
                    Toast.makeText(ThanhToanActivity.this,"Thanh toán thành công",Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Toast.makeText(ThanhToanActivity.this,"Không để trống bất kỳ thông tin nào",Toast.LENGTH_LONG).show();

                }

            }
        });
    }

    private void Init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Thanh toán");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void InitWidget() {
        editten=findViewById(R.id.edithoten);
        btncapnhat=findViewById(R.id.btncapnhat);
        editdiachi=findViewById(R.id.editdiachi);
        editsdt=findViewById(R.id.editsdt);
        toolbar=findViewById(R.id.toolbar);
        txttongtien=findViewById(R.id.txttongtien);
    }

    @Override
    public void getDataHoaDon(String ten, String diachi, String sdt, long tongtien, String uid, String id,String ngay,String mahoadon) {

    }
}

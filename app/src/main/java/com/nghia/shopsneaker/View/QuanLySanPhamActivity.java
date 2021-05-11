package com.nghia.shopsneaker.View;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.nghia.shopsneaker.Adapter.SanPhamAdapter;
import com.nghia.shopsneaker.Controller.INewsController;
import com.nghia.shopsneaker.Controller.NewView;
import com.nghia.shopsneaker.Model.NewsModel;
import com.nghia.shopsneaker.R;

import java.util.ArrayList;

public class QuanLySanPhamActivity  extends AppCompatActivity
implements NewView {
    private Toolbar toolbar;
    private GridView gv;
    private INewsController newsController;
    private SanPhamAdapter newsAdapter;
    private ArrayList<NewsModel> arrayList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly_sp);
        InitWidget();
        Init();
    }

    private void Init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Back");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        arrayList=new ArrayList<>();
        newsController=new INewsController(this,this);
        newsController.HandlegetDataNewsManager();

        gv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                 NewsModel newsModel=arrayList.get(position);
                 DiaLogRequire(newsModel.getID(),newsModel.getTen(),newsModel.getDiachi(),
                         newsModel.getGiatien(),newsModel.getSoluong(),newsModel.getMota());

                return true;
            }
        });
    }

    private void DiaLogRequire(final String ID, final String ten, final String diachi, final long giatien, final int soluong, final String mota) {
        final Dialog dialog=new Dialog(this);
        Log.d("CHECKED",ID+"");
        dialog.setContentView(R.layout.dialog_require);
        dialog.show();
       Button btncapnhat=dialog.findViewById(R.id.btncapnhat);
       Button btnxoa=dialog.findViewById(R.id.btnxoa);
       final EditText editsoluong=dialog.findViewById(R.id.editsoluong);
        final EditText editdiachi=dialog.findViewById(R.id.editdiachi);
       final EditText editten=dialog.findViewById(R.id.editten);
        final EditText editgiatien=dialog.findViewById(R.id.editgiatien);
       final EditText editmota=dialog.findViewById(R.id.editmota);
           editsoluong.setText(soluong+"");
           editmota.setText(mota);
           editten.setText(ten);
           editgiatien.setText(giatien+"");
           editdiachi.setText(diachi);
          btncapnhat.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  String soluong=editsoluong.getText().toString().trim();
                  String mota=editmota.getText().toString().trim();
                  String ten=editten.getText().toString().trim();
                  String giatien=editgiatien.getText().toString().trim();
                  String diachi=editdiachi.getText().toString().trim();
                  newsController.HandleCapNhat(ID, ten, diachi, Long.parseLong(giatien), Integer.parseInt(soluong), mota);
                  Hienthi();
                  dialog.cancel();
              }
          });
          btnxoa.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  newsController.HandleXoa(ID);
                  Hienthi();
                  dialog.cancel();

              }
          });

    }
    private  void  Hienthi(){
        if(arrayList.size()>0){
            arrayList.clear();
        }
        newsController.HandlegetDataNewsManager();

    }

    private void InitWidget() {
        toolbar=findViewById(R.id.toolbar);
        gv=findViewById(R.id.gv);
    }


    @Override
    public void getDataGiohang(String id, String ten, String hinhanh, int soluong, long giatien, long tongtien) {

    }

    @Override
    public void getDataSanPham(String id, String ten, String diachi, String ngay, String type, String hinhanh, String mota, int soluong, long giatien) {
      arrayList.add(new NewsModel(id,ten,diachi,ngay,type,hinhanh,mota,soluong,giatien));
      newsAdapter=new SanPhamAdapter(this,arrayList);
      gv.setAdapter(newsAdapter);
    }
}

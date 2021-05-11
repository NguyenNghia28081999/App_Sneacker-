package com.nghia.shopsneaker.View.Store;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.nghia.shopsneaker.Adapter.NewsAdapter;
import com.nghia.shopsneaker.Config.PreferanceManager;
import com.nghia.shopsneaker.Controller.INewsController;
import com.nghia.shopsneaker.Controller.UserController;
import com.nghia.shopsneaker.Model.NewsModel;
import com.nghia.shopsneaker.R;
import com.nghia.shopsneaker.View.ChitietSanPhamActivity;

import java.util.ArrayList;

public class StoreNewsActivity  extends AppCompatActivity {
    private UserController userController;
    private ImageView hinhanh;
    private Toolbar toolbar;
    private GridView gv;
    private NewsAdapter newsAdapter;
    private ArrayList<NewsModel> arrayList;
    private INewsController newsController;
    private Intent intent;
    private PreferanceManager preferanceManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_news);
        InitWidget();
        Init();
    }

    private void Init() {
        preferanceManager=new PreferanceManager(this);
        arrayList=new ArrayList<>();
        intent=getIntent();
       newsController=new INewsController(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(preferanceManager.getTypeShoe());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        newsController.HandleReadDataNews(preferanceManager.getTypeShoe(),arrayList);
        newsAdapter=new NewsAdapter(this,arrayList);
        gv.setAdapter(newsAdapter);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                newsAdapter.notifyDataSetChanged();
            }
        },2500);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent=new Intent(StoreNewsActivity.this, ChitietSanPhamActivity.class);
                intent.putExtra("NEWS",arrayList.get(position));
                startActivity(intent);
            }
        });
    }

    private void InitWidget() {

        toolbar=findViewById(R.id.toolbar);
        hinhanh=findViewById(R.id.hinhanh);
        gv=findViewById(R.id.gv);
    }


}

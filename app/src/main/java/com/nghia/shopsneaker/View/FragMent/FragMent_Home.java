package com.nghia.shopsneaker.View.FragMent;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.navigation.NavigationView;
import com.nghia.shopsneaker.Adapter.NewsAdapter;
import com.nghia.shopsneaker.Config.PreferanceManager;
import com.nghia.shopsneaker.Controller.BannerController;
import com.nghia.shopsneaker.Controller.INewsController;
import com.nghia.shopsneaker.Model.NewsModel;
import com.nghia.shopsneaker.R;
import com.nghia.shopsneaker.View.ChitietSanPhamActivity;
import com.nghia.shopsneaker.View.GioHangActivity;
import com.nghia.shopsneaker.View.LienHeActivity;
import com.nghia.shopsneaker.View.Store.StoreNewsActivity;
import com.nghia.shopsneaker.View.ThongTinActivity;

import java.util.ArrayList;

public class FragMent_Home  extends Fragment
 implements  View.OnClickListener,NavigationView.OnNavigationItemSelectedListener{
    View view;
    private ViewPager viewFlipper;
    private BannerController bannerController;
    private INewsController newsController;
    private AppCompatSpinner appCompatSpinner;
    private ArrayList<String> arrayList;
    private ArrayList<NewsModel> newsModelArrayList;
    private GridView gv;
    private NewsAdapter adapter;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private PreferanceManager preferanceManager;
    private ImageView giohang;
    private EditText editimkiem;
    private Button btntimkiem;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_home,container,false);
        InitWidget();
        Init();
        return  view;
    }

    private void Init() {
        preferanceManager=new PreferanceManager(getContext());
        navigationView.setNavigationItemSelectedListener(this);
        toggle=new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,R.string.Open,R.string.Close);
        toggle.syncState();
        arrayList=new ArrayList<>();
        newsModelArrayList=new ArrayList<>();
        bannerController=new BannerController(getContext());
        newsController=new INewsController(getContext());
        bannerController.HandleBanner(viewFlipper);
        bannerController.HandleReadCatgoreis(arrayList);

        newsController.HandleReadDataNewsAll(newsModelArrayList);
        adapter=new NewsAdapter(getContext(),newsModelArrayList);
        gv.setAdapter(adapter);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
                gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent=new Intent(getContext(), ChitietSanPhamActivity.class);
                        intent.putExtra("NEWS",newsModelArrayList.get(position));
                        startActivity(intent);
                    }
                });
            }
        },1500);
        giohang.setOnClickListener(this);
        btntimkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=editimkiem.getText().toString().trim();
                if(name.length()>0){
                 if(newsModelArrayList.size()>0){
                     newsModelArrayList.clear();

                 }
                    newsController.HandleReadDataNewsAll(newsModelArrayList,name);
                    adapter=new NewsAdapter(getContext(),newsModelArrayList);
                    gv.setAdapter(adapter);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent=new Intent(getContext(), ChitietSanPhamActivity.class);
                                    intent.putExtra("NEWS",newsModelArrayList.get(position));
                                    startActivity(intent);
                                }
                            });
                        }
                    },1500);

                }else{
                    Toast.makeText(getContext(), "Hẫy nhập tên càn tìm kiếm", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void InitWidget() {
        btntimkiem=view.findViewById(R.id.btntimkiem);
        editimkiem=view.findViewById(R.id.edittimkiem);
        giohang=view.findViewById(R.id.giohang);
        navigationView=view.findViewById(R.id.navigationview);
        toolbar=view.findViewById(R.id.toolbar);
         drawerLayout=view.findViewById(R.id.drawablelayout);
        gv=view.findViewById(R.id.gv);
        viewFlipper=view.findViewById(R.id.viewfilip);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.giohang:
                startActivity(new Intent(getContext(), GioHangActivity.class));break;
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nike: preferanceManager.PutTypeShoe("NIKE");
              startActivity(new Intent(getContext(),StoreNewsActivity.class));break;
            case R.id.vans: preferanceManager.PutTypeShoe("VANS");
                startActivity(new Intent(getContext(),StoreNewsActivity.class));break;
            case R.id.adidas: preferanceManager.PutTypeShoe("ADIDAS");
            startActivity(new Intent(getContext(),StoreNewsActivity.class));break;
            case R.id.lienhe: preferanceManager.PutTypeShoe("ADIDAS");
                startActivity(new Intent(getContext(), LienHeActivity.class));break;
            case R.id.thongtin: preferanceManager.PutTypeShoe("ADIDAS");
                startActivity(new Intent(getContext(), ThongTinActivity.class));break;


        }
        drawerLayout.closeDrawers();
        return true;
    }
}

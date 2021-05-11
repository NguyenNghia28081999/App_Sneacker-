package com.nghia.shopsneaker.View;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nghia.shopsneaker.R;

public class ThongTinActivity  extends AppCompatActivity
 implements OnMapReadyCallback {
    private GoogleMap map;
    private MapFragment mapFragment;
    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diachi);
        toolbar=findViewById(R.id.toolbar);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
setSupportActionBar(toolbar);
getSupportActionBar().setDisplayHomeAsUpEnabled(true);
getSupportActionBar().setTitle("Back");
toolbar.setNavigationOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        finish();
    }
});
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map=googleMap;
        LatLng latLng=new LatLng(10.9757486,106.6728942);

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,17));
        map.addMarker(new MarkerOptions()
                .title("Shop Snaker")
                .snippet("Chào mừng bạn đến với shop chúng tôi")
                .position(latLng));
    }
}

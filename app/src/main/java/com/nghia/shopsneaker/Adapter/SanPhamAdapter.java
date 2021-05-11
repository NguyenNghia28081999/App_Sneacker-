package com.nghia.shopsneaker.Adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nghia.shopsneaker.Model.NewsModel;
import com.nghia.shopsneaker.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SanPhamAdapter extends BaseAdapter {
    private ArrayList<NewsModel> arrayList;
    private Context context;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    public SanPhamAdapter(Context context, ArrayList<NewsModel> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public  class  ViewHodler{
        TextView txtsoluong,txttensp,
        txtngay,txtgiatien,txttype;
        ImageView hinhanh;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHodler viewHodler;
        firebaseStorage=FirebaseStorage.getInstance("gs://shopsneaker-e9ead.appspot.com/");
        storageReference=firebaseStorage.getReference();
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.dong_news_food_sp,null);
            viewHodler=new ViewHodler();
            viewHodler.hinhanh=convertView.findViewById(R.id.hinhanh);
            viewHodler.txtngay=convertView.findViewById(R.id.txtngay);
            viewHodler.txtgiatien=convertView.findViewById(R.id.txtgiatien);
            viewHodler.txttensp=convertView.findViewById(R.id.txtten);
            viewHodler.txttype=convertView.findViewById(R.id.txttype);
            viewHodler.txtsoluong=convertView.findViewById(R.id.txtsoluong);
            convertView.setTag(viewHodler);
        }else{
            viewHodler= (ViewHodler) convertView.getTag();
        }
          Log.d("CHECKED",arrayList.get(position).getHinhanh());
         storageReference.child("Store").child(""+arrayList.get(position).getHinhanh())
                 .getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
             @Override
             public void onSuccess(Uri uri) {

                 Picasso.with(context).load(uri).into(viewHodler.hinhanh);
             }
         });
        viewHodler.txttensp.setText(arrayList.get(position).getTen());
        viewHodler.txtngay.setText(arrayList.get(position).getNgay());
        viewHodler.txtgiatien.setText("Giá tiền : "+arrayList.get(position).getGiatien()+"đ");
        viewHodler.txttype.setText("Loại : "+arrayList.get(position).getType());
        viewHodler.txtsoluong.setText("Số Lượng : "+arrayList.get(position).getSoluong());


        return convertView;
    }
}

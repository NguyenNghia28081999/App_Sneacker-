package com.nghia.shopsneaker.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nghia.shopsneaker.Controller.INewsController;
import com.nghia.shopsneaker.Model.NewsModel;
import com.nghia.shopsneaker.R;
import com.nghia.shopsneaker.View.GioHangActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GiohangAdapter extends BaseAdapter {
    private ArrayList<NewsModel> arrayList;
    private Context context;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private INewsController newsController;
    long tongtien=0,giatien=0,tongtiens=0;

    private int k=1;
    public GiohangAdapter(Context context, ArrayList<NewsModel> arrayList){
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
        TextView txtten,txtsoluong,txtgiatien;
        Button btnthem,btntru;
        ImageView imagedelete,hinhanh;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHodler viewHodler;

        newsController=new INewsController(context);
        firebaseStorage=FirebaseStorage.getInstance("gs://shopsneaker-e9ead.appspot.com/");
        storageReference=firebaseStorage.getReference();
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.dong_giohang,null);
            viewHodler=new ViewHodler();
            viewHodler.hinhanh=convertView.findViewById(R.id.hinhanh);
            viewHodler.txtgiatien=convertView.findViewById(R.id.txtgiatien);
            viewHodler.txtten=convertView.findViewById(R.id.txtten);
            viewHodler.txtsoluong=convertView.findViewById(R.id.txtsoluong);
            viewHodler.imagedelete=convertView.findViewById(R.id.remove);
            viewHodler.btnthem=convertView.findViewById(R.id.btnthem);
            viewHodler.btntru=convertView.findViewById(R.id.btntru);
            convertView.setTag(viewHodler);
        }else{
            viewHodler= (ViewHodler) convertView.getTag();
        }

         storageReference.child("Store").child(""+arrayList.get(position).getHinhanh())
                 .getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
             @Override
             public void onSuccess(Uri uri) {

                 Picasso.with(context).load(uri).into(viewHodler.hinhanh);
             }
         });
        viewHodler.txtten.setText(arrayList.get(position).getTen());
        viewHodler.txtgiatien.setText(arrayList.get(position).getGiatien()+"đ");
        viewHodler.txtsoluong.setText(arrayList.get(position).getSoluong()+"");





       viewHodler.btnthem.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
             k=arrayList.get(position).getSoluong();
               k++;
               newsController.HandleUpdateSoLuong(arrayList.get(position).getID(),k);
               GioHangActivity.countDownTimer.start();

           }
       });
        viewHodler.btntru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                k=arrayList.get(position).getSoluong();
                if(k>1){

                    k--;
                    newsController.HandleUpdateSoLuong(arrayList.get(position).getID(),k);
                    GioHangActivity.countDownTimer.start();
                }

            }
        });
        viewHodler.imagedelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setMessage("Bạn có chắc xóa món hàng này không !");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        newsController.HandleDelete(arrayList.get(position).getID());
                        GioHangActivity.countDownTimer.start();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
        return convertView;
    }
}

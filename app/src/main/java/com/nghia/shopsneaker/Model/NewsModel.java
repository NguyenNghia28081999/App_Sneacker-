package com.nghia.shopsneaker.Model;

import android.content.Context;
import androidx.annotation.NonNull;
import com.nghia.shopsneaker.Controller.INewsModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;

public class NewsModel  implements  Serializable {
    private String ID;
    private String ten;
    private  String diachi;
    private String ngay;
    private String type;
    private String hinhanh;
    private String mota;
    private String size;
    private int soluong;
    private long giatien;
    private long tongtien;
    private FirebaseFirestore db;
    private FirebaseAuth firebaseAuth;
    private Context context;

    private INewsModel callback;
    public  NewsModel(Context context, INewsModel callback){
        this.callback=callback;
        this.context=context;
        db=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
    }
    public NewsModel(){

    }

    public NewsModel(String ID,String ten, String hinhanh, int soluong, long giatien,long tongtien) {
        this.ID = ID;
        this.ten = ten;
        this.soluong = soluong;
        this.giatien = giatien;
        this.hinhanh=hinhanh;
        this.tongtien=tongtien;
    }
    public NewsModel(String ten, String hinhanh,int soluong, long giatien,long tongtien,String Size) {
        this.ten = ten;
        this.soluong = soluong;
        this.giatien = giatien;
        this.hinhanh=hinhanh;
        this.tongtien=tongtien;
    }

    public NewsModel(String ten, String diachi, String ngay, String type, String hinhanh, String mota, int soluong, long giatien,String size) {
        this.ten = ten;
        this.diachi = diachi;
        this.type = type;
        this.soluong = soluong;
        this.giatien = giatien;
        this.ngay=ngay;
        this.mota=mota;
        this.hinhanh=hinhanh;
        this.size=size;


    }
    public NewsModel(String ID,String ten, String diachi,String ngay, String type, String hinhanh,String mota,int soluong, long giatien) {
        this.ID=ID;
        this.ten = ten;

        this.diachi = diachi;
        this.type = type;
        this.soluong = soluong;
        this.giatien = giatien;
        this.ngay=ngay;
        this.mota=mota;
        this.hinhanh=hinhanh;

    }
    public  void HandlegetDataNews(String type, final ArrayList<NewsModel> arrayList){
        db.collection("News")
                .document("Store")
                .collection("ALL")
                .whereEqualTo("type",type)
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                   for(DocumentSnapshot d:queryDocumentSnapshots){
                       NewsModel newsModel=d.toObject(NewsModel.class);
                       arrayList.add(new NewsModel(newsModel.getTen(),
                               newsModel.getDiachi(),newsModel.getNgay(),newsModel.getType(),
                               newsModel.getHinhanh(),newsModel.getMota(),newsModel.getSoluong(),
                               newsModel.getGiatien(),newsModel.getSize()));

                   }
            }
        });
    }
    public  void HandleInsertGiohang(String ten,String hinhanh,int soluong,long giatien,long tongtien,String Size){

        NewsModel newsModel=new NewsModel(ten,hinhanh,soluong,giatien,tongtien,Size);
        db.collection("GioHang")
                .document(firebaseAuth.getCurrentUser().getUid())
                .collection("ChiTiet")
                .add(newsModel).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {

            }
        });
    }
    public  void HandleUpdateSoLuong(String ID,int soluong){
            db.collection("GioHang")
                    .document(firebaseAuth.getCurrentUser().getUid())
                    .collection("ChiTiet")
                    .document(ID).update("soluong",soluong).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {

                }
            });
    }
    public  void HandleDelete(String ID){
        db.collection("GioHang")
                .document(firebaseAuth.getCurrentUser().getUid())
                .collection("ChiTiet")
                .document(ID).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }
    public  void HandleGetDataGioHang(final ArrayList<NewsModel> arrayList){
        db.collection("GioHang")
                .document(firebaseAuth.getCurrentUser().getUid())
                .collection("ChiTiet")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                   for(DocumentSnapshot d : queryDocumentSnapshots){
                       NewsModel newsModel=d.toObject(NewsModel.class);
                       callback.getDataGioHang(d.getId(),newsModel.getTen(),
                               newsModel.getHinhanh(),newsModel.getSoluong(),newsModel.getGiatien(),newsModel.getTongtien());

                   }
             }
        });

    }

    public  void HandlegetDataNewsALL( final ArrayList<NewsModel> arrayList){
        db.collection("News")
                .document("Store")
                .collection("ALL")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot d:queryDocumentSnapshots){
                    NewsModel newsModel=d.toObject(NewsModel.class);
                    arrayList.add(new NewsModel(newsModel.getTen(),
                            newsModel.getDiachi(),newsModel.getNgay(),newsModel.getType(),
                            newsModel.getHinhanh(),newsModel.getMota(),newsModel.getSoluong(),
                            newsModel.getGiatien(),newsModel.getSize()));

                }
            }
        });
    }
    public  void HandlegetDataNewsALL( final ArrayList<NewsModel> arrayList,String name){
        db.collection("News")
                .document("Store")
                .collection("ALL")
                .whereEqualTo("ten",name)
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot d:queryDocumentSnapshots){
                    NewsModel newsModel=d.toObject(NewsModel.class);
                    arrayList.add(new NewsModel(newsModel.getTen(),
                            newsModel.getDiachi(),newsModel.getNgay(),newsModel.getType(),
                            newsModel.getHinhanh(),newsModel.getMota(),newsModel.getSoluong(),
                            newsModel.getGiatien(),newsModel.getSize()));

                }
            }
        });
    }
    public  void HandlegetDataNewsStore(String email, final ArrayList<NewsModel> arrayList){
        db.collection("News")
                .document("Store")
                .collection("ALL")
                .whereEqualTo("email",email)
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot d:queryDocumentSnapshots){
                    NewsModel newsModel=d.toObject(NewsModel.class);
                    arrayList.add(new NewsModel(newsModel.getTen(),
                            newsModel.getDiachi(),newsModel.getNgay(),newsModel.getType(),
                            newsModel.getHinhanh(),newsModel.getMota(),newsModel.getSoluong(),
                            newsModel.getGiatien(),newsModel.getSize()));

                }
            }
        });
    }
    public  void HandlegetDataNewsManager( ){
        db.collection("News")
                .document("Store")
                .collection("ALL")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot d:queryDocumentSnapshots){
                    NewsModel newsModel=d.toObject(NewsModel.class);
                    callback.getDataSanPham(d.getId(),newsModel.getTen(),
                            newsModel.getDiachi(),newsModel.getNgay(),newsModel.getType(),
                            newsModel.getHinhanh(),newsModel.getMota(),newsModel.getSoluong(),
                            newsModel.getGiatien());


                }
            }
        });
    }
    public  void HandleCapNhat(String ID,String ten,String diachi,long giatien,int  soluong,String mota){

        db.collection("News")
                   .document("Store")
                   .collection("ALL")
                   .document(ID).update("ten",ten,
                       "diachi",diachi,
                "giatien",giatien,
                "soluong",soluong,
                "mota",mota)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });

    }
    public  void HandleXoa(String ID){

        db.collection("News")
                .document("Store")
                .collection("ALL")
                .document(ID).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });

    }

    public  void HandlegetDataFind(String name, final ArrayList<NewsModel> arrayList){
        db.collection("News")
                .document("Store")
                .collection("ALL")
                .whereLessThanOrEqualTo("ten",name)
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot d:queryDocumentSnapshots){
                    NewsModel newsModel=d.toObject(NewsModel.class);

                    arrayList.add(new NewsModel(newsModel.getTen(),
                            newsModel.getDiachi(),newsModel.getNgay(),newsModel.getType(),
                            newsModel.getHinhanh(),newsModel.getMota(),newsModel.getSoluong(),
                            newsModel.getGiatien(),newsModel.getSize()));

                }
            }
        });
    }


    public  void HandleCreateNews(String ten, String diachi, String ngay, String type, String hinhanh,String mota,int soluong, long giatien){



        NewsModel newsModel=new NewsModel(ten,diachi,ngay,type,hinhanh,mota,soluong,giatien,"35,36,37,38,39,40,41,42");



        db.collection("News")
                .document("Store")
                .collection("ALL")
                .add(newsModel).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                   if(task.isSuccessful()){
                       callback.OnSucess();
                   }else{
                       callback.OnFail();
                   }
            }
        });

    }

    public String getTen() {
        return ten;
    }

    public String getDiachi() {
        return diachi;
    }

    public String getNgay() {
        return ngay;
    }

    public String getType() {
        return type;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public int getSoluong() {
        return soluong;
    }

    public long getGiatien() {
        return giatien;
    }

    public String getMota() {
        return mota;
    }

    public String getSize() {
        return size;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public long getTongtien() {
        return tongtien;
    }

    public void setTongtien(long tongtien) {
        this.tongtien = tongtien;
    }
}

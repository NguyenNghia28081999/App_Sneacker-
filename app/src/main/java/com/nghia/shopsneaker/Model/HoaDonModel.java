package com.nghia.shopsneaker.Model;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.nghia.shopsneaker.Controller.IHoaDon;

public class HoaDonModel {
    private FirebaseFirestore db;
    private String ten;
    private String diachi;
    private String sdt;
    private long tongtien;
    private  String uid;
    private String ID;
    private String ngay;
    private String size;
    private String mahoadon;
    private IHoaDon callback;
    public  HoaDonModel(IHoaDon callback){
        this.callback=callback;
        db=FirebaseFirestore.getInstance();

    }
    public HoaDonModel(){

    }

    public HoaDonModel(String ten, String diachi, String sdt, long tongtien, String uid, String ID,String ngay) {
        this.ten = ten;
        this.diachi = diachi;
        this.sdt = sdt;
        this.tongtien = tongtien;
        this.uid = uid;
        this.ID = ID;
        this.ngay = ngay;
    }

    public HoaDonModel(String ten, String diachi, String sdt, long tongtien, String uid, String id,String ngay,String mahoadon) {
        this.ten = ten;
        this.diachi = diachi;
        this.sdt = sdt;
        this.tongtien = tongtien;
        this.uid = uid;
        this.ID=id;
        this.ngay=ngay;
        this.mahoadon=mahoadon;
    }

    public  void HandleSendThanhToan(String ten, String diachi, String sdt, long tongtien, String uid,String ngay,String mahoadon){
        HoaDonModel hoaDonModel=new HoaDonModel(ten,diachi,sdt,tongtien,uid, ngay,mahoadon);
        db.collection("BILL")
                .document("USER")
                .collection("ALL")
                .add(hoaDonModel).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {

            }
        });
    }
    public  void HandlegetDataHoaDon(){
        db.collection("BILL")
                .document("USER")
                .collection("ALL")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot d :queryDocumentSnapshots){
                    HoaDonModel hoaDonModel=d.toObject(HoaDonModel.class);
                    callback.getDataHoaDon(hoaDonModel.getTen(),hoaDonModel.getDiachi(),hoaDonModel.getSdt(),
                            hoaDonModel.getTongtien(),hoaDonModel.getUid(),d.getId(),hoaDonModel.getNgay(),hoaDonModel.getMahoadon());
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

    public String getSdt() {
        return sdt;
    }

    public long getTongtien() {
        return tongtien;
    }

    public String getUid() {
        return uid;
    }

    public String getNgay() {
        return ngay;
    }

    public String getSize() {
        return size;
    }

    public String getMahoadon() {
        return mahoadon;
    }
}

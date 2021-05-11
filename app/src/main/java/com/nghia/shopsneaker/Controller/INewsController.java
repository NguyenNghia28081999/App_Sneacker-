package com.nghia.shopsneaker.Controller;

import android.content.Context;
import android.widget.Toast;
import com.nghia.shopsneaker.Model.NewsModel;

import java.util.ArrayList;

public class INewsController implements INewsModel {
    private NewsModel newsModel;
    private Context context;
    private NewView callback;
    public INewsController(Context context){
        this.context=context;
        newsModel=new NewsModel(context,this);
    }
    public INewsController(Context context,NewView callback){
        this.context=context;
        this.callback=callback;
        newsModel=new NewsModel(context,this);
    }
    public  void HandleCreateNews(String ten,String diachi,String ngay,String type,String hinhanh,String mota,int soluong,long giatien){
        newsModel.HandleCreateNews(ten,diachi,ngay,type,hinhanh,mota,soluong,giatien);
    }
    public  void HandleReadDataNews(String type, ArrayList<NewsModel> arrayList){
        newsModel.HandlegetDataNews(type,arrayList);
    }
    public  void HandlegetDataFind(String name,ArrayList<NewsModel> arrayList){
        newsModel.HandlegetDataFind(name,arrayList);
    }
    public  void HandlegetDataNewsManager(){
        newsModel.HandlegetDataNewsManager();
    }
    public  void HandleCapNhat(String ID,String ten,String diachi,long giatien,int soluong,String mota){
        newsModel.HandleCapNhat(ID,ten,diachi,giatien,soluong,mota);
    }
    public  void HandleXoa(String ID){
        newsModel.HandleXoa(ID);
    }
    public  void HandleReadDataNewsStore(String email, ArrayList<NewsModel> arrayList){
        newsModel.HandlegetDataNewsStore(email,arrayList);
    }
    public  void HandleInsertGiohang(String ten,String hinhanh,int soluong,long giatien,long tongtien,String Size){
        newsModel.HandleInsertGiohang(ten,hinhanh,soluong,giatien,tongtien,Size);
    }
    public  void HandlegetDataGioHang(ArrayList<NewsModel> arrayList){
        newsModel.HandleGetDataGioHang(arrayList);
    }
    public  void HandleUpdateSoLuong(String ID ,int soluong){
        newsModel.HandleUpdateSoLuong(ID,soluong);
    }
    public  void  HandleDelete(String ID){
        newsModel.HandleDelete(ID);
    }


    @Override
    public void OnSucess() {
        Toast.makeText(context,"Thêm thành công",Toast.LENGTH_LONG).show();
    }

    @Override
    public void OnFail() {
        Toast.makeText(context,"Thêm thất bại",Toast.LENGTH_LONG).show();
    }

    @Override
    public void getDataGioHang(String id, String ten, String hinhanh, int soluong, long giatien, long tongtien) {
        callback.getDataGiohang(id,ten,hinhanh,soluong,giatien,tongtien);
    }

    @Override
    public void getDataSanPham(String id, String ten, String diachi, String ngay, String type, String hinhanh, String mota, int soluong, long giatien) {
        callback.getDataSanPham(id,ten,diachi,ngay,type,hinhanh,mota,soluong,giatien);
    }

    public void HandleReadDataNewsAll(ArrayList<NewsModel> newsModelArrayList) {
        newsModel.HandlegetDataNewsALL(newsModelArrayList);
    }
    public void HandleReadDataNewsAll(ArrayList<NewsModel> newsModelArrayList,String name) {
        newsModel.HandlegetDataNewsALL(newsModelArrayList,name);
    }
}

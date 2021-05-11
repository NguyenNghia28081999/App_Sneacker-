package com.nghia.shopsneaker.Controller;

public interface NewView {
    void getDataGiohang(String id, String ten, String hinhanh, int soluong, long giatien, long tongtien);

    void getDataSanPham(String id, String ten, String diachi, String ngay, String type, String hinhanh, String mota, int soluong, long giatien);
}

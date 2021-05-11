package com.nghia.shopsneaker.Controller;

import com.nghia.shopsneaker.Model.HoaDonModel;

public class HoaDonController implements IHoaDon{
    private HoaDonModel hoaDonModel;
    private HoaDonView callback;

    public HoaDonController(HoaDonView callback) {
        this.callback = callback;
        hoaDonModel=new HoaDonModel(this);
    }
    public  void
    HandleSendHoaDon(String ten,String diachi,String sdt,long tongtien,String uid,String ngay,String mahoadon){
        hoaDonModel.HandleSendThanhToan(ten,diachi,sdt,tongtien,uid,ngay,mahoadon);
    }
    public  void HandleGetDataHoaDon(){
        hoaDonModel.HandlegetDataHoaDon();
    }

    @Override
    public void getDataHoaDon(String ten, String diachi, String sdt, long tongtien, String uid, String id,String ngay,String mahoadon) {
        callback.getDataHoaDon(ten,diachi,sdt,tongtien,uid,id,ngay,mahoadon);
    }
}

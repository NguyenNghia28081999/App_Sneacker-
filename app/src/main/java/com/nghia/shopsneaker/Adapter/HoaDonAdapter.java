package com.nghia.shopsneaker.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.nghia.shopsneaker.Model.HoaDonModel;
import com.nghia.shopsneaker.R;

import java.util.ArrayList;

public class HoaDonAdapter  extends BaseAdapter {
    private Context context;
    private ArrayList<HoaDonModel> arrayList;

    public HoaDonAdapter(Context context, ArrayList<HoaDonModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
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
        TextView txttongtien,
         txtdiachi,txtten,txtsdt,txtsize,txtmahoadon;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.dong_hoadon,null);
            viewHodler=new ViewHodler();
            viewHodler.txttongtien=convertView.findViewById(R.id.txttongtien);
            viewHodler.txtmahoadon=convertView.findViewById(R.id.txtmahoadon);
            viewHodler.txtten=convertView.findViewById(R.id.txtten);
            viewHodler.txtsdt=convertView.findViewById(R.id.txtsdt);
            viewHodler.txtdiachi=convertView.findViewById(R.id.txtdiachi);
            viewHodler.txtsize=convertView.findViewById(R.id.txtsize);
            convertView.setTag(viewHodler);
        }else{
            viewHodler= (ViewHodler) convertView.getTag();
        }
        viewHodler.txtdiachi.setText("Địa chỉ : "+arrayList.get(position).getDiachi());
        viewHodler.txtten.setText("Họ tên : "+arrayList.get(position).getTen());
        viewHodler.txttongtien.setText("Tổng tiền : "+arrayList.get(position).getTongtien());
        viewHodler.txtsdt.setText("Số điện thoại : "+arrayList.get(position).getSdt());
        viewHodler.txtsize.setText("Size : "+arrayList.get(position).getSize());
        viewHodler.txtmahoadon.setText("Mã GD : "+arrayList.get(position).getMahoadon());
        return convertView;
    }
}

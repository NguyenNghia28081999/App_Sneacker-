package com.nghia.shopsneaker.View.FragMent;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.google.firebase.auth.FirebaseAuth;
import com.nghia.shopsneaker.Config.PreferanceManager;
import com.nghia.shopsneaker.Controller.UserController;
import com.nghia.shopsneaker.R;
import com.nghia.shopsneaker.View.HomeActivity;
import com.nghia.shopsneaker.View.HomeStoreActivity;
import com.nghia.shopsneaker.View.QuanLySanPhamActivity;
import de.hdodenhof.circleimageview.CircleImageView;

public class FragMent_ProFile extends Fragment {
    View view;
    private PreferanceManager preferanceManager;
    private Button btncapnhat;
    private EditText edithoten,editsdt,editdiachi;
    private TextView txtemail,txtmatkhau;
    private UserController userController;
    private CardView c1;
    private TextView txtdangxuat;
    private CircleImageView circleImageView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_profile,container,false);
        IniWidget();
        Init();

        return  view;
    }

    private void Init() {
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            if(FirebaseAuth.getInstance().getCurrentUser().isEmailVerified()){
                preferanceManager=new PreferanceManager(getContext());
                userController=new UserController(getContext());
                userController.HandleUpdateProfile(editsdt,editdiachi,txtemail,edithoten);
            }else{
                c1.setVisibility(View.GONE);
                btncapnhat.setVisibility(View.GONE);
                txtdangxuat.setVisibility(View.GONE);
                circleImageView.setVisibility(View.GONE);
            }
        }

        txtdangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), HomeActivity.class));
            }
        });

    }

    private void IniWidget() {
        circleImageView=view.findViewById(R.id.circleview);
        txtdangxuat=view.findViewById(R.id.txtdangxuat);
        txtemail=view.findViewById(R.id.txtemail);
        txtmatkhau=view.findViewById(R.id.txtmatkhau);
        edithoten=view.findViewById(R.id.edithoten);
        editsdt=view.findViewById(R.id.editsdt);
        editdiachi=view.findViewById(R.id.editdiachi);
        c1=view.findViewById(R.id.c1);
        btncapnhat=view.findViewById(R.id.btncapnhat);
    }
}

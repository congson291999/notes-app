package com.example.ghich;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ThemActivity extends AppCompatActivity {
    ArrayList<GhiChu> arrayGhiChu;
    Button btnThem;
    EditText edtTieuDe;
    EditText edtTag;
    EditText edtNoiDung;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);
        btnThem=(Button)findViewById(R.id.btnThem);
        edtTieuDe=(EditText) findViewById(R.id.edtTieuDe);
        edtNoiDung=(EditText)findViewById(R.id.edtNoiDung);
        edtTag=(EditText)findViewById(R.id.edtTag);
        arrayGhiChu= new ArrayList<>();

        sharedPreferences=getSharedPreferences("dataghichu", Context.MODE_PRIVATE);
        int size = sharedPreferences.getInt("Status_size", 0);

        for(int i=0;i<size;i++)
        {
            String tieude=sharedPreferences.getString("Status_tieude" + i, null);
            String noidung=sharedPreferences.getString("Status_noidung"+i,null);
            String tag=sharedPreferences.getString("Status_tag" + i, null);
            String t=sharedPreferences.getString("Status_thoigian" + i, null);
            arrayGhiChu.add(new GhiChu(tieude,noidung ,t,tag));
        }



        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd hhmmss");
                dateFormatter.setLenient(false);
                Date today = new Date();
                String s = dateFormatter.format(today);
                String tieude=edtTieuDe.getText().toString();
                String tag=edtTag.getText().toString();
                String noidung=edtNoiDung.getText().toString();
                arrayGhiChu.add(new GhiChu(tieude,noidung,today.toString(),tag));
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putInt("Status_size", arrayGhiChu.size());
                for(int i=0;i<arrayGhiChu.size();i++)
                {
                    editor.remove("Status_tieude" + i);
                    editor.remove("Status_tag" + i);
                    editor.remove("Status_thoigian"+i);
                    editor.remove("Status_noidung"+i);
                    editor.putString("Status_tieude" + i, arrayGhiChu.get(i).getTieuDe());
                    editor.putString("Status_noidung" + i,arrayGhiChu.get(i).getNoiDung());
                    editor.putString("Status_tag" + i, arrayGhiChu.get(i).getTag());
                    editor.putString("Status_thoigian" + i, arrayGhiChu.get(i).getThoiGianChinhSua());
                }
                editor.commit();
                Intent intent=new Intent(ThemActivity.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(ThemActivity.this,"Thêm thành công",Toast.LENGTH_LONG).show();

            }
        });
    }
}

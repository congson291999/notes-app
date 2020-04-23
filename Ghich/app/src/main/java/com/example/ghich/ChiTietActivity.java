package com.example.ghich;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ChiTietActivity extends AppCompatActivity {
    ArrayList<GhiChu> arrayGhiChu;
    EditText edtTieuDe,edtNoiDung;
    EditText edtTag;
    TextView tvThoiGian;
    SharedPreferences sharedPreferences;
    Button btnSua;
    Button btnXoa;
    int vitri=-1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet);
        arrayGhiChu= new ArrayList<>();
        btnSua=(Button)findViewById(R.id.btnSua);
        btnXoa=(Button)findViewById(R.id.btnXoa);
        edtTieuDe=(EditText)findViewById(R.id.editTextTieuDe) ;
        edtTag=(EditText)findViewById(R.id.editTextTag);
        edtNoiDung=(EditText)findViewById(R.id.editTextNoiDung);
        tvThoiGian=(TextView)findViewById(R.id.textViewThoiGianChinhSua);
        final SharedPreferences sharedPreferences = getSharedPreferences("dataghichu", Context.MODE_PRIVATE);
        int size = sharedPreferences.getInt("Status_size", 0);
        for(int i=0;i<size;i++)
        {
            String tieude=sharedPreferences.getString("Status_tieude" + i, null);
            String tag=sharedPreferences.getString("Status_tag" + i, null);
            String t=sharedPreferences.getString("Status_thoigian" + i, null);
            String noidung=sharedPreferences.getString("Status_noidung" + i,null);
            arrayGhiChu.add(new GhiChu(tieude,noidung, t,tag));
        }
        setDataByBundle();
        btnSua.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                DateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd hhmmss");
                dateFormatter.setLenient(false);
                Date today = new Date();
                String s = dateFormatter.format(today);
                arrayGhiChu.get(vitri).setTieuDe(edtTieuDe.getText().toString());
                arrayGhiChu.get(vitri).setNoiDung(edtNoiDung.getText().toString());
                arrayGhiChu.get(vitri).setTag(edtTag.getText().toString());
                arrayGhiChu.get(vitri).setThoiGianChinhSua(today.toString());
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putInt("Status_size", arrayGhiChu.size());
                for(int i=0;i<arrayGhiChu.size();i++)
                {
                    editor.remove("Status_tieude" + i);
                    editor.remove("Status_noidung"+i);
                    editor.remove("Status_tag" + i);
                    editor.remove("Status_thoigian"+i);
                    editor.putString("Status_tieude" + i, arrayGhiChu.get(i).getTieuDe());
                    editor.putString("Status_noidung"+i,arrayGhiChu.get(i).getNoiDung());
                    editor.putString("Status_tag" + i, arrayGhiChu.get(i).getTag());
                    editor.putString("Status_thoigian" + i, arrayGhiChu.get(i).getThoiGianChinhSua());
                }
                editor.commit();
                Intent intent=new Intent(ChiTietActivity.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(ChiTietActivity.this,"Sửa thành công",Toast.LENGTH_LONG).show();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                arrayGhiChu.remove(vitri);
                vitri=-1;
                edtTieuDe.setText("");
                edtTag.setText("");
                tvThoiGian.setText("");
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putInt("Status_size", arrayGhiChu.size());
                for(int i=0;i<arrayGhiChu.size();i++)
                {
                    editor.remove("Status_tieude" + i);
                    editor.remove("Status_tag" + i);
                    editor.remove("Status_thoigian"+i);
                    editor.remove("Status_noidung"+i);
                    editor.putString("Status_tieude" + i, arrayGhiChu.get(i).getTieuDe());
                    editor.putString("Status_noidung"+i,arrayGhiChu.get(i).getNoiDung());
                    editor.putString("Status_tag" + i, arrayGhiChu.get(i).getTag());
                    editor.putString("Status_thoigian" + i, arrayGhiChu.get(i).getThoiGianChinhSua());
                }
                editor.commit();
                Intent intent=new Intent(ChiTietActivity.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(ChiTietActivity.this,"Xóa thành công",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void setDataByBundle(){
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("bundle");
        vitri=bundle.getInt("index");
        edtTieuDe.setText(arrayGhiChu.get(vitri).getTieuDe());
        edtTag.setText(arrayGhiChu.get(vitri).getTag());
        edtNoiDung.setText(arrayGhiChu.get(vitri).getNoiDung());
        tvThoiGian.setText(arrayGhiChu.get(vitri).getThoiGianChinhSua());
    }
}

package com.example.ghich;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Button;
import android.widget.SearchView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    ListView lvghichu;
    ArrayList<GhiChu> arrayGhiChu;
    GhiChuAdapter adapter;
    SharedPreferences sharedPreferences;
    ImageButton imgbtnThem;
    SearchView search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lvghichu=(ListView)findViewById(R.id.listviewGhiChu);
        arrayGhiChu= new ArrayList<>();
        imgbtnThem=(ImageButton)findViewById(R.id.imgbtnThem);
        search=(SearchView)findViewById(R.id.search);

        sharedPreferences=getSharedPreferences("dataghichu", Context.MODE_PRIVATE);
        int size = sharedPreferences.getInt("Status_size", 0);

        for(int i=0;i<size;i++)
        {
            String tieude=sharedPreferences.getString("Status_tieude" + i, null);
            String tag=sharedPreferences.getString("Status_tag" + i, null);
            String t=sharedPreferences.getString("Status_thoigian" + i, null);
            String noidung=sharedPreferences.getString("Status_thoigian"+i,null);
            arrayGhiChu.add(new GhiChu(tieude,noidung, t,tag));
        }
        imgbtnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ThemActivity.class);
                startActivity(intent);
            }
        });

        lvghichu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ByBundle(i);
            }
        });
        adapter =new GhiChuAdapter(this,R.layout.dong_ghi_chu,arrayGhiChu);
        lvghichu.setAdapter(adapter);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);

                return false;
            }
        });
    }
    public void ByBundle(int vitri){
        Intent intent=new Intent(MainActivity.this,ChiTietActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("index",vitri);
        intent.putExtra("bundle",bundle);
        startActivity(intent);
    }


}

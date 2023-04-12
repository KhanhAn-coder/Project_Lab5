package com.example.a51900475_lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button btnRemoveAll, btnRemoveSelected;
    ArrayList<Phones> phoneList = new ArrayList<>();
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        btnRemoveAll = findViewById(R.id.btnRemoveAll);
        btnRemoveSelected = findViewById(R.id.btnRemoveSelected);

        phoneList.add(new Phones(R.drawable.ic_baseline_phone_iphone_24,"Apple",false));
        phoneList.add(new Phones(R.drawable.ic_baseline_phone_iphone_24,"Samsung",false));
        phoneList.add(new Phones(R.drawable.ic_baseline_phone_iphone_24,"Nokia",false));
        phoneList.add(new Phones(R.drawable.ic_baseline_phone_iphone_24,"Oppo",false));

        myAdapter = new MyAdapter(phoneList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(myAdapter);

        btnRemoveSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = phoneList.size()-1; i>= 0;i--){
                    if(phoneList.get(i).isChecked()==true){
                        phoneList.remove(i);
                        myAdapter.notifyItemRemoved(i);
                    }
                }

            }
        });

        btnRemoveAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(phoneList.size() == 0){
                    Toast.makeText(MainActivity.this, "Empty List", Toast.LENGTH_SHORT).show();
                }else {
                    phoneList.clear();
                    myAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        int count = 0;
        switch (id){
            case R.id.deleteAll:
                if (phoneList.size()==0){
                    Toast.makeText(MainActivity.this, "Empty List", Toast.LENGTH_SHORT).show();
                }else {
                    phoneList.clear();
                    myAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.check:
                if(item.getTitle().equals("Check All")){
                    for (int i =0;i<=phoneList.size()-1;i++){
                        phoneList.get(i).setCheck(true);
                        myAdapter.notifyItemChanged(i);
                    }
                    item.setTitle("Uncheck All");
                }else if (item.getTitle().equals("Uncheck All")) {
                    for (int i = 0; i <= phoneList.size() - 1; i++) {
                        phoneList.get(i).setCheck(false);
                        myAdapter.notifyItemChanged(i);
                    }
                    item.setTitle("Check All");
                }

                break;
            case R.id.deleteSelected:
                for(int i = phoneList.size()-1; i>= 0;i--){
                    if(phoneList.get(i).isChecked()==true){
                        phoneList.remove(i);
                        myAdapter.notifyItemRemoved(i);
                    }
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
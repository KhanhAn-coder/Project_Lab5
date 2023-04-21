package com.example.lab5_ex2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    ArrayList<Event> eventList = new ArrayList<>();

    final ActivityResultLauncher<Intent> launcherActivityInfo = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        Bundle extras = intent.getExtras();
                        eventList.add(new Event(extras.getString("name"), extras.getString("place"), extras.getString("date"), extras.getString("time"),false ));
                        myAdapter = new MyAdapter(eventList);
                        recyclerView.setAdapter(myAdapter);


                    }
                }
            });




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        myAdapter = new MyAdapter(eventList);
        eventList.add(new Event("Sinh Hoat Chu Nhiem","C210","09/03/2023","4:49",false));
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,RecyclerView.VERTICAL));
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        MenuItem itemSwitch = menu.findItem(R.id.ItemSwitch);
        final Switch sw = (Switch) itemSwitch.getActionView();
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    for(int i = 0;i<=eventList.size()-1;i++){
                        eventList.get(i).setCheck(true);
                        myAdapter.notifyItemChanged(i);
                    }
                }else {
                    for(int i=0;i<=eventList.size()-1;i++){
                        eventList.get(i).setCheck(false);
                        myAdapter.notifyItemChanged(i);
                    }
                }
            }
        });

        return true;
    }

    @SuppressLint("ResourceType")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.ItemAdd:
                Intent intent = new Intent(MainActivity.this,EditEvent.class);
                intent.putExtra("code","add");
                launcherActivityInfo.launch(intent);
                break;

            case R.id.RemoveAll:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Do you want to delete all items ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(eventList.size()==0){
                            Toast.makeText(MainActivity.this, "Empty List", Toast.LENGTH_SHORT).show();
                        }else {
                            eventList.clear();
                            myAdapter.notifyDataSetChanged();
                        }

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();


        }
        return super.onOptionsItemSelected(item);
    }
}
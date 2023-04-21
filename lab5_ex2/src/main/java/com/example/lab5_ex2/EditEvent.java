package com.example.lab5_ex2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.SimpleTimeZone;

public class EditEvent extends AppCompatActivity {
    EditText edtname, edtdate, edtplace, edttime;
    Calendar myCalendar = Calendar.getInstance();
    Calendar myCurrentTime = Calendar.getInstance();
    ArrayList<String> roomList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);
        edtname = findViewById(R.id.edtname);
        edtdate = findViewById(R.id.edtdate);
        edtplace = findViewById(R.id.edtplace);
        edttime = findViewById(R.id.edttime);

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR,year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                edtdate.setText( day+"/"+(month+1)+"/"+year);
            }
        };

        edtdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(EditEvent.this, date,myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                myCalendar.set(Calendar.HOUR_OF_DAY,hour);
                myCalendar.set(Calendar.MINUTE,minute);
                edttime.setText(hour+":"+minute);
            }
        };

        edttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(EditEvent.this, time,myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE),true).show();
            }
        });




        edtplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] roomList2 = {"C201","C202","C203","C204"};
                int checkedItem = 1;
                AlertDialog.Builder builder = new AlertDialog.Builder(EditEvent.this);
                builder.setTitle("Select Place");
                builder.setSingleChoiceItems(roomList2, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                edtplace.setText("C201");
                                break;
                            case 1:
                                edtplace.setText("C202");
                                break;
                            case 2:
                                edtplace.setText("C203");
                                break;
                            case 3:
                                edtplace.setText("C204");
                                break;
                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        getDataFromMainActivity();

    }



    private void getDataFromMainActivity() {
        Intent intent = getIntent();
        String code = intent.getStringExtra("code");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_save,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.ItemSave:
                Intent intent = new Intent();
                Bundle extras = new Bundle();
                extras.putString("name",edtname.getText().toString());
                extras.putString("date",edtdate.getText().toString());
                extras.putString("place",edtplace.getText().toString());
                extras.putString("time",edttime.getText().toString());
                intent.putExtras(extras);
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
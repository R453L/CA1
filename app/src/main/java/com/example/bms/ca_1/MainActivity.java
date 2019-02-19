package com.example.bms.ca_1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        radioGroup = findViewById(R.id.radioGroup);

        findViewById(R.id.btnProceed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected = radioGroup.getCheckedRadioButtonId();

                if(selected == R.id.radioStudent){
                    startActivity(new Intent(MainActivity.this,StudentCourseActivity.class));
                }
                else if(selected == R.id.radioEmployee){

                    final String[] course = {"one", "two", "three", "four", "five"};

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Choose Course");

                    builder.setItems(course, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            showCustomToast(course[which]+" is selected.");
                        }
                    });
                    builder.setPositiveButton("Ok",null);
                    builder.setNegativeButton("No",null);
                    builder.setNeutralButton("Cancel",null);

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else {
                    Toast.makeText(MainActivity.this,
                            "Select at least one radio option.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void showCustomToast(String msg){
        LayoutInflater inflater = getLayoutInflater();

        View layout = inflater.inflate(R.layout.custom_toast_layout,(ViewGroup) findViewById(R.id.custom_toast_layout));
        TextView text = layout.findViewById(R.id.tv1);
        text.setText(msg);

        Toast toast = new Toast(MainActivity.this);
        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return true;
    }
}

package com.example.cse489_2023_3_2020260187;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ClassLecturesActivity extends AppCompatActivity {

    Button addNew, Back ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_lectures);

        // calling the value from signup activity
        Intent intent = this.getIntent();
        String username = intent.getStringExtra("UserName");

        System.out.println(username);

//        SharedPreferences localPref = ClassLecturesActivity.this.getPreferences(MODE_PRIVATE);
//        String useername = localPref.getString("name","");
//        System.out.println(useername);


        addNew = findViewById(R.id.btnAddNew);




        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ClassLecturesActivity.this, ClassSummaryActivity.class);
                intent.putExtra("id", "2020-2-60-187");
                startActivity(intent);

            }
        });

    }
}
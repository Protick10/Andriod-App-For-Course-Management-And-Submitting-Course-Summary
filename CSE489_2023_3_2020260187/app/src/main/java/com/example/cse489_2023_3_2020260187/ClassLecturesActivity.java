package com.example.cse489_2023_3_2020260187;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
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
                intent.putExtra("name","Protick Saha");
                startActivity(intent);

            }
        });

        loadClassSummary();



    }
    private void loadClassSummary(){

        String q= "SELECT * FROM ClassSummary";
        ClassSummaryDB db = new ClassSummaryDB(this,"ClassSummaryDB.db", null, 1);
        Cursor cur = db.selectClassSummary(q);
        if (cur != null){
            if (cur.getCount()>0){
                while (cur.moveToNext()){
                    String ID = cur.getString(0);
                    String Name = cur.getString(1);
                    String course = cur.getString(2);
                    String type = cur.getString(3);
                    String date = cur.getString(4);
                    String lecture = cur.getString(5);
                    String topic = cur.getString(6);
                    String summary = cur.getString(7);


                    System.out.println(ID);
                    System.out.println(Name);
                    System.out.println(course);
                    System.out.println(type);
                    System.out.println(date);
                    System.out.println(lecture);
                    System.out.println(topic);
                    System.out.println(summary);
                }
            }
            cur.close();
        }
        db.close();
    }


}
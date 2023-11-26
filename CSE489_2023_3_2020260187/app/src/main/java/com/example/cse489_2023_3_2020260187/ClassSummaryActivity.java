package com.example.cse489_2023_3_2020260187;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ClassSummaryActivity extends AppCompatActivity {

    TextView summaryId, summaryName ;
    RadioGroup courseGroup;
    RadioButton radioButton1, radioButton2, radioButton3, radioButton4, radioButtonTheory, radioButtonLab;
    EditText summarydate, summaryLecture, summaryTopic, summarySummary;

    String courseradio,typeradio, clssumname, clssumid;
    private EditText etTopic;

//    private ClassSummaryDB db;
    private ClassSummary classSummary;

//    int lecture_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_summary);

        summaryId = findViewById(R.id.tvID);
        summaryName = findViewById(R.id.tvName);
        courseGroup = findViewById(R.id.radioGrp1);
        radioButton1 = findViewById(R.id.radioBtn1);
        radioButton2 = findViewById(R.id.radioBtn2);
        radioButton3 = findViewById(R.id.radioBtn3);
        radioButton4 = findViewById(R.id.radioBtn4);
        radioButtonTheory = findViewById(R.id.radioBtnTheory);
        radioButtonTheory = findViewById(R.id.radioBtnLab);
        summarydate = findViewById(R.id.date);
        summaryLecture = findViewById(R.id.etLecture);
        summaryTopic = findViewById(R.id.etTopic);
        summarySummary = findViewById(R.id.etSummary);


        SharedPreferences localPref = getPreferences(MODE_PRIVATE);
        clssumname = localPref.getString("name","");
        clssumid = localPref.getString("user_id","");
//        Intent intent = this.getIntent();
//         clssumid = intent.getStringExtra("id");
//        clssumname = intent.getStringExtra("name");
        summaryId.setText(clssumid);
        summaryName.setText(clssumname);

        String lecture = summaryLecture.getText().toString();

//        int lecture_no = Integer.valueOf(lecture);









        etTopic = findViewById(R.id.etTopic);

        findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic = etTopic.getText().toString();
//                System.out.println(topic);

                if (TextUtils.isEmpty(summarydate.getText().toString())){
                    summarydate.setError("please enter the date");
                } else if (TextUtils.isEmpty(summaryLecture.getText().toString())) {
                    summaryLecture.setError("Please fill up the lecture field");
                } else if (TextUtils.isEmpty(summaryTopic.getText().toString())) {
                    summaryTopic.setError("enter the topic");

                } else if (TextUtils.isEmpty(summarySummary.getText().toString())) {
                    summarySummary.setError("please enter the summary");
                }
                else if (radioButton1.isChecked()){

                    courseradio = radioButton1.getText().toString();

                }else if (radioButton2.isChecked()){

                    courseradio = radioButton2.getText().toString();

                }else if (radioButton3.isChecked()){

                    courseradio = radioButton3.getText().toString();

                }else if (radioButton4.isChecked()){

                    courseradio = radioButton4.getText().toString();

                } else if (radioButtonTheory.isChecked()) {

                    typeradio = radioButtonTheory.getText().toString();

                } else if (radioButtonLab.isChecked()) {

                    typeradio = radioButtonLab.getText().toString();
                }
                    else {

                    if (clssumid.isEmpty()){

                        clssumid = topic + System.currentTimeMillis();
                        ClassSummaryDB db = new ClassSummaryDB(ClassSummaryActivity.this);
//                        db = new ClassSummaryDB(ClassSummaryActivity.this);
                        db.insertClassSummary(clssumid,clssumname,courseradio,typeradio,summarydate.getText().toString(),Integer.valueOf(lecture),summaryTopic.getText().toString(),summarySummary.getText().toString());

                        Intent intent1= new Intent(ClassSummaryActivity.this,ClassLecturesActivity.class);
                        startActivity(intent1);
                        db.close();
                        finish();
//

                    }else {
                        ClassSummaryDB db = new ClassSummaryDB(ClassSummaryActivity.this);
                        db.updateClassSummary(clssumid,clssumname,courseradio,typeradio,summarydate.getText().toString(),Integer.valueOf(lecture),summaryTopic.getText().toString(),summarySummary.getText().toString());

                        Intent intent2= new Intent(ClassSummaryActivity.this,ClassLecturesActivity.class);
                        startActivity(intent2);
                        db.close();
                        finish();



                    }


//                    Intent intent1= new Intent(ClassSummaryActivity.this,ClassLecturesActivity.class);
//                    startActivity(intent1);



                }
//                System.out.println(clssumid);
//                System.out.println(clssumname);
//                System.out.println(courseradio);
//                System.out.println(typeradio);
//                System.out.println(summarydate.getText().toString());
//                System.out.println(Integer.valueOf(lecture));
//                System.out.println(summaryTopic.getText().toString());
//                System.out.println(summarySummary.getText().toString());



//
//                Intent intent1= new Intent(ClassSummaryActivity.this,ClassLecturesActivity.class);
//                startActivity(intent1);
            }


        });
    }






}
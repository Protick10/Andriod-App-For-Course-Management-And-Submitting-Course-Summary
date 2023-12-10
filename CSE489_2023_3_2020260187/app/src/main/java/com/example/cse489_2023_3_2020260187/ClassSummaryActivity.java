package com.example.cse489_2023_3_2020260187;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.NameValuePair;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class ClassSummaryActivity extends AppCompatActivity {

    TextView summaryId, summaryName ;
    RadioGroup courseGroup;
    RadioButton radioButton1, radioButton2, radioButton3, radioButton4, radioButtonTheory, radioButtonLab;
    EditText summarydate, summaryLecture, summaryTopic, summarySummary;

    String courseradio,typeradio, clssumname, clssumid;
    String dbID="";
    private EditText etTopic;
    int lecno;

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
        radioButtonLab = findViewById(R.id.radioBtnLab);
        summarydate = findViewById(R.id.date);
        summaryLecture = findViewById(R.id.etLecture);
        summaryTopic = findViewById(R.id.etTopic);
        summarySummary = findViewById(R.id.etSummary);


        SharedPreferences localPref = getSharedPreferences("localPref", MODE_PRIVATE);

        Log.d("SharedPreferences", "Retrieving name: " + localPref.getString("name", ""));
        Log.d("SharedPreferences", "Retrieving user_id: " + localPref.getString("user_id", ""));
        clssumname = localPref.getString("name","");
        clssumid = localPref.getString("user_id","");
//        Intent intent = this.getIntent();
//         clssumid = intent.getStringExtra("id");
//        clssumname = intent.getStringExtra("name");
        summaryId.setText(clssumid);
        summaryName.setText(clssumname);

//        String lecture = summaryLecture.getText().toString();

//        int lecture_no = Integer.valueOf(lecture);









        etTopic = findViewById(R.id.etTopic);

        findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic = etTopic.getText().toString();
                String lecture = summaryLecture.getText().toString();
                lecno = Integer.valueOf(lecture);
//                System.out.println(topic);

                if (radioButton1.isChecked() || radioButton2.isChecked() || radioButton3.isChecked() || radioButton4.isChecked()) {
                    // Check which radio button is selected
                    if (radioButton1.isChecked()) {
                        courseradio = radioButton1.getText().toString();
                    } else if (radioButton2.isChecked()) {
                        courseradio = radioButton2.getText().toString();
                    } else if (radioButton3.isChecked()) {
                        courseradio = radioButton3.getText().toString();
                    } else if (radioButton4.isChecked()) {
                        courseradio = radioButton4.getText().toString();
                    }

                    // Check which type radio button is selected
                    if (radioButtonTheory.isChecked()) {
                        typeradio = radioButtonTheory.getText().toString();
                    } else if (radioButtonLab.isChecked()) {
                        typeradio = radioButtonLab.getText().toString();
                    }

                    // Save the class summary
                    if (!TextUtils.isEmpty(summarydate.getText().toString()) && !TextUtils.isEmpty(summaryLecture.getText().toString()) &&
                            !TextUtils.isEmpty(summaryTopic.getText().toString()) && !TextUtils.isEmpty(summarySummary.getText().toString())) {
                        if (dbID.isEmpty()) {
                            dbID = topic + System.currentTimeMillis();

                            ClassSummaryDB db = new ClassSummaryDB(ClassSummaryActivity.this);
                            db.insertClassSummary(dbID, clssumname, courseradio, typeradio, summarydate.getText().toString(), lecno, summaryTopic.getText().toString(), summarySummary.getText().toString());
                            //for remote database...
                            String keys[] = {"action", "sid", "semester", "id", "course", "type", "topic", "date", "lecture",
                                    "summary"};
                            String values[] = {"backup", "2020-2-60-187", "2023-3", dbID, courseradio, typeradio, summaryTopic.getText().toString(), summarydate.getText().toString(),lecture,summarySummary.getText().toString()};
                            httpRequest(keys,values);
                            Intent intent1 = new Intent(ClassSummaryActivity.this, ClassLecturesActivity.class);
                            startActivity(intent1);
                            db.close();
                        } else {
                            ClassSummaryDB db = new ClassSummaryDB(ClassSummaryActivity.this);
                            db.updateClassSummary(dbID, clssumname, courseradio, typeradio, summarydate.getText().toString(), lecno, summaryTopic.getText().toString(), summarySummary.getText().toString());

                            Intent intent2 = new Intent(ClassSummaryActivity.this, ClassLecturesActivity.class);
                            startActivity(intent2);
                            db.close();

                        }

//                        Log.d("ClassSummaryActivity", "Save button clicked");
                    } else {
                        // Display an error message
                        Toast.makeText(ClassSummaryActivity.this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Display an error message
                    Toast.makeText(ClassSummaryActivity.this, "Please select a course and type", Toast.LENGTH_SHORT).show();
                }


//                if (TextUtils.isEmpty(summarydate.getText().toString())){
//                    summarydate.setError("please enter the date");
//                } else if (TextUtils.isEmpty(summaryLecture.getText().toString())) {
//                    summaryLecture.setError("Please fill up the lecture field");
//                } else if (TextUtils.isEmpty(summaryTopic.getText().toString())) {
//                    summaryTopic.setError("enter the topic");
//
//                } else if (TextUtils.isEmpty(summarySummary.getText().toString())) {
//                    summarySummary.setError("please enter the summary");
//                }
//                else if (radioButton1.isChecked()){
//
//                    courseradio = radioButton1.getText().toString();
//
//                }else if (radioButton2.isChecked()){
//
//                    courseradio = radioButton2.getText().toString();
//
//                }else if (radioButton3.isChecked()){
//
//                    courseradio = radioButton3.getText().toString();
//
//                }else if (radioButton4.isChecked()){
//
//                    courseradio = radioButton4.getText().toString();
//
//                } else if (radioButtonTheory.isChecked()) {
//
//                    typeradio = radioButtonTheory.getText().toString();
//
//                } else if (radioButtonLab.isChecked()) {
//
//                    typeradio = radioButtonLab.getText().toString();
//                }
//                    else {
//
//                    if (clssumid.isEmpty()){
//
//                        clssumid = topic + System.currentTimeMillis();
//                        ClassSummaryDB db = new ClassSummaryDB(ClassSummaryActivity.this);
////                        db = new ClassSummaryDB(ClassSummaryActivity.this);
//                        db.insertClassSummary(clssumid,clssumname,courseradio,typeradio,summarydate.getText().toString(),lecno,summaryTopic.getText().toString(),summarySummary.getText().toString());
//
////                        Intent intent1= new Intent(ClassSummaryActivity.this,ClassLecturesActivity.class);
////                        startActivity(intent1);
//                        db.close();
////                        finish();
//                        Log.d("ClassSummaryActivity", "Save button clicked");
////
//
//                    }else {
//                        ClassSummaryDB db = new ClassSummaryDB(ClassSummaryActivity.this);
//                        db.updateClassSummary(clssumid,clssumname,courseradio,typeradio,summarydate.getText().toString(),lecno,summaryTopic.getText().toString(),summarySummary.getText().toString());
//
////                        Intent intent2= new Intent(ClassSummaryActivity.this,ClassLecturesActivity.class);
////                        startActivity(intent2);
//                        db.close();
////                        finish();
//                        Log.d("ClassSummaryActivity", "Save button clicked");
//
//
//
//                    }
//
//
//                    Intent intent1= new Intent(ClassSummaryActivity.this,ClassLecturesActivity.class);
//                    startActivity(intent1);
//
//
//
//                }

//                else {
//                    // Move your database operations outside of the conditions
//                    if (clssumid.isEmpty()) {
//                        clssumid = topic + System.currentTimeMillis();
//                    }
//
//                    ClassSummaryDB db = new ClassSummaryDB(ClassSummaryActivity.this);
//                    db.insertClassSummary(clssumid, clssumname, courseradio, typeradio, summarydate.getText().toString(), lecno, summaryTopic.getText().toString(), summarySummary.getText().toString());
//
//                    Intent intent1 = new Intent(ClassSummaryActivity.this, ClassLecturesActivity.class);
//                    startActivity(intent1);
//                    db.close();
//                    Log.d("ClassSummaryActivity", "Save button clicked");
//                }
//                System.out.println(clssumid);
//                System.out.println(clssumname);
//                System.out.println(courseradio);
//                System.out.println(typeradio);
//                System.out.println(summarydate.getText().toString());
//                System.out.println(lecno);
//                System.out.println(summaryTopic.getText().toString());
//                System.out.println(summarySummary.getText().toString());



//
//                Intent intent1= new Intent(ClassSummaryActivity.this,ClassLecturesActivity.class);
//                startActivity(intent1);
            }


        });
    }

    private void httpRequest(final String keys[],final String values[]){
        new AsyncTask<Void,Void,String>(){
            @Override
            protected String doInBackground(Void... voids) {
                List<NameValuePair> params=new ArrayList<NameValuePair>();
                for (int i=0; i<keys.length; i++){
                    params.add(new BasicNameValuePair(keys[i],values[i]));
                }
                String url= "https://www.muthosoft.com/univ/cse489/index.php";
                String data="";
                try {
                    data=JSONParser.getInstance().makeHttpRequest(url,"POST",params);
                    System.out.println(data);
                    return data;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
            protected void onPostExecute(String data){
                if(data!=null){
                    System.out.println(data);
                    System.out.println("Ok2");
                   // updateClassSummaryListByServerData(data);
                   // Toast.makeText(getApplicationContext(),data,Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }






}
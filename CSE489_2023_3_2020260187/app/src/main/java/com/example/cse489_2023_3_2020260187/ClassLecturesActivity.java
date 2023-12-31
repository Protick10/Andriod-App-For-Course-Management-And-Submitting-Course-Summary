package com.example.cse489_2023_3_2020260187;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ClassLecturesActivity extends AppCompatActivity {

    Button addNew, Back ;

    private ArrayList<ClassSummary> classes; // represents information of each lecture
    private ClassSummaryAdapter adapter; // Use to render the template for each lecture
    private ListView lvClasses;
//    ClassSummaryDB classSummaryDB;
//    ClassSummary classSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_lectures);

        // initialize list-reference by ListView object defined in XML
        lvClasses = findViewById(R.id.lvLec);
        classes = new ArrayList<>();

        // calling the value from signup activity
        Intent intent = this.getIntent();
        String username = intent.getStringExtra("name");
        String id = intent.getStringExtra("id");



//        System.out.println(username);

//        SharedPreferences localPref = ClassLecturesActivity.this.getPreferences(MODE_PRIVATE);
//        String useername = localPref.getString("name","");
//        System.out.println(useername);


        addNew = findViewById(R.id.btnAddNew);

//        loadClassSummary();




        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(ClassLecturesActivity.this, ClassSummaryActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("name",username);
                startActivity(intent);

            }
        });





    }

    public void onStart(){
        super.onStart();
        loadData();
    }

    private void loadData(){
        classes.clear();
        ClassSummaryDB db = new ClassSummaryDB(this);
        Cursor rows = db.selectClassSummary("SELECT * FROM ClassSummary");
        if (rows.getCount() > 0) {
            while (rows.moveToNext()) {
                String id = rows.getString(0);
                String Name = rows.getString(1);
                String course = rows.getString(2);
                String type = rows.getString(3);
                String date = rows.getString(4);
                int lecture = rows.getInt(5);
                String topic = rows.getString(6);
                String summary = rows.getString(7);

                ClassSummary cs = new ClassSummary (id, Name, course, type, date, lecture, topic, summary );
                classes.add(cs);
            }// end-while
        } // end-if
        db.close();
        adapter = new ClassSummaryAdapter(this, classes);
        lvClasses.setAdapter(adapter);



// handle the click on an class-summary-list item
        lvClasses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int
            position, long id) {
// String item = (String) parent.getItemAtPosition(position);
                System.out.println(position);
                Intent i = new Intent(ClassLecturesActivity.this, ClassSummaryActivity.class);
                i.putExtra("ClassSummaryKey", classes.get(position).id);
                i.putExtra("CourseCode", classes.get(position).course);
                i.putExtra("Lecture", classes.get(position).lecture);
                i.putExtra("Topic", classes.get(position).topic);
                i.putExtra("Date", classes.get(position).date);
                i.putExtra("Type", classes.get(position).type);
                i.putExtra("Summary", classes.get(position).description);



                startActivity(i);
            }
        });

// handle the long-click on an class-summary-list item
        lvClasses.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int
            position, long id) {

            String info = classes.get(position).course + ","  + classes.get(position).topic;
            String message = "Do you want to delete class-summary"+ info + "?";
            System.out.println(message);
//showDialog(message, &quot;Delete Event&quot;, events.get(position).key);
            return true;
        }
        });
    }



//    private void loadClassSummary(){
//
//        String q= "SELECT * FROM ClassSummary";
//        ClassSummaryDB db = new ClassSummaryDB(this);
//        Cursor cur = db.selectClassSummary(q);
//        if (cur != null){
//            if (cur.getCount()>0){
//                while (cur.moveToNext()){
//                    String ID = cur.getString(0);
//                    String Name = cur.getString(1);
//                    String course = cur.getString(2);
//                    String type = cur.getString(3);
//                    String date = cur.getString(4);
//                    String lecture = cur.getString(5);
//                    String topic = cur.getString(6);
//                    String summary = cur.getString(7);
//
//
//                    System.out.println(ID);
//                    System.out.println(Name);
//                    System.out.println(course);
//                    System.out.println(type);
//                    System.out.println(date);
//                    System.out.println(lecture);
//                    System.out.println(topic);
//                    System.out.println(summary);
//                }
//            }
//            cur.close();
//        }
//        db.close();
//    }




}
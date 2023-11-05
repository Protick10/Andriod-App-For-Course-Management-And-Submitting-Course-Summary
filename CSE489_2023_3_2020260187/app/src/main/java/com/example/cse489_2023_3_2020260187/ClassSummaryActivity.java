package com.example.cse489_2023_3_2020260187;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ClassSummaryActivity extends AppCompatActivity {

    TextView summaryId;

    private EditText etTopic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_summary);

        summaryId = findViewById(R.id.tvID);

        Intent intent = this.getIntent();
        String id = intent.getStringExtra("id");
        summaryId.setText(id);




        etTopic = findViewById(R.id.etTopic);

        findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic = etTopic.getText().toString();
                System.out.println(topic);
            }
        });
    }
}
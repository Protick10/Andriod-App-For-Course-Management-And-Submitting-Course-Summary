package com.example.cse489_2023_3_2020260187;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ClassSummaryAdapter extends ArrayAdapter<ClassSummary> {
    private final Context context;
    private final ArrayList<ClassSummary> values;

    public ClassSummaryAdapter(@NonNull Context context, @NonNull ArrayList<ClassSummary> items) {
        super(context, -1, items);
        this.context = context;
        this.values = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.layout_class_summary_row, parent, false);

        TextView topLeftTitle = rowView.findViewById(R.id.tvtopLeftTitle);
        TextView dateTime = rowView.findViewById(R.id.tvDateTime);
        TextView topic = rowView.findViewById(R.id.tvETopic);
        //TextView eventType = rowView.findViewById(R.id.tvEventType);

        ClassSummary e = values.get(position);
        topLeftTitle.setText(e.course + ":" + e.type + "-" + e.lecture);
        dateTime.setText(e.date);
        topic.setText(e.topic);

        return rowView;
    }
}

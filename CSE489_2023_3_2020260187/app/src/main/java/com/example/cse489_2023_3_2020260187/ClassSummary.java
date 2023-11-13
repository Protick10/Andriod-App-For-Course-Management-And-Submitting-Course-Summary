package com.example.cse489_2023_3_2020260187;



// Change the class name and attributes according to the fields of Lectur Summary

public class ClassSummary {
    String id = "";
    String name = "";
    String course = "" ;
    String type = "";
    String date = "";
    int lecture ;
    String topic = "";

    String description = "";


    public ClassSummary(String id, String name, String course,String type, String date,int lecture,String topic, String description){
        this.id = id;
        this.name = name;
        this.course = course;
        this.type = type;
        this.date = date;
        this.lecture = lecture;
        this.topic = topic;
        this.description = description;

    }
}

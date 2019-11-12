package com.jen.android.coursescheduler_jlau;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "course_table")
public class Course {

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @PrimaryKey(autoGenerate = true)
    private int courseId;

    @NonNull
    @ColumnInfo(name ="course")
    private String mCourse;

    public String getCourse() {
        return this.mCourse;
    }

    public Course(@NonNull String mCourse) {
        this.mCourse = mCourse;
    }
}

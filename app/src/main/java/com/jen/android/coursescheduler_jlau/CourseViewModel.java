package com.jen.android.coursescheduler_jlau;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;


public class CourseViewModel extends AndroidViewModel {
    //add reference to repository
    private CourseRepository mRepository;
    //cache list of words
    private LiveData<List<Course>> mAllCourses;
    //reference library and get list of words
    public CourseViewModel(Application application) {
        super(application);
        mRepository = new CourseRepository(application);
        mAllCourses = mRepository.getAllCourses();
    }
    //this completely hides the implementation from the UI
    LiveData<List<Course>> getAllCourses() {
        return mAllCourses;
    }

    public void insert(Course course) {
        mRepository.insert(course);
    }





}
